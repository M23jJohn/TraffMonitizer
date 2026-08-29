package androidx.compose.ui.text.android;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: StaticLayoutFactory.android.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactoryDefault;", "Landroidx/compose/ui/text/android/StaticLayoutFactoryImpl;", "<init>", "()V", "create", "Landroid/text/StaticLayout;", "params", "Landroidx/compose/ui/text/android/StaticLayoutParams;", "isFallbackLineSpacingEnabled", "", "layout", "useFallbackLineSpacing", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
final class StaticLayoutFactoryDefault implements StaticLayoutFactoryImpl {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isInitialized;
    private static Constructor<StaticLayout> staticLayoutConstructor;

    @Override // androidx.compose.ui.text.android.StaticLayoutFactoryImpl
    public boolean isFallbackLineSpacingEnabled(StaticLayout layout, boolean useFallbackLineSpacing) {
        return false;
    }

    /* compiled from: StaticLayoutFactory.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactoryDefault$Companion;", "", "<init>", "()V", "isInitialized", "", "staticLayoutConstructor", "Ljava/lang/reflect/Constructor;", "Landroid/text/StaticLayout;", "getStaticLayoutConstructor", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Constructor<StaticLayout> getStaticLayoutConstructor() {
            if (StaticLayoutFactoryDefault.isInitialized) {
                return StaticLayoutFactoryDefault.staticLayoutConstructor;
            }
            StaticLayoutFactoryDefault.isInitialized = true;
            try {
                Class cls = Integer.TYPE;
                Class cls2 = Float.TYPE;
                StaticLayoutFactoryDefault.staticLayoutConstructor = StaticLayout.class.getConstructor(CharSequence.class, cls, cls, TextPaint.class, cls, Layout.Alignment.class, TextDirectionHeuristic.class, cls2, cls2, Boolean.TYPE, TextUtils.TruncateAt.class, cls, cls);
            } catch (NoSuchMethodException unused) {
                StaticLayoutFactoryDefault.staticLayoutConstructor = null;
                Log.e("StaticLayoutFactory", "unable to collect necessary constructor.");
            }
            return StaticLayoutFactoryDefault.staticLayoutConstructor;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00b9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00ba  */
    @Override // androidx.compose.ui.text.android.StaticLayoutFactoryImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public StaticLayout create(StaticLayoutParams params) {
        Constructor<StaticLayout> constructor;
        StaticLayout staticLayout;
        Constructor staticLayoutConstructor2 = INSTANCE.getStaticLayoutConstructor();
        if (staticLayoutConstructor2 != null) {
            try {
                constructor = null;
            } catch (IllegalAccessException unused) {
                constructor = null;
            } catch (InstantiationException unused2) {
                constructor = null;
            } catch (InvocationTargetException unused3) {
                constructor = null;
            }
            try {
                staticLayout = (StaticLayout) staticLayoutConstructor2.newInstance(params.getText(), Integer.valueOf(params.getStart()), Integer.valueOf(params.getEnd()), params.getPaint(), Integer.valueOf(params.getWidth()), params.getAlignment(), params.getTextDir(), Float.valueOf(params.getLineSpacingMultiplier()), Float.valueOf(params.getLineSpacingExtra()), Boolean.valueOf(params.getIncludePadding()), params.getEllipsize(), Integer.valueOf(params.getEllipsizedWidth()), Integer.valueOf(params.getMaxLines()));
            } catch (IllegalAccessException unused4) {
                staticLayoutConstructor = constructor;
                Log.e("StaticLayoutFactory", "unable to call constructor");
                staticLayout = constructor;
                if (staticLayout != 0) {
                }
            } catch (InstantiationException unused5) {
                staticLayoutConstructor = constructor;
                Log.e("StaticLayoutFactory", "unable to call constructor");
                staticLayout = constructor;
                if (staticLayout != 0) {
                }
            } catch (InvocationTargetException unused6) {
                staticLayoutConstructor = constructor;
                Log.e("StaticLayoutFactory", "unable to call constructor");
                staticLayout = constructor;
                if (staticLayout != 0) {
                }
            }
            return staticLayout != 0 ? staticLayout : new StaticLayout(params.getText(), params.getStart(), params.getEnd(), params.getPaint(), params.getWidth(), params.getAlignment(), params.getLineSpacingMultiplier(), params.getLineSpacingExtra(), params.getIncludePadding(), params.getEllipsize(), params.getEllipsizedWidth());
        }
        constructor = null;
        staticLayout = constructor;
        if (staticLayout != 0) {
        }
    }
}
