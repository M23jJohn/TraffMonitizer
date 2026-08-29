package androidx.compose.foundation.text.input.internal;

import android.os.Build;
import android.view.inputmethod.EditorInfo;
import androidx.compose.foundation.text.handwriting.StylusHandwriting_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.core.view.inputmethod.EditorInfoCompat;
import kotlin.Metadata;

/* compiled from: EditorInfo.android.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¨\u0006\u0013"}, d2 = {"update", "", "Landroid/view/inputmethod/EditorInfo;", "text", "", "selection", "Landroidx/compose/ui/text/TextRange;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "contentMimeTypes", "", "", "update-pLxbY9I", "(Landroid/view/inputmethod/EditorInfo;Ljava/lang/CharSequence;JLandroidx/compose/ui/text/input/ImeOptions;[Ljava/lang/String;)V", "hasFlag", "", "bits", "", "flag", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EditorInfo_androidKt {
    private static final boolean hasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    /* renamed from: update-pLxbY9I$default, reason: not valid java name */
    public static /* synthetic */ void m1535updatepLxbY9I$default(EditorInfo editorInfo, CharSequence charSequence, long j, ImeOptions imeOptions, String[] strArr, int i, Object obj) {
        if ((i & 8) != 0) {
            strArr = null;
        }
        m1534updatepLxbY9I(editorInfo, charSequence, j, imeOptions, strArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01bb  */
    /* renamed from: update-pLxbY9I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m1534updatepLxbY9I(EditorInfo editorInfo, CharSequence charSequence, long j, ImeOptions imeOptions, String[] strArr) {
        String privateImeOptions;
        int imeAction = imeOptions.getImeAction();
        int i = 3;
        int i2 = 6;
        if (ImeAction.m7020equalsimpl0(imeAction, ImeAction.INSTANCE.m7033getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i2 = 0;
            }
        } else if (ImeAction.m7020equalsimpl0(imeAction, ImeAction.INSTANCE.m7037getNoneeUduSuo())) {
            i2 = 1;
        } else if (ImeAction.m7020equalsimpl0(imeAction, ImeAction.INSTANCE.m7035getGoeUduSuo())) {
            i2 = 2;
        } else if (ImeAction.m7020equalsimpl0(imeAction, ImeAction.INSTANCE.m7036getNexteUduSuo())) {
            i2 = 5;
        } else if (ImeAction.m7020equalsimpl0(imeAction, ImeAction.INSTANCE.m7038getPreviouseUduSuo())) {
            i2 = 7;
        } else if (ImeAction.m7020equalsimpl0(imeAction, ImeAction.INSTANCE.m7039getSearcheUduSuo())) {
            i2 = 3;
        } else if (ImeAction.m7020equalsimpl0(imeAction, ImeAction.INSTANCE.m7040getSendeUduSuo())) {
            i2 = 4;
        } else if (!ImeAction.m7020equalsimpl0(imeAction, ImeAction.INSTANCE.m7034getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction".toString());
        }
        editorInfo.imeOptions = i2;
        PlatformImeOptions platformImeOptions = imeOptions.getPlatformImeOptions();
        if (platformImeOptions != null && (privateImeOptions = platformImeOptions.getPrivateImeOptions()) != null) {
            editorInfo.privateImeOptions = privateImeOptions;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleListHelper.INSTANCE.setHintLocales(editorInfo, imeOptions.getHintLocales());
        }
        int keyboardType = imeOptions.getKeyboardType();
        if (!KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7094getTextPjHm6EE())) {
            if (KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7087getAsciiPjHm6EE())) {
                editorInfo.imeOptions |= Integer.MIN_VALUE;
            } else {
                if (KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7090getNumberPjHm6EE())) {
                    i = 2;
                } else if (!KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7093getPhonePjHm6EE())) {
                    if (KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7096getUriPjHm6EE())) {
                        i = 17;
                    } else if (KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7089getEmailPjHm6EE())) {
                        i = 33;
                    } else if (KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7092getPasswordPjHm6EE())) {
                        i = 129;
                    } else if (KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7091getNumberPasswordPjHm6EE())) {
                        i = 18;
                    } else {
                        if (!KeyboardType.m7073equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7088getDecimalPjHm6EE())) {
                            throw new IllegalStateException("Invalid Keyboard Type".toString());
                        }
                        i = 8194;
                    }
                }
                editorInfo.inputType = i;
                if (!imeOptions.getSingleLine() && hasFlag(editorInfo.inputType, 1)) {
                    editorInfo.inputType |= 131072;
                    if (ImeAction.m7020equalsimpl0(imeOptions.getImeAction(), ImeAction.INSTANCE.m7033getDefaulteUduSuo())) {
                        editorInfo.imeOptions |= 1073741824;
                    }
                }
                if (hasFlag(editorInfo.inputType, 1)) {
                    int capitalization = imeOptions.getCapitalization();
                    if (KeyboardCapitalization.m7056equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m7065getCharactersIUNYP9k())) {
                        editorInfo.inputType |= 4096;
                    } else if (KeyboardCapitalization.m7056equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m7069getWordsIUNYP9k())) {
                        editorInfo.inputType |= 8192;
                    } else if (KeyboardCapitalization.m7056equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m7067getSentencesIUNYP9k())) {
                        editorInfo.inputType |= 16384;
                    }
                    if (imeOptions.getAutoCorrect()) {
                        editorInfo.inputType |= 32768;
                    }
                }
                editorInfo.initialSelStart = TextRange.m6848getStartimpl(j);
                editorInfo.initialSelEnd = TextRange.m6843getEndimpl(j);
                EditorInfoCompat.setInitialSurroundingText(editorInfo, charSequence);
                if (strArr != null) {
                    EditorInfoCompat.setContentMimeTypes(editorInfo, strArr);
                }
                editorInfo.imeOptions |= 33554432;
                if (!StylusHandwriting_androidKt.isStylusHandwritingSupported() && !KeyboardType.m7073equalsimpl0(imeOptions.getKeyboardType(), KeyboardType.INSTANCE.m7092getPasswordPjHm6EE()) && !KeyboardType.m7073equalsimpl0(imeOptions.getKeyboardType(), KeyboardType.INSTANCE.m7091getNumberPasswordPjHm6EE())) {
                    EditorInfoCompat.setStylusHandwritingEnabled(editorInfo, true);
                    EditorInfoApi34.INSTANCE.setHandwritingGestures(editorInfo);
                    return;
                } else {
                    EditorInfoCompat.setStylusHandwritingEnabled(editorInfo, false);
                }
            }
        }
        i = 1;
        editorInfo.inputType = i;
        if (!imeOptions.getSingleLine()) {
            editorInfo.inputType |= 131072;
            if (ImeAction.m7020equalsimpl0(imeOptions.getImeAction(), ImeAction.INSTANCE.m7033getDefaulteUduSuo())) {
            }
        }
        if (hasFlag(editorInfo.inputType, 1)) {
        }
        editorInfo.initialSelStart = TextRange.m6848getStartimpl(j);
        editorInfo.initialSelEnd = TextRange.m6843getEndimpl(j);
        EditorInfoCompat.setInitialSurroundingText(editorInfo, charSequence);
        if (strArr != null) {
        }
        editorInfo.imeOptions |= 33554432;
        if (!StylusHandwriting_androidKt.isStylusHandwritingSupported()) {
        }
        EditorInfoCompat.setStylusHandwritingEnabled(editorInfo, false);
    }
}
