package androidx.compose.foundation.text;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.InputTransformationKt;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.TextObfuscationMode;
import androidx.compose.foundation.text.input.TextObfuscationMode_androidKt;
import androidx.compose.foundation.text.input.internal.CodepointTransformation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.autofill.ContentType;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Density;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BasicSecureTextField.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aÛ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u001028\b\u0002\u0010\u0011\u001a2\u0012\u0004\u0012\u00020\u0013\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012¢\u0006\u0002\b\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%H\u0007¢\u0006\u0004\b&\u0010'\u001a\u001a\u0010(\u001a\u0004\u0018\u00010\n*\u0004\u0018\u00010\n2\b\u0010)\u001a\u0004\u0018\u00010\nH\u0002\u001a \u0010-\u001a\u00020\u00012\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b/H\u0003¢\u0006\u0002\u00100\u001aÇ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u001028\b\u0002\u0010\u0011\u001a2\u0012\u0004\u0012\u00020\u0013\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012¢\u0006\u0002\b\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b1\u00102\u001aÑ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u001028\b\u0002\u0010\u0011\u001a2\u0012\u0004\u0012\u00020\u0013\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012¢\u0006\u0002\b\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#H\u0007¢\u0006\u0004\b3\u00104\"\u000e\u0010*\u001a\u00020+X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010,\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"BasicSecureTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "Lkotlin/ExtensionFunctionType;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "decorator", "Landroidx/compose/foundation/text/input/TextFieldDecorator;", "textObfuscationMode", "Landroidx/compose/foundation/text/input/TextObfuscationMode;", "textObfuscationCharacter", "", "scrollState", "Landroidx/compose/foundation/ScrollState;", "BasicSecureTextField-ltb6GB4", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/TextFieldDecorator;ICLandroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "then", "next", "LAST_TYPED_CHARACTER_REVEAL_DURATION_MILLIS", "", "DefaultObfuscationCharacter", "DisableCutCopy", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "BasicSecureTextField-Jb9bMDk", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/TextFieldDecorator;ICLandroidx/compose/runtime/Composer;III)V", "BasicSecureTextField-egD4TGM", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text/input/TextFieldDecorator;ICLandroidx/compose/runtime/Composer;III)V", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BasicSecureTextFieldKt {
    private static final char DefaultObfuscationCharacter = 8226;
    private static final long LAST_TYPED_CHARACTER_REVEAL_DURATION_MILLIS = 1500;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicSecureTextField_Jb9bMDk$lambda$11(TextFieldState textFieldState, Modifier modifier, boolean z, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, int i2, int i3, int i4, Composer composer, int i5) {
        m1252BasicSecureTextFieldJb9bMDk(textFieldState, modifier, z, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, function2, mutableInteractionSource, brush, textFieldDecorator, i, c, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicSecureTextField_egD4TGM$lambda$12(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, int i2, int i3, int i4, Composer composer, int i5) {
        m1253BasicSecureTextFieldegD4TGM(textFieldState, modifier, z, z2, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, function2, mutableInteractionSource, brush, textFieldDecorator, i, c, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicSecureTextField_ltb6GB4$lambda$8(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, ScrollState scrollState, int i2, int i3, int i4, Composer composer, int i5) {
        m1254BasicSecureTextFieldltb6GB4(textFieldState, modifier, z, z2, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, function2, mutableInteractionSource, brush, textFieldDecorator, i, c, scrollState, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DisableCutCopy$lambda$10(Function2 function2, int i, Composer composer, int i2) {
        DisableCutCopy(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:124:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x04b0  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0213  */
    /* renamed from: BasicSecureTextField-ltb6GB4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m1254BasicSecureTextFieldltb6GB4(final TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, ScrollState scrollState, Composer composer, final int i2, final int i3, final int i4) {
        final TextFieldState textFieldState2;
        int i5;
        Modifier modifier2;
        int i6;
        boolean z3;
        int i7;
        boolean z4;
        int i8;
        final InputTransformation inputTransformation2;
        int i9;
        TextStyle textStyle2;
        int i10;
        final KeyboardOptions keyboardOptions2;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function22;
        final int i23;
        final char c2;
        Composer composer2;
        final boolean z5;
        final boolean z6;
        final InputTransformation inputTransformation3;
        final Modifier modifier3;
        final TextStyle textStyle3;
        final KeyboardActionHandler keyboardActionHandler2;
        final MutableInteractionSource mutableInteractionSource2;
        final Brush brush2;
        final TextFieldDecorator textFieldDecorator2;
        final ScrollState scrollState2;
        ScopeUpdateScope endRestartGroup;
        Modifier.Companion companion;
        KeyboardOptions keyboardOptions3;
        boolean z7;
        SolidColor solidColor;
        int i24;
        final KeyboardOptions keyboardOptions4;
        final ScrollState scrollState3;
        final Brush brush3;
        final TextFieldDecorator textFieldDecorator3;
        final KeyboardActionHandler keyboardActionHandler3;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function23;
        final MutableInteractionSource mutableInteractionSource3;
        TextStyle textStyle4;
        char c3;
        final boolean z8;
        int i25;
        final boolean z9;
        final boolean z10;
        CodepointTransformation codepointTransformation;
        Modifier.Companion companion2;
        int i26;
        Composer startRestartGroup = composer.startRestartGroup(1568790509);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicSecureTextField)N(state,modifier,enabled,readOnly,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,onTextLayout,interactionSource,cursorBrush,decorator,textObfuscationMode:c#foundation.text.input.TextObfuscationMode,textObfuscationCharacter,scrollState)140@8345L46,141@8428L60,142@8535L130,142@8493L172,155@9098L129,155@9059L168,162@9271L424,176@9771L38,177@9841L288,191@10367L891,191@10352L906:BasicSecureTextField.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
            textFieldState2 = textFieldState;
        } else {
            textFieldState2 = textFieldState;
            if ((i2 & 6) == 0) {
                i5 = (startRestartGroup.changed(textFieldState2) ? 4 : 2) | i2;
            } else {
                i5 = i2;
            }
        }
        int i27 = i4 & 2;
        if (i27 != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                z3 = z;
                i5 |= startRestartGroup.changed(z3) ? 256 : 128;
                i7 = i4 & 8;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    z4 = z2;
                    i5 |= startRestartGroup.changed(z4) ? 2048 : 1024;
                    i8 = i4 & 16;
                    int i28 = 8192;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i2 & 24576) == 0) {
                        inputTransformation2 = inputTransformation;
                        i5 |= startRestartGroup.changed(inputTransformation2) ? 16384 : 8192;
                        i9 = i4 & 32;
                        if (i9 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            textStyle2 = textStyle;
                        } else {
                            textStyle2 = textStyle;
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i5 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                            }
                        }
                        i10 = i4 & 64;
                        if (i10 != 0) {
                            i5 |= 1572864;
                            keyboardOptions2 = keyboardOptions;
                        } else {
                            keyboardOptions2 = keyboardOptions;
                            if ((i2 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(keyboardOptions2) ? 1048576 : 524288;
                            }
                        }
                        i11 = i4 & 128;
                        if (i11 != 0) {
                            i5 |= 12582912;
                        } else if ((i2 & 12582912) == 0) {
                            i5 |= startRestartGroup.changed(keyboardActionHandler) ? 8388608 : 4194304;
                        }
                        i12 = i4 & 256;
                        if (i12 != 0) {
                            i5 |= 100663296;
                        } else if ((i2 & 100663296) == 0) {
                            i13 = i12;
                            i5 |= startRestartGroup.changedInstance(function2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            i14 = i4 & 512;
                            if (i14 == 0) {
                                i5 |= 805306368;
                            } else if ((i2 & 805306368) == 0) {
                                i15 = i14;
                                i5 |= startRestartGroup.changed(mutableInteractionSource) ? 536870912 : 268435456;
                                i16 = i4 & 1024;
                                if (i16 != 0) {
                                    i18 = i3 | 6;
                                    i17 = i16;
                                } else if ((i3 & 6) == 0) {
                                    i17 = i16;
                                    i18 = i3 | (startRestartGroup.changed(brush) ? 4 : 2);
                                } else {
                                    i17 = i16;
                                    i18 = i3;
                                }
                                i19 = i4 & 2048;
                                if (i19 != 0) {
                                    i18 |= 48;
                                } else if ((i3 & 48) == 0) {
                                    i18 |= (i3 & 64) == 0 ? startRestartGroup.changed(textFieldDecorator) : startRestartGroup.changedInstance(textFieldDecorator) ? 32 : 16;
                                }
                                if ((i3 & 384) == 0) {
                                    if ((i4 & 4096) == 0 && startRestartGroup.changed(i)) {
                                        i26 = 256;
                                        i18 |= i26;
                                    }
                                    i26 = 128;
                                    i18 |= i26;
                                }
                                int i29 = i18;
                                i20 = i4 & 8192;
                                if (i20 != 0) {
                                    i21 = i29 | 3072;
                                } else if ((i3 & 3072) == 0) {
                                    i21 = i29 | (startRestartGroup.changed(c) ? 2048 : 1024);
                                } else {
                                    i21 = i29;
                                }
                                if ((i3 & 24576) == 0) {
                                    if ((i4 & 16384) == 0 && startRestartGroup.changed(scrollState)) {
                                        i28 = 16384;
                                    }
                                    i21 |= i28;
                                }
                                i22 = i21;
                                if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 9363) != 9362, i5 & 1)) {
                                    startRestartGroup.startDefaults();
                                    ComposerKt.sourceInformation(startRestartGroup, "138@8287L21");
                                    if ((i2 & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                        startRestartGroup.skipToGroupEnd();
                                        if ((i4 & 4096) != 0) {
                                            i22 &= -897;
                                        }
                                        if ((i4 & 16384) != 0) {
                                            i22 &= -57345;
                                        }
                                        keyboardActionHandler3 = keyboardActionHandler;
                                        function23 = function2;
                                        mutableInteractionSource3 = mutableInteractionSource;
                                        brush3 = brush;
                                        textFieldDecorator3 = textFieldDecorator;
                                        c3 = c;
                                        scrollState3 = scrollState;
                                        i25 = i22;
                                        keyboardOptions4 = keyboardOptions2;
                                        z9 = z4;
                                        companion = modifier2;
                                        textStyle4 = textStyle2;
                                        z8 = z3;
                                        i24 = i;
                                    } else {
                                        companion = i27 != 0 ? Modifier.INSTANCE : modifier2;
                                        if (i6 != 0) {
                                            z3 = true;
                                        }
                                        if (i7 != 0) {
                                            z4 = false;
                                        }
                                        if (i8 != 0) {
                                            inputTransformation2 = null;
                                        }
                                        if (i9 != 0) {
                                            textStyle2 = TextStyle.INSTANCE.getDefault();
                                        }
                                        if (i10 != 0) {
                                            keyboardOptions2 = KeyboardOptions.INSTANCE.getSecureTextField$foundation_release();
                                        }
                                        KeyboardActionHandler keyboardActionHandler4 = i11 != 0 ? null : keyboardActionHandler;
                                        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function24 = i13 != 0 ? null : function2;
                                        MutableInteractionSource mutableInteractionSource4 = i15 != 0 ? null : mutableInteractionSource;
                                        if (i17 != 0) {
                                            keyboardOptions3 = keyboardOptions2;
                                            z7 = z3;
                                            solidColor = new SolidColor(Color.INSTANCE.m4641getBlack0d7_KjU(), null);
                                        } else {
                                            keyboardOptions3 = keyboardOptions2;
                                            z7 = z3;
                                            solidColor = brush;
                                        }
                                        TextFieldDecorator textFieldDecorator4 = i19 != 0 ? null : textFieldDecorator;
                                        if ((i4 & 4096) != 0) {
                                            i24 = TextObfuscationMode_androidKt.getDefault(TextObfuscationMode.INSTANCE);
                                            i22 &= -897;
                                        } else {
                                            i24 = i;
                                        }
                                        char c4 = i20 != 0 ? (char) 8226 : c;
                                        int i30 = i22;
                                        if ((i4 & 16384) != 0) {
                                            brush3 = solidColor;
                                            textFieldDecorator3 = textFieldDecorator4;
                                            keyboardActionHandler3 = keyboardActionHandler4;
                                            function23 = function24;
                                            mutableInteractionSource3 = mutableInteractionSource4;
                                            c3 = c4;
                                            scrollState3 = ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1);
                                            keyboardOptions4 = keyboardOptions3;
                                            i25 = i30 & (-57345);
                                            z9 = z4;
                                            textStyle4 = textStyle2;
                                            z8 = z7;
                                        } else {
                                            keyboardOptions4 = keyboardOptions3;
                                            scrollState3 = scrollState;
                                            brush3 = solidColor;
                                            textFieldDecorator3 = textFieldDecorator4;
                                            keyboardActionHandler3 = keyboardActionHandler4;
                                            function23 = function24;
                                            mutableInteractionSource3 = mutableInteractionSource4;
                                            textStyle4 = textStyle2;
                                            c3 = c4;
                                            z8 = z7;
                                            i25 = i30;
                                            z9 = z4;
                                        }
                                    }
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1568790509, i5, i25, "androidx.compose.foundation.text.BasicSecureTextField (BasicSecureTextField.kt:139)");
                                    }
                                    final State rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Character.valueOf(c3), startRestartGroup, (i25 >> 9) & 14);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 74193289, "CC(remember):BasicSecureTextField.kt#9igjgp");
                                    Object rememberedValue = startRestartGroup.rememberedValue();
                                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue = new SecureTextFieldController(rememberUpdatedState);
                                        startRestartGroup.updateRememberedValue(rememberedValue);
                                    }
                                    final SecureTextFieldController secureTextFieldController = (SecureTextFieldController) rememberedValue;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 74196783, "CC(remember):BasicSecureTextField.kt#9igjgp");
                                    boolean changedInstance = startRestartGroup.changedInstance(secureTextFieldController);
                                    BasicSecureTextFieldKt$BasicSecureTextField$1$1 rememberedValue2 = startRestartGroup.rememberedValue();
                                    if (changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue2 = new BasicSecureTextFieldKt$BasicSecureTextField$1$1(secureTextFieldController, null);
                                        startRestartGroup.updateRememberedValue(rememberedValue2);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    EffectsKt.LaunchedEffect(secureTextFieldController, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, startRestartGroup, 0);
                                    if (TextObfuscationMode.m1500equalsimpl0(i24, TextObfuscationMode.INSTANCE.m1505getRevealLastTypedvTwcZD0())) {
                                        startRestartGroup.startReplaceGroup(74209068);
                                        ComposerKt.sourceInformation(startRestartGroup, "152@8922L31");
                                        z10 = BasicSecureTextField_androidKt.platformAllowsRevealLastTyped(startRestartGroup, 0);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        startRestartGroup.startReplaceGroup(-1994484951);
                                        startRestartGroup.endReplaceGroup();
                                        z10 = false;
                                    }
                                    Boolean valueOf = Boolean.valueOf(z10);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 74214798, "CC(remember):BasicSecureTextField.kt#9igjgp");
                                    boolean changed = startRestartGroup.changed(z10) | startRestartGroup.changedInstance(secureTextFieldController);
                                    final TextStyle textStyle5 = textStyle4;
                                    BasicSecureTextFieldKt$BasicSecureTextField$2$1 rememberedValue3 = startRestartGroup.rememberedValue();
                                    if (changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue3 = new BasicSecureTextFieldKt$BasicSecureTextField$2$1(z10, secureTextFieldController, null);
                                        startRestartGroup.updateRememberedValue(rememberedValue3);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    EffectsKt.LaunchedEffect(valueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue3, startRestartGroup, 0);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 74220629, "CC(remember):BasicSecureTextField.kt#9igjgp");
                                    boolean z11 = (((i25 & 896) ^ 384) > 256 && startRestartGroup.changed(i24)) || (i25 & 384) == 256;
                                    Object rememberedValue4 = startRestartGroup.rememberedValue();
                                    if (z11 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        if (TextObfuscationMode.m1500equalsimpl0(i24, TextObfuscationMode.INSTANCE.m1505getRevealLastTypedvTwcZD0())) {
                                            codepointTransformation = secureTextFieldController.getCodepointTransformation();
                                        } else {
                                            codepointTransformation = TextObfuscationMode.m1500equalsimpl0(i24, TextObfuscationMode.INSTANCE.m1504getHiddenvTwcZD0()) ? new CodepointTransformation() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda3
                                                @Override // androidx.compose.foundation.text.input.internal.CodepointTransformation
                                                public final int transform(int i31, int i32) {
                                                    int BasicSecureTextField_ltb6GB4$lambda$4$lambda$3;
                                                    BasicSecureTextField_ltb6GB4$lambda$4$lambda$3 = BasicSecureTextFieldKt.BasicSecureTextField_ltb6GB4$lambda$4$lambda$3(State.this, i31, i32);
                                                    return BasicSecureTextField_ltb6GB4$lambda$4$lambda$3;
                                                }
                                            } : null;
                                        }
                                        startRestartGroup.updateRememberedValue(codepointTransformation);
                                        rememberedValue4 = codepointTransformation;
                                    }
                                    final CodepointTransformation codepointTransformation2 = (CodepointTransformation) rememberedValue4;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 74236243, "CC(remember):BasicSecureTextField.kt#9igjgp");
                                    Object rememberedValue5 = startRestartGroup.rememberedValue();
                                    if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda4
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                Unit BasicSecureTextField_ltb6GB4$lambda$6$lambda$5;
                                                BasicSecureTextField_ltb6GB4$lambda$6$lambda$5 = BasicSecureTextFieldKt.BasicSecureTextField_ltb6GB4$lambda$6$lambda$5((SemanticsPropertyReceiver) obj);
                                                return BasicSecureTextField_ltb6GB4$lambda$6$lambda$5;
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue5);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    Modifier semantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) rememberedValue5, 1, null);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 74238733, "CC(remember):BasicSecureTextField.kt#9igjgp");
                                    BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$2$1 rememberedValue6 = startRestartGroup.rememberedValue();
                                    if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                        rememberedValue6 = new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$2$1
                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                                                return m1255invokeZmokQxo(keyEvent.m5765unboximpl());
                                            }

                                            /* renamed from: invoke-ZmokQxo, reason: not valid java name */
                                            public final Boolean m1255invokeZmokQxo(android.view.KeyEvent keyEvent) {
                                                KeyCommand mo1304mapZmokQxo = KeyMapping_androidKt.getPlatformDefaultKeyMapping().mo1304mapZmokQxo(keyEvent);
                                                return Boolean.valueOf(mo1304mapZmokQxo == KeyCommand.COPY || mo1304mapZmokQxo == KeyCommand.CUT);
                                            }
                                        };
                                        startRestartGroup.updateRememberedValue(rememberedValue6);
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    Modifier onPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(semantics$default, (Function1) rememberedValue6);
                                    if (z10) {
                                        companion2 = secureTextFieldController.getFocusChangeModifier();
                                    } else {
                                        companion2 = Modifier.INSTANCE;
                                    }
                                    final Modifier then = onPreviewKeyEvent.then(companion2);
                                    DisableCutCopy(ComposableLambdaKt.rememberComposableLambda(135148049, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$BasicSecureTextField$3
                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                            invoke(composer3, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer3, int i31) {
                                            InputTransformation inputTransformation4;
                                            ComposerKt.sourceInformation(composer3, "C192@10377L875:BasicSecureTextField.kt#423gt5");
                                            if (!composer3.shouldExecute((i31 & 3) != 2, i31 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(135148049, i31, -1, "androidx.compose.foundation.text.BasicSecureTextField.<anonymous> (BasicSecureTextField.kt:192)");
                                            }
                                            if (z10) {
                                                inputTransformation4 = BasicSecureTextFieldKt.then(inputTransformation2, secureTextFieldController.getPasswordInputTransformation());
                                            } else {
                                                inputTransformation4 = inputTransformation2;
                                            }
                                            BasicTextFieldKt.BasicTextField(textFieldState2, then, z8, z9, inputTransformation4, textStyle5, keyboardOptions4, keyboardActionHandler3, TextFieldLineLimits.SingleLine.INSTANCE, function23, mutableInteractionSource3, brush3, codepointTransformation2, null, textFieldDecorator3, scrollState3, true, composer3, 100663296, 1572864, 8192);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, startRestartGroup, 54), startRestartGroup, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    c2 = c3;
                                    composer2 = startRestartGroup;
                                    i23 = i24;
                                    inputTransformation3 = inputTransformation2;
                                    z5 = z8;
                                    z6 = z9;
                                    textStyle3 = textStyle5;
                                    keyboardOptions2 = keyboardOptions4;
                                    keyboardActionHandler2 = keyboardActionHandler3;
                                    function22 = function23;
                                    mutableInteractionSource2 = mutableInteractionSource3;
                                    brush2 = brush3;
                                    textFieldDecorator2 = textFieldDecorator3;
                                    scrollState2 = scrollState3;
                                    modifier3 = companion;
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    function22 = function2;
                                    i23 = i;
                                    c2 = c;
                                    composer2 = startRestartGroup;
                                    z5 = z3;
                                    z6 = z4;
                                    inputTransformation3 = inputTransformation2;
                                    modifier3 = modifier2;
                                    textStyle3 = textStyle2;
                                    keyboardActionHandler2 = keyboardActionHandler;
                                    mutableInteractionSource2 = mutableInteractionSource;
                                    brush2 = brush;
                                    textFieldDecorator2 = textFieldDecorator;
                                    scrollState2 = scrollState;
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup != null) {
                                    endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda5
                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(Object obj, Object obj2) {
                                            Unit BasicSecureTextField_ltb6GB4$lambda$8;
                                            BasicSecureTextField_ltb6GB4$lambda$8 = BasicSecureTextFieldKt.BasicSecureTextField_ltb6GB4$lambda$8(TextFieldState.this, modifier3, z5, z6, inputTransformation3, textStyle3, keyboardOptions2, keyboardActionHandler2, function22, mutableInteractionSource2, brush2, textFieldDecorator2, i23, c2, scrollState2, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                            return BasicSecureTextField_ltb6GB4$lambda$8;
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            i15 = i14;
                            i16 = i4 & 1024;
                            if (i16 != 0) {
                            }
                            i19 = i4 & 2048;
                            if (i19 != 0) {
                            }
                            if ((i3 & 384) == 0) {
                            }
                            int i292 = i18;
                            i20 = i4 & 8192;
                            if (i20 != 0) {
                            }
                            if ((i3 & 24576) == 0) {
                            }
                            i22 = i21;
                            if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 9363) != 9362, i5 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup != null) {
                            }
                        }
                        i13 = i12;
                        i14 = i4 & 512;
                        if (i14 == 0) {
                        }
                        i15 = i14;
                        i16 = i4 & 1024;
                        if (i16 != 0) {
                        }
                        i19 = i4 & 2048;
                        if (i19 != 0) {
                        }
                        if ((i3 & 384) == 0) {
                        }
                        int i2922 = i18;
                        i20 = i4 & 8192;
                        if (i20 != 0) {
                        }
                        if ((i3 & 24576) == 0) {
                        }
                        i22 = i21;
                        if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 9363) != 9362, i5 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                        }
                    }
                    inputTransformation2 = inputTransformation;
                    i9 = i4 & 32;
                    if (i9 != 0) {
                    }
                    i10 = i4 & 64;
                    if (i10 != 0) {
                    }
                    i11 = i4 & 128;
                    if (i11 != 0) {
                    }
                    i12 = i4 & 256;
                    if (i12 != 0) {
                    }
                    i13 = i12;
                    i14 = i4 & 512;
                    if (i14 == 0) {
                    }
                    i15 = i14;
                    i16 = i4 & 1024;
                    if (i16 != 0) {
                    }
                    i19 = i4 & 2048;
                    if (i19 != 0) {
                    }
                    if ((i3 & 384) == 0) {
                    }
                    int i29222 = i18;
                    i20 = i4 & 8192;
                    if (i20 != 0) {
                    }
                    if ((i3 & 24576) == 0) {
                    }
                    i22 = i21;
                    if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 9363) != 9362, i5 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                z4 = z2;
                i8 = i4 & 16;
                int i282 = 8192;
                if (i8 == 0) {
                }
                inputTransformation2 = inputTransformation;
                i9 = i4 & 32;
                if (i9 != 0) {
                }
                i10 = i4 & 64;
                if (i10 != 0) {
                }
                i11 = i4 & 128;
                if (i11 != 0) {
                }
                i12 = i4 & 256;
                if (i12 != 0) {
                }
                i13 = i12;
                i14 = i4 & 512;
                if (i14 == 0) {
                }
                i15 = i14;
                i16 = i4 & 1024;
                if (i16 != 0) {
                }
                i19 = i4 & 2048;
                if (i19 != 0) {
                }
                if ((i3 & 384) == 0) {
                }
                int i292222 = i18;
                i20 = i4 & 8192;
                if (i20 != 0) {
                }
                if ((i3 & 24576) == 0) {
                }
                i22 = i21;
                if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 9363) != 9362, i5 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z3 = z;
            i7 = i4 & 8;
            if (i7 != 0) {
            }
            z4 = z2;
            i8 = i4 & 16;
            int i2822 = 8192;
            if (i8 == 0) {
            }
            inputTransformation2 = inputTransformation;
            i9 = i4 & 32;
            if (i9 != 0) {
            }
            i10 = i4 & 64;
            if (i10 != 0) {
            }
            i11 = i4 & 128;
            if (i11 != 0) {
            }
            i12 = i4 & 256;
            if (i12 != 0) {
            }
            i13 = i12;
            i14 = i4 & 512;
            if (i14 == 0) {
            }
            i15 = i14;
            i16 = i4 & 1024;
            if (i16 != 0) {
            }
            i19 = i4 & 2048;
            if (i19 != 0) {
            }
            if ((i3 & 384) == 0) {
            }
            int i2922222 = i18;
            i20 = i4 & 8192;
            if (i20 != 0) {
            }
            if ((i3 & 24576) == 0) {
            }
            i22 = i21;
            if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 9363) != 9362, i5 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        z3 = z;
        i7 = i4 & 8;
        if (i7 != 0) {
        }
        z4 = z2;
        i8 = i4 & 16;
        int i28222 = 8192;
        if (i8 == 0) {
        }
        inputTransformation2 = inputTransformation;
        i9 = i4 & 32;
        if (i9 != 0) {
        }
        i10 = i4 & 64;
        if (i10 != 0) {
        }
        i11 = i4 & 128;
        if (i11 != 0) {
        }
        i12 = i4 & 256;
        if (i12 != 0) {
        }
        i13 = i12;
        i14 = i4 & 512;
        if (i14 == 0) {
        }
        i15 = i14;
        i16 = i4 & 1024;
        if (i16 != 0) {
        }
        i19 = i4 & 2048;
        if (i19 != 0) {
        }
        if ((i3 & 384) == 0) {
        }
        int i29222222 = i18;
        i20 = i4 & 8192;
        if (i20 != 0) {
        }
        if ((i3 & 24576) == 0) {
        }
        i22 = i21;
        if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 9363) != 9362, i5 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int BasicSecureTextField_ltb6GB4$lambda$4$lambda$3(State state, int i, int i2) {
        return ((Character) state.getValue()).charValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicSecureTextField_ltb6GB4$lambda$6$lambda$5(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContentType(semanticsPropertyReceiver, ContentType.INSTANCE.getPassword());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final InputTransformation then(InputTransformation inputTransformation, InputTransformation inputTransformation2) {
        return inputTransformation == null ? inputTransformation2 : inputTransformation2 == null ? inputTransformation : InputTransformationKt.then(inputTransformation, inputTransformation2);
    }

    private static final void DisableCutCopy(final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(1141174275);
        ComposerKt.sourceInformation(startRestartGroup, "C(DisableCutCopy)N(content)312@14862L7,314@14908L877,335@15790L80:BasicSecureTextField.kt#423gt5");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1141174275, i2, -1, "androidx.compose.foundation.text.DisableCutCopy (BasicSecureTextField.kt:311)");
            }
            ProvidableCompositionLocal<TextToolbar> localTextToolbar = CompositionLocalsKt.getLocalTextToolbar();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localTextToolbar);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            final TextToolbar textToolbar = (TextToolbar) consume;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1451823856, "CC(remember):BasicSecureTextField.kt#9igjgp");
            boolean changed = startRestartGroup.changed(textToolbar);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new TextToolbar() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1
                    private final /* synthetic */ TextToolbar $$delegate_0;

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public TextToolbarStatus getStatus() {
                        return this.$$delegate_0.getStatus();
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void hide() {
                        this.$$delegate_0.hide();
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void showMenu(Rect rect, Function0<Unit> onCopyRequested, Function0<Unit> onPasteRequested, Function0<Unit> onCutRequested, Function0<Unit> onSelectAllRequested) {
                        this.$$delegate_0.showMenu(rect, onCopyRequested, onPasteRequested, onCutRequested, onSelectAllRequested);
                    }

                    {
                        this.$$delegate_0 = TextToolbar.this;
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void showMenu(Rect rect, Function0<Unit> onCopyRequested, Function0<Unit> onPasteRequested, Function0<Unit> onCutRequested, Function0<Unit> onSelectAllRequested, Function0<Unit> onAutofillRequested) {
                        TextToolbar.this.showMenu(rect, null, onPasteRequested, null, onSelectAllRequested, onAutofillRequested);
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalTextToolbar().provides((BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1) rememberedValue), function2, startRestartGroup, ((i2 << 3) & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit DisableCutCopy$lambda$10;
                    DisableCutCopy$lambda$10 = BasicSecureTextFieldKt.DisableCutCopy$lambda$10(Function2.this, i, (Composer) obj, ((Integer) obj2).intValue());
                    return DisableCutCopy$lambda$10;
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use the overload that takes in readOnly parameter.")
    /* renamed from: BasicSecureTextField-Jb9bMDk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1252BasicSecureTextFieldJb9bMDk(final TextFieldState textFieldState, Modifier modifier, boolean z, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, Composer composer, final int i2, final int i3, final int i4) {
        TextFieldState textFieldState2;
        int i5;
        Modifier modifier2;
        int i6;
        boolean z2;
        int i7;
        InputTransformation inputTransformation2;
        int i8;
        TextStyle textStyle2;
        int i9;
        KeyboardOptions keyboardOptions2;
        int i10;
        KeyboardActionHandler keyboardActionHandler2;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        Composer composer2;
        final Function2 function22;
        final int i23;
        final boolean z3;
        final InputTransformation inputTransformation3;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final Modifier modifier3;
        final KeyboardActionHandler keyboardActionHandler3;
        final MutableInteractionSource mutableInteractionSource2;
        final Brush brush2;
        final TextFieldDecorator textFieldDecorator2;
        final char c2;
        ScopeUpdateScope endRestartGroup;
        int i24;
        Modifier modifier4;
        int i25;
        TextStyle textStyle4;
        Composer startRestartGroup = composer.startRestartGroup(1399310985);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicSecureTextField)N(state,modifier,enabled,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,onTextLayout,interactionSource,cursorBrush,decorator,textObfuscationMode:c#foundation.text.input.TextObfuscationMode,textObfuscationCharacter)364@17059L564:BasicSecureTextField.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
            textFieldState2 = textFieldState;
        } else if ((i2 & 6) == 0) {
            textFieldState2 = textFieldState;
            i5 = (startRestartGroup.changed(textFieldState2) ? 4 : 2) | i2;
        } else {
            textFieldState2 = textFieldState;
            i5 = i2;
        }
        int i26 = i4 & 2;
        if (i26 != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                z2 = z;
                i5 |= startRestartGroup.changed(z2) ? 256 : 128;
                i7 = i4 & 8;
                if (i7 != 0) {
                    i5 |= 3072;
                } else if ((i2 & 3072) == 0) {
                    inputTransformation2 = inputTransformation;
                    i5 |= startRestartGroup.changed(inputTransformation2) ? 2048 : 1024;
                    i8 = i4 & 16;
                    if (i8 == 0) {
                        i5 |= 24576;
                    } else if ((i2 & 24576) == 0) {
                        textStyle2 = textStyle;
                        i5 |= startRestartGroup.changed(textStyle2) ? 16384 : 8192;
                        i9 = i4 & 32;
                        if (i9 != 0) {
                            i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            keyboardOptions2 = keyboardOptions;
                        } else {
                            keyboardOptions2 = keyboardOptions;
                            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i5 |= startRestartGroup.changed(keyboardOptions2) ? 131072 : 65536;
                            }
                        }
                        i10 = i4 & 64;
                        if (i10 != 0) {
                            i5 |= 1572864;
                            keyboardActionHandler2 = keyboardActionHandler;
                        } else {
                            keyboardActionHandler2 = keyboardActionHandler;
                            if ((i2 & 1572864) == 0) {
                                i5 |= startRestartGroup.changed(keyboardActionHandler2) ? 1048576 : 524288;
                            }
                        }
                        i11 = i4 & 128;
                        if (i11 != 0) {
                            i5 |= 12582912;
                        } else if ((i2 & 12582912) == 0) {
                            i12 = i11;
                            i5 |= startRestartGroup.changedInstance(function2) ? 8388608 : 4194304;
                            i13 = i4 & 256;
                            if (i13 == 0) {
                                i5 |= 100663296;
                            } else if ((i2 & 100663296) == 0) {
                                i14 = i13;
                                i5 |= startRestartGroup.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                i15 = i4 & 512;
                                if (i15 != 0) {
                                    i5 |= 805306368;
                                } else if ((i2 & 805306368) == 0) {
                                    i16 = i15;
                                    i5 |= startRestartGroup.changed(brush) ? 536870912 : 268435456;
                                    i17 = i4 & 1024;
                                    if (i17 == 0) {
                                        i18 = i3 | 6;
                                    } else if ((i3 & 6) == 0) {
                                        i18 = i3 | ((i3 & 8) == 0 ? startRestartGroup.changed(textFieldDecorator) : startRestartGroup.changedInstance(textFieldDecorator) ? 4 : 2);
                                    } else {
                                        i18 = i3;
                                    }
                                    i19 = i4 & 2048;
                                    if (i19 == 0) {
                                        i18 |= 48;
                                        i20 = i19;
                                    } else if ((i3 & 48) == 0) {
                                        i20 = i19;
                                        i18 |= startRestartGroup.changed(i) ? 32 : 16;
                                    } else {
                                        i20 = i19;
                                    }
                                    int i27 = i18;
                                    i21 = i4 & 4096;
                                    if (i21 == 0) {
                                        i22 = i27 | 384;
                                    } else if ((i3 & 384) == 0) {
                                        i22 = i27 | (startRestartGroup.changed(c) ? 256 : 128);
                                    } else {
                                        i22 = i27;
                                    }
                                    if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 147) != 146, i5 & 1)) {
                                        composer2 = startRestartGroup;
                                        composer2.skipToGroupEnd();
                                        function22 = function2;
                                        i23 = i;
                                        z3 = z2;
                                        inputTransformation3 = inputTransformation2;
                                        textStyle3 = textStyle2;
                                        keyboardOptions3 = keyboardOptions2;
                                        modifier3 = modifier2;
                                        keyboardActionHandler3 = keyboardActionHandler2;
                                        mutableInteractionSource2 = mutableInteractionSource;
                                        brush2 = brush;
                                        textFieldDecorator2 = textFieldDecorator;
                                        c2 = c;
                                    } else {
                                        if (i26 != 0) {
                                            modifier4 = Modifier.INSTANCE;
                                            i24 = i7;
                                        } else {
                                            i24 = i7;
                                            modifier4 = modifier2;
                                        }
                                        boolean z4 = i6 != 0 ? true : z2;
                                        InputTransformation inputTransformation4 = i24 != 0 ? null : inputTransformation2;
                                        if (i8 != 0) {
                                            textStyle4 = TextStyle.INSTANCE.getDefault();
                                            i25 = i9;
                                        } else {
                                            i25 = i9;
                                            textStyle4 = textStyle2;
                                        }
                                        KeyboardOptions secureTextField$foundation_release = i25 != 0 ? KeyboardOptions.INSTANCE.getSecureTextField$foundation_release() : keyboardOptions2;
                                        KeyboardActionHandler keyboardActionHandler4 = i10 != 0 ? null : keyboardActionHandler2;
                                        Function2 function23 = i12 != 0 ? null : function2;
                                        MutableInteractionSource mutableInteractionSource3 = i14 != 0 ? null : mutableInteractionSource;
                                        Brush solidColor = i16 != 0 ? new SolidColor(Color.INSTANCE.m4641getBlack0d7_KjU(), null) : brush;
                                        TextFieldDecorator textFieldDecorator3 = i17 != 0 ? null : textFieldDecorator;
                                        int m1505getRevealLastTypedvTwcZD0 = i20 != 0 ? TextObfuscationMode.INSTANCE.m1505getRevealLastTypedvTwcZD0() : i;
                                        char c3 = i21 != 0 ? (char) 8226 : c;
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1399310985, i5, i22, "androidx.compose.foundation.text.BasicSecureTextField (BasicSecureTextField.kt:363)");
                                        }
                                        int i28 = i5 << 3;
                                        int i29 = i22 << 3;
                                        composer2 = startRestartGroup;
                                        m1254BasicSecureTextFieldltb6GB4(textFieldState2, modifier4, z4, false, inputTransformation4, textStyle4, secureTextField$foundation_release, keyboardActionHandler4, function23, mutableInteractionSource3, solidColor, textFieldDecorator3, m1505getRevealLastTypedvTwcZD0, c3, null, composer2, (i5 & 14) | 3072 | (i5 & 112) | (i5 & 896) | (57344 & i28) | (458752 & i28) | (3670016 & i28) | (29360128 & i28) | (234881024 & i28) | (i28 & 1879048192), ((i5 >> 27) & 14) | (i29 & 112) | (i29 & 896) | (i29 & 7168), 16384);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        modifier3 = modifier4;
                                        z3 = z4;
                                        inputTransformation3 = inputTransformation4;
                                        textStyle3 = textStyle4;
                                        keyboardOptions3 = secureTextField$foundation_release;
                                        keyboardActionHandler3 = keyboardActionHandler4;
                                        function22 = function23;
                                        mutableInteractionSource2 = mutableInteractionSource3;
                                        brush2 = solidColor;
                                        textFieldDecorator2 = textFieldDecorator3;
                                        i23 = m1505getRevealLastTypedvTwcZD0;
                                        c2 = c3;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup == null) {
                                        endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda2
                                            @Override // kotlin.jvm.functions.Function2
                                            public final Object invoke(Object obj, Object obj2) {
                                                Unit BasicSecureTextField_Jb9bMDk$lambda$11;
                                                BasicSecureTextField_Jb9bMDk$lambda$11 = BasicSecureTextFieldKt.BasicSecureTextField_Jb9bMDk$lambda$11(TextFieldState.this, modifier3, z3, inputTransformation3, textStyle3, keyboardOptions3, keyboardActionHandler3, function22, mutableInteractionSource2, brush2, textFieldDecorator2, i23, c2, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                                return BasicSecureTextField_Jb9bMDk$lambda$11;
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                i16 = i15;
                                i17 = i4 & 1024;
                                if (i17 == 0) {
                                }
                                i19 = i4 & 2048;
                                if (i19 == 0) {
                                }
                                int i272 = i18;
                                i21 = i4 & 4096;
                                if (i21 == 0) {
                                }
                                if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 147) != 146, i5 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup == null) {
                                }
                            }
                            i14 = i13;
                            i15 = i4 & 512;
                            if (i15 != 0) {
                            }
                            i16 = i15;
                            i17 = i4 & 1024;
                            if (i17 == 0) {
                            }
                            i19 = i4 & 2048;
                            if (i19 == 0) {
                            }
                            int i2722 = i18;
                            i21 = i4 & 4096;
                            if (i21 == 0) {
                            }
                            if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 147) != 146, i5 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                            }
                        }
                        i12 = i11;
                        i13 = i4 & 256;
                        if (i13 == 0) {
                        }
                        i14 = i13;
                        i15 = i4 & 512;
                        if (i15 != 0) {
                        }
                        i16 = i15;
                        i17 = i4 & 1024;
                        if (i17 == 0) {
                        }
                        i19 = i4 & 2048;
                        if (i19 == 0) {
                        }
                        int i27222 = i18;
                        i21 = i4 & 4096;
                        if (i21 == 0) {
                        }
                        if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 147) != 146, i5 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    textStyle2 = textStyle;
                    i9 = i4 & 32;
                    if (i9 != 0) {
                    }
                    i10 = i4 & 64;
                    if (i10 != 0) {
                    }
                    i11 = i4 & 128;
                    if (i11 != 0) {
                    }
                    i12 = i11;
                    i13 = i4 & 256;
                    if (i13 == 0) {
                    }
                    i14 = i13;
                    i15 = i4 & 512;
                    if (i15 != 0) {
                    }
                    i16 = i15;
                    i17 = i4 & 1024;
                    if (i17 == 0) {
                    }
                    i19 = i4 & 2048;
                    if (i19 == 0) {
                    }
                    int i272222 = i18;
                    i21 = i4 & 4096;
                    if (i21 == 0) {
                    }
                    if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 147) != 146, i5 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                inputTransformation2 = inputTransformation;
                i8 = i4 & 16;
                if (i8 == 0) {
                }
                textStyle2 = textStyle;
                i9 = i4 & 32;
                if (i9 != 0) {
                }
                i10 = i4 & 64;
                if (i10 != 0) {
                }
                i11 = i4 & 128;
                if (i11 != 0) {
                }
                i12 = i11;
                i13 = i4 & 256;
                if (i13 == 0) {
                }
                i14 = i13;
                i15 = i4 & 512;
                if (i15 != 0) {
                }
                i16 = i15;
                i17 = i4 & 1024;
                if (i17 == 0) {
                }
                i19 = i4 & 2048;
                if (i19 == 0) {
                }
                int i2722222 = i18;
                i21 = i4 & 4096;
                if (i21 == 0) {
                }
                if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 147) != 146, i5 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z2 = z;
            i7 = i4 & 8;
            if (i7 != 0) {
            }
            inputTransformation2 = inputTransformation;
            i8 = i4 & 16;
            if (i8 == 0) {
            }
            textStyle2 = textStyle;
            i9 = i4 & 32;
            if (i9 != 0) {
            }
            i10 = i4 & 64;
            if (i10 != 0) {
            }
            i11 = i4 & 128;
            if (i11 != 0) {
            }
            i12 = i11;
            i13 = i4 & 256;
            if (i13 == 0) {
            }
            i14 = i13;
            i15 = i4 & 512;
            if (i15 != 0) {
            }
            i16 = i15;
            i17 = i4 & 1024;
            if (i17 == 0) {
            }
            i19 = i4 & 2048;
            if (i19 == 0) {
            }
            int i27222222 = i18;
            i21 = i4 & 4096;
            if (i21 == 0) {
            }
            if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 147) != 146, i5 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        z2 = z;
        i7 = i4 & 8;
        if (i7 != 0) {
        }
        inputTransformation2 = inputTransformation;
        i8 = i4 & 16;
        if (i8 == 0) {
        }
        textStyle2 = textStyle;
        i9 = i4 & 32;
        if (i9 != 0) {
        }
        i10 = i4 & 64;
        if (i10 != 0) {
        }
        i11 = i4 & 128;
        if (i11 != 0) {
        }
        i12 = i11;
        i13 = i4 & 256;
        if (i13 == 0) {
        }
        i14 = i13;
        i15 = i4 & 512;
        if (i15 != 0) {
        }
        i16 = i15;
        i17 = i4 & 1024;
        if (i17 == 0) {
        }
        i19 = i4 & 2048;
        if (i19 == 0) {
        }
        int i272222222 = i18;
        i21 = i4 & 4096;
        if (i21 == 0) {
        }
        if (startRestartGroup.shouldExecute((i5 & 306783379) == 306783378 || (i22 & 147) != 146, i5 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use the overload that takes in scrollState parameter.")
    /* renamed from: BasicSecureTextField-egD4TGM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void m1253BasicSecureTextFieldegD4TGM(final TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2 function2, MutableInteractionSource mutableInteractionSource, Brush brush, TextFieldDecorator textFieldDecorator, int i, char c, Composer composer, final int i2, final int i3, final int i4) {
        TextFieldState textFieldState2;
        int i5;
        Modifier modifier2;
        int i6;
        boolean z3;
        int i7;
        InputTransformation inputTransformation2;
        int i8;
        TextStyle textStyle2;
        int i9;
        KeyboardOptions keyboardOptions2;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        Composer composer2;
        final boolean z4;
        final KeyboardActionHandler keyboardActionHandler2;
        final Function2 function22;
        final int i23;
        final char c2;
        final boolean z5;
        final InputTransformation inputTransformation3;
        final TextStyle textStyle3;
        final KeyboardOptions keyboardOptions3;
        final Modifier modifier3;
        final MutableInteractionSource mutableInteractionSource2;
        final Brush brush2;
        final TextFieldDecorator textFieldDecorator2;
        ScopeUpdateScope endRestartGroup;
        int i24;
        Modifier modifier4;
        int i25;
        InputTransformation inputTransformation4;
        int i26;
        TextStyle textStyle4;
        Composer startRestartGroup = composer.startRestartGroup(-817513499);
        ComposerKt.sourceInformation(startRestartGroup, "C(BasicSecureTextField)N(state,modifier,enabled,readOnly,inputTransformation,textStyle,keyboardOptions,onKeyboardAction,onTextLayout,interactionSource,cursorBrush,decorator,textObfuscationMode:c#foundation.text.input.TextObfuscationMode,textObfuscationCharacter)421@19275L21,406@18694L609:BasicSecureTextField.kt#423gt5");
        if ((i4 & 1) != 0) {
            i5 = i2 | 6;
            textFieldState2 = textFieldState;
        } else if ((i2 & 6) == 0) {
            textFieldState2 = textFieldState;
            i5 = (startRestartGroup.changed(textFieldState2) ? 4 : 2) | i2;
        } else {
            textFieldState2 = textFieldState;
            i5 = i2;
        }
        int i27 = i4 & 2;
        if (i27 != 0) {
            i5 |= 48;
        } else if ((i2 & 48) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 32 : 16;
            i6 = i4 & 4;
            if (i6 == 0) {
                i5 |= 384;
            } else if ((i2 & 384) == 0) {
                z3 = z;
                i5 |= startRestartGroup.changed(z3) ? 256 : 128;
                i7 = i4 & 16;
                if (i7 != 0) {
                    i5 |= 24576;
                } else if ((i2 & 24576) == 0) {
                    inputTransformation2 = inputTransformation;
                    i5 |= startRestartGroup.changed(inputTransformation2) ? 16384 : 8192;
                    i8 = i4 & 32;
                    if (i8 == 0) {
                        i5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        textStyle2 = textStyle;
                    } else {
                        textStyle2 = textStyle;
                        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            i5 |= startRestartGroup.changed(textStyle2) ? 131072 : 65536;
                        }
                    }
                    i9 = i4 & 64;
                    if (i9 == 0) {
                        i5 |= 1572864;
                        keyboardOptions2 = keyboardOptions;
                    } else {
                        keyboardOptions2 = keyboardOptions;
                        if ((i2 & 1572864) == 0) {
                            i5 |= startRestartGroup.changed(keyboardOptions2) ? 1048576 : 524288;
                        }
                    }
                    i10 = i4 & 128;
                    if (i10 == 0) {
                        i5 |= 12582912;
                    } else if ((i2 & 12582912) == 0) {
                        i5 |= startRestartGroup.changed(keyboardActionHandler) ? 8388608 : 4194304;
                    }
                    i11 = i4 & 256;
                    if (i11 == 0) {
                        i5 |= 100663296;
                    } else if ((i2 & 100663296) == 0) {
                        i12 = i11;
                        i5 |= startRestartGroup.changedInstance(function2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                        i13 = i4 & 512;
                        if (i13 != 0) {
                            i5 |= 805306368;
                        } else if ((i2 & 805306368) == 0) {
                            i14 = i13;
                            i5 |= startRestartGroup.changed(mutableInteractionSource) ? 536870912 : 268435456;
                            i15 = i4 & 1024;
                            if (i15 == 0) {
                                i17 = i3 | 6;
                                i16 = i15;
                            } else if ((i3 & 6) == 0) {
                                i16 = i15;
                                i17 = i3 | (startRestartGroup.changed(brush) ? 4 : 2);
                            } else {
                                i16 = i15;
                                i17 = i3;
                            }
                            i18 = i4 & 2048;
                            if (i18 == 0) {
                                i17 |= 48;
                            } else if ((i3 & 48) == 0) {
                                i17 |= (i3 & 64) == 0 ? startRestartGroup.changed(textFieldDecorator) : startRestartGroup.changedInstance(textFieldDecorator) ? 32 : 16;
                            }
                            int i28 = i17;
                            i19 = i4 & 4096;
                            if (i19 == 0) {
                                i20 = i28 | 384;
                            } else if ((i3 & 384) == 0) {
                                i20 = i28 | (startRestartGroup.changed(i) ? 256 : 128);
                            } else {
                                i20 = i28;
                            }
                            i21 = i4 & 8192;
                            if (i21 == 0) {
                                i22 = i20 | 3072;
                            } else {
                                int i29 = i20;
                                if ((i3 & 3072) == 0) {
                                    i29 |= startRestartGroup.changed(c) ? 2048 : 1024;
                                }
                                i22 = i29;
                            }
                            if (startRestartGroup.shouldExecute((i5 & 306782355) == 306782354 || (i22 & 1171) != 1170, i5 & 1)) {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                z4 = z2;
                                keyboardActionHandler2 = keyboardActionHandler;
                                function22 = function2;
                                i23 = i;
                                c2 = c;
                                z5 = z3;
                                inputTransformation3 = inputTransformation2;
                                textStyle3 = textStyle2;
                                keyboardOptions3 = keyboardOptions2;
                                modifier3 = modifier2;
                                mutableInteractionSource2 = mutableInteractionSource;
                                brush2 = brush;
                                textFieldDecorator2 = textFieldDecorator;
                            } else {
                                if (i27 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                    i24 = i7;
                                } else {
                                    i24 = i7;
                                    modifier4 = modifier2;
                                }
                                boolean z6 = i6 != 0 ? true : z3;
                                boolean z7 = (i4 & 8) != 0 ? false : z2;
                                if (i24 != 0) {
                                    i25 = i21;
                                    inputTransformation4 = null;
                                } else {
                                    i25 = i21;
                                    inputTransformation4 = inputTransformation2;
                                }
                                if (i8 != 0) {
                                    textStyle4 = TextStyle.INSTANCE.getDefault();
                                    i26 = i9;
                                } else {
                                    i26 = i9;
                                    textStyle4 = textStyle2;
                                }
                                KeyboardOptions secureTextField$foundation_release = i26 != 0 ? KeyboardOptions.INSTANCE.getSecureTextField$foundation_release() : keyboardOptions2;
                                KeyboardActionHandler keyboardActionHandler3 = i10 != 0 ? null : keyboardActionHandler;
                                Function2 function23 = i12 != 0 ? null : function2;
                                MutableInteractionSource mutableInteractionSource3 = i14 != 0 ? null : mutableInteractionSource;
                                Brush solidColor = i16 != 0 ? new SolidColor(Color.INSTANCE.m4641getBlack0d7_KjU(), null) : brush;
                                TextFieldDecorator textFieldDecorator3 = i18 != 0 ? null : textFieldDecorator;
                                int m1505getRevealLastTypedvTwcZD0 = i19 != 0 ? TextObfuscationMode.INSTANCE.m1505getRevealLastTypedvTwcZD0() : i;
                                char c3 = i25 != 0 ? (char) 8226 : c;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-817513499, i5, i22, "androidx.compose.foundation.text.BasicSecureTextField (BasicSecureTextField.kt:405)");
                                }
                                composer2 = startRestartGroup;
                                m1254BasicSecureTextFieldltb6GB4(textFieldState2, modifier4, z6, false, inputTransformation4, textStyle4, secureTextField$foundation_release, keyboardActionHandler3, function23, mutableInteractionSource3, solidColor, textFieldDecorator3, m1505getRevealLastTypedvTwcZD0, c3, ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), composer2, (i5 & 14) | 3072 | (i5 & 112) | (i5 & 896) | (57344 & i5) | (458752 & i5) | (3670016 & i5) | (29360128 & i5) | (234881024 & i5) | (1879048192 & i5), i22 & 8190, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                z4 = z7;
                                modifier3 = modifier4;
                                z5 = z6;
                                inputTransformation3 = inputTransformation4;
                                textStyle3 = textStyle4;
                                keyboardOptions3 = secureTextField$foundation_release;
                                keyboardActionHandler2 = keyboardActionHandler3;
                                function22 = function23;
                                mutableInteractionSource2 = mutableInteractionSource3;
                                brush2 = solidColor;
                                textFieldDecorator2 = textFieldDecorator3;
                                i23 = m1505getRevealLastTypedvTwcZD0;
                                c2 = c3;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.BasicSecureTextFieldKt$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit BasicSecureTextField_egD4TGM$lambda$12;
                                        BasicSecureTextField_egD4TGM$lambda$12 = BasicSecureTextFieldKt.BasicSecureTextField_egD4TGM$lambda$12(TextFieldState.this, modifier3, z5, z4, inputTransformation3, textStyle3, keyboardOptions3, keyboardActionHandler2, function22, mutableInteractionSource2, brush2, textFieldDecorator2, i23, c2, i2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                        return BasicSecureTextField_egD4TGM$lambda$12;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        i14 = i13;
                        i15 = i4 & 1024;
                        if (i15 == 0) {
                        }
                        i18 = i4 & 2048;
                        if (i18 == 0) {
                        }
                        int i282 = i17;
                        i19 = i4 & 4096;
                        if (i19 == 0) {
                        }
                        i21 = i4 & 8192;
                        if (i21 == 0) {
                        }
                        if (startRestartGroup.shouldExecute((i5 & 306782355) == 306782354 || (i22 & 1171) != 1170, i5 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i12 = i11;
                    i13 = i4 & 512;
                    if (i13 != 0) {
                    }
                    i14 = i13;
                    i15 = i4 & 1024;
                    if (i15 == 0) {
                    }
                    i18 = i4 & 2048;
                    if (i18 == 0) {
                    }
                    int i2822 = i17;
                    i19 = i4 & 4096;
                    if (i19 == 0) {
                    }
                    i21 = i4 & 8192;
                    if (i21 == 0) {
                    }
                    if (startRestartGroup.shouldExecute((i5 & 306782355) == 306782354 || (i22 & 1171) != 1170, i5 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                inputTransformation2 = inputTransformation;
                i8 = i4 & 32;
                if (i8 == 0) {
                }
                i9 = i4 & 64;
                if (i9 == 0) {
                }
                i10 = i4 & 128;
                if (i10 == 0) {
                }
                i11 = i4 & 256;
                if (i11 == 0) {
                }
                i12 = i11;
                i13 = i4 & 512;
                if (i13 != 0) {
                }
                i14 = i13;
                i15 = i4 & 1024;
                if (i15 == 0) {
                }
                i18 = i4 & 2048;
                if (i18 == 0) {
                }
                int i28222 = i17;
                i19 = i4 & 4096;
                if (i19 == 0) {
                }
                i21 = i4 & 8192;
                if (i21 == 0) {
                }
                if (startRestartGroup.shouldExecute((i5 & 306782355) == 306782354 || (i22 & 1171) != 1170, i5 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z3 = z;
            i7 = i4 & 16;
            if (i7 != 0) {
            }
            inputTransformation2 = inputTransformation;
            i8 = i4 & 32;
            if (i8 == 0) {
            }
            i9 = i4 & 64;
            if (i9 == 0) {
            }
            i10 = i4 & 128;
            if (i10 == 0) {
            }
            i11 = i4 & 256;
            if (i11 == 0) {
            }
            i12 = i11;
            i13 = i4 & 512;
            if (i13 != 0) {
            }
            i14 = i13;
            i15 = i4 & 1024;
            if (i15 == 0) {
            }
            i18 = i4 & 2048;
            if (i18 == 0) {
            }
            int i282222 = i17;
            i19 = i4 & 4096;
            if (i19 == 0) {
            }
            i21 = i4 & 8192;
            if (i21 == 0) {
            }
            if (startRestartGroup.shouldExecute((i5 & 306782355) == 306782354 || (i22 & 1171) != 1170, i5 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 == 0) {
        }
        z3 = z;
        i7 = i4 & 16;
        if (i7 != 0) {
        }
        inputTransformation2 = inputTransformation;
        i8 = i4 & 32;
        if (i8 == 0) {
        }
        i9 = i4 & 64;
        if (i9 == 0) {
        }
        i10 = i4 & 128;
        if (i10 == 0) {
        }
        i11 = i4 & 256;
        if (i11 == 0) {
        }
        i12 = i11;
        i13 = i4 & 512;
        if (i13 != 0) {
        }
        i14 = i13;
        i15 = i4 & 1024;
        if (i15 == 0) {
        }
        i18 = i4 & 2048;
        if (i18 == 0) {
        }
        int i2822222 = i17;
        i19 = i4 & 4096;
        if (i19 == 0) {
        }
        i21 = i4 & 8192;
        if (i21 == 0) {
        }
        if (startRestartGroup.shouldExecute((i5 & 306782355) == 306782354 || (i22 & 1171) != 1170, i5 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }
}
