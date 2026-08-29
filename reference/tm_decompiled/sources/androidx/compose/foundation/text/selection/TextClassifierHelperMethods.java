package androidx.compose.foundation.text.selection;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.LocaleList;
import android.text.TextUtils;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import androidx.compose.material3.internal.Listener$$ExternalSyntheticApiModelOutline0;
import androidx.compose.ui.text.intl.Locale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;

/* compiled from: PlatformSelectionBehaviors.android.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0011\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0000¢\u0006\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/text/selection/TextClassifierHelperMethods;", "", "<init>", "()V", "createTextClassificationSession", "Landroid/view/textclassifier/TextClassifier;", "context", "Landroid/content/Context;", "selectedTextType", "Landroidx/compose/foundation/text/selection/SelectedTextType;", "toAndroidLocaleList", "Landroid/os/LocaleList;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "hasLegacyAssistItem", "", "Landroid/view/textclassifier/TextClassification;", "hasLegacyAssistItem$foundation_release", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextClassifierHelperMethods {
    public static final int $stable = 0;
    public static final TextClassifierHelperMethods INSTANCE = new TextClassifierHelperMethods();

    /* compiled from: PlatformSelectionBehaviors.android.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SelectedTextType.values().length];
            try {
                iArr[SelectedTextType.EditableText.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SelectedTextType.StaticText.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private TextClassifierHelperMethods() {
    }

    public final TextClassifier createTextClassificationSession(Context context, SelectedTextType selectedTextType) {
        String str;
        TextClassificationContext build;
        TextClassifier createTextClassificationSession;
        TextClassificationManager m3065m = Listener$$ExternalSyntheticApiModelOutline0.m3065m(context.getSystemService(Listener$$ExternalSyntheticApiModelOutline0.m()));
        int i = WhenMappings.$EnumSwitchMapping$0[selectedTextType.ordinal()];
        if (i == 1) {
            str = "edittext";
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            str = "textview";
        }
        Listener$$ExternalSyntheticApiModelOutline0.m$3();
        build = Listener$$ExternalSyntheticApiModelOutline0.m(context.getPackageName(), str).build();
        createTextClassificationSession = m3065m.createTextClassificationSession(build);
        return createTextClassificationSession;
    }

    public final LocaleList toAndroidLocaleList(androidx.compose.ui.text.intl.LocaleList localeList) {
        androidx.compose.ui.text.intl.LocaleList localeList2 = localeList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(localeList2, 10));
        Iterator<Locale> it = localeList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPlatformLocale());
        }
        java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
        return Listener$$ExternalSyntheticApiModelOutline0.m((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length));
    }

    public final boolean hasLegacyAssistItem$foundation_release(TextClassification textClassification) {
        Drawable icon;
        Intent intent;
        View.OnClickListener onClickListener;
        CharSequence label;
        icon = textClassification.getIcon();
        if (icon == null) {
            label = textClassification.getLabel();
            if (TextUtils.isEmpty(label)) {
                return false;
            }
        }
        intent = textClassification.getIntent();
        if (intent != null) {
            return true;
        }
        onClickListener = textClassification.getOnClickListener();
        return onClickListener != null;
    }
}
