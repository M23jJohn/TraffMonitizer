package androidx.compose.ui.platform.coreshims;

import android.view.autofill.AutofillId;
import androidx.core.content.ContextCompat$$ExternalSyntheticApiModelOutline0;

/* loaded from: classes.dex */
public class AutofillIdCompat {
    private final Object mWrappedObj;

    private AutofillIdCompat(AutofillId autofillId) {
        this.mWrappedObj = autofillId;
    }

    public static AutofillIdCompat toAutofillIdCompat(AutofillId autofillId) {
        return new AutofillIdCompat(autofillId);
    }

    public AutofillId toAutofillId() {
        return ContextCompat$$ExternalSyntheticApiModelOutline0.m7674m(this.mWrappedObj);
    }
}
