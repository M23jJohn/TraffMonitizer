package androidx.compose.ui.autofill;

import android.util.Log;
import android.util.SparseArray;
import android.view.ViewStructure;
import android.view.autofill.AutofillValue;
import androidx.compose.material3.internal.Listener$$ExternalSyntheticApiModelOutline0;
import androidx.compose.ui.geometry.Rect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;

/* compiled from: AndroidAutofill.android.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0001\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0001¨\u0006\t"}, d2 = {"populateViewStructure", "", "Landroidx/compose/ui/autofill/AndroidAutofill;", "root", "Landroid/view/ViewStructure;", "performAutofill", "values", "Landroid/util/SparseArray;", "Landroid/view/autofill/AutofillValue;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidAutofill_androidKt {
    public static final void populateViewStructure(AndroidAutofill androidAutofill, ViewStructure viewStructure) {
        if (androidAutofill.getAutofillTree().getChildren().isEmpty()) {
            return;
        }
        int addChildCount = AutofillApi26Helper.INSTANCE.addChildCount(viewStructure, androidAutofill.getAutofillTree().getChildren().size());
        for (Map.Entry<Integer, AutofillNode> entry : androidAutofill.getAutofillTree().getChildren().entrySet()) {
            int intValue = entry.getKey().intValue();
            AutofillNode value = entry.getValue();
            ViewStructure newChild = AutofillApi26Helper.INSTANCE.newChild(viewStructure, addChildCount);
            AutofillApi26Helper.INSTANCE.setAutofillId(newChild, androidAutofill.getRootAutofillId(), intValue);
            AutofillApi26Helper.INSTANCE.setId(newChild, intValue, androidAutofill.getView().getContext().getPackageName(), null, null);
            AutofillApi26Helper.INSTANCE.setAutofillType(newChild, ContentDataType_androidKt.getDataType(ContentDataType.INSTANCE.getText()));
            AutofillApi26Helper autofillApi26Helper = AutofillApi26Helper.INSTANCE;
            List<AutofillType> autofillTypes = value.getAutofillTypes();
            ArrayList arrayList = new ArrayList(autofillTypes.size());
            int size = autofillTypes.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(AndroidAutofillType_androidKt.getAndroidType(autofillTypes.get(i)));
            }
            autofillApi26Helper.setAutofillHints(newChild, (String[]) arrayList.toArray(new String[0]));
            Rect boundingBox = value.getBoundingBox();
            if (boundingBox == null) {
                Log.w("Autofill Warning", "Bounding box not set.\n                        Did you call perform autofillTree before the component was positioned? ");
            } else {
                int round = Math.round(boundingBox.getLeft());
                int round2 = Math.round(boundingBox.getTop());
                int round3 = Math.round(boundingBox.getRight());
                AutofillApi26Helper.INSTANCE.setDimens(newChild, round, round2, 0, 0, round3 - round, Math.round(boundingBox.getBottom()) - round2);
            }
            addChildCount++;
        }
    }

    public static final void performAutofill(AndroidAutofill androidAutofill, SparseArray<AutofillValue> sparseArray) {
        if (androidAutofill.getAutofillTree().getChildren().isEmpty()) {
            return;
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            AutofillValue m3062m = Listener$$ExternalSyntheticApiModelOutline0.m3062m((Object) sparseArray.get(keyAt));
            if (AutofillApi26Helper.INSTANCE.isText(m3062m)) {
                androidAutofill.getAutofillTree().performAutofill(keyAt, AutofillApi26Helper.INSTANCE.textValue(m3062m).toString());
            } else {
                if (AutofillApi26Helper.INSTANCE.isDate(m3062m)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for date");
                }
                if (AutofillApi26Helper.INSTANCE.isList(m3062m)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for list");
                }
                if (AutofillApi26Helper.INSTANCE.isToggle(m3062m)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541:  Add onFill() callback for toggle");
                }
            }
        }
    }
}
