package androidx.core.graphics;

import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;

/* loaded from: classes3.dex */
public class TypefaceCompatApi31Impl extends TypefaceCompatApi29Impl {
    private static final String TAG = "TypefaceCompatApi31Impl";

    private static Typeface getSystemFontFamily(String str) {
        Typeface create = Typeface.create(str, 0);
        Typeface create2 = Typeface.create(Typeface.DEFAULT, 0);
        if (create == null || create.equals(create2)) {
            return null;
        }
        return create;
    }

    @Override // androidx.core.graphics.TypefaceCompatApi29Impl
    protected Font getFontFromSystemFont(FontsContractCompat.FontInfo fontInfo) {
        Typeface systemFontFamily;
        Font guessPrimaryFont;
        String systemFont = fontInfo.getSystemFont();
        if (systemFont == null || (systemFontFamily = getSystemFontFamily(systemFont)) == null || (guessPrimaryFont = TypefaceCompat.guessPrimaryFont(systemFontFamily)) == null) {
            return null;
        }
        if (TextUtils.isEmpty(fontInfo.getVariationSettings())) {
            return guessPrimaryFont;
        }
        try {
            return new Font.Builder(guessPrimaryFont).setFontVariationSettings(fontInfo.getVariationSettings()).build();
        } catch (IOException unused) {
            Log.e(TAG, "Failed to clone Font instance. Fall back to provider font.");
            return null;
        }
    }
}
