package androidx.compose.foundation.text.contextmenu;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope_androidKt;
import androidx.compose.foundation.text.contextmenu.data.ProcessTextKey;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.ui.text.TextRange;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: ProcessText.android.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"addProcessedTextContextMenuItems", "", "Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;", "context", "Landroid/content/Context;", "editable", "", "text", "", "selection", "Landroidx/compose/ui/text/TextRange;", "addProcessedTextContextMenuItems-UAq72N0", "(Landroidx/compose/foundation/text/contextmenu/builder/TextContextMenuBuilderScope;Landroid/content/Context;ZLjava/lang/CharSequence;J)V", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProcessText_androidKt {
    /* renamed from: addProcessedTextContextMenuItems-UAq72N0, reason: not valid java name */
    public static final void m1437addProcessedTextContextMenuItemsUAq72N0(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, final boolean z, final CharSequence charSequence, final long j) {
        if (!ComposeFoundationFlags.isSmartSelectionEnabled || TextRange.m6842getCollapsedimpl(j) || charSequence.length() == 0) {
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        final Context context2 = context;
        List<ResolveInfo> queryProcessTextActivities = ProcessTextApi23Impl.INSTANCE.queryProcessTextActivities(context2);
        if (queryProcessTextActivities.isEmpty()) {
            return;
        }
        textContextMenuBuilderScope.separator();
        int size = queryProcessTextActivities.size();
        int i = 0;
        while (i < size) {
            final ResolveInfo resolveInfo = queryProcessTextActivities.get(i);
            TextContextMenuBuilderScope_androidKt.item$default(textContextMenuBuilderScope, new ProcessTextKey(i), resolveInfo.loadLabel(packageManager).toString(), 0, new Function1() { // from class: androidx.compose.foundation.text.contextmenu.ProcessText_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit addProcessedTextContextMenuItems_UAq72N0$lambda$1$lambda$0;
                    addProcessedTextContextMenuItems_UAq72N0$lambda$1$lambda$0 = ProcessText_androidKt.addProcessedTextContextMenuItems_UAq72N0$lambda$1$lambda$0(context2, resolveInfo, z, charSequence, j, (TextContextMenuSession) obj);
                    return addProcessedTextContextMenuItems_UAq72N0$lambda$1$lambda$0;
                }
            }, 4, null);
            i++;
            context2 = context;
        }
        textContextMenuBuilderScope.separator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addProcessedTextContextMenuItems_UAq72N0$lambda$1$lambda$0(Context context, ResolveInfo resolveInfo, boolean z, CharSequence charSequence, long j, TextContextMenuSession textContextMenuSession) {
        ProcessTextApi23Impl.INSTANCE.getOnClickProcessTextItem().invoke(context, resolveInfo, Boolean.valueOf(z), charSequence, TextRange.m6836boximpl(j));
        textContextMenuSession.close();
        return Unit.INSTANCE;
    }
}
