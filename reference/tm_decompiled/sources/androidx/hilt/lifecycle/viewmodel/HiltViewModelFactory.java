package androidx.hilt.lifecycle.viewmodel;

import android.content.Context;
import android.content.ContextWrapper;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HiltViewModelFactory.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"HiltViewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "delegateFactory", "create", "hilt-lifecycle-viewmodel_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HiltViewModelFactory {
    public static final ViewModelProvider.Factory create(Context context, ViewModelProvider.Factory delegateFactory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(delegateFactory, "delegateFactory");
        while (context instanceof ContextWrapper) {
            if (!(context instanceof ComponentActivity)) {
                context = ((ContextWrapper) context).getBaseContext();
                Intrinsics.checkNotNullExpressionValue(context, "getBaseContext(...)");
            } else {
                ViewModelProvider.Factory createInternal = dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.createInternal((ComponentActivity) context, delegateFactory);
                Intrinsics.checkNotNullExpressionValue(createInternal, "createInternal(...)");
                return createInternal;
            }
        }
        throw new IllegalStateException("Expected an activity context for creating a HiltViewModelFactory but instead found: " + context);
    }
}
