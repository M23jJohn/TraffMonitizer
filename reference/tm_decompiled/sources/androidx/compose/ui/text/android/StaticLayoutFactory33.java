package androidx.compose.ui.text.android;

import android.graphics.text.LineBreakConfig;
import android.text.StaticLayout;
import androidx.core.content.ContextCompat$$ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: StaticLayoutFactory.android.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactory33;", "", "<init>", "()V", "isFallbackLineSpacingEnabled", "", "layout", "Landroid/text/StaticLayout;", "setLineBreakConfig", "", "builder", "Landroid/text/StaticLayout$Builder;", "lineBreakStyle", "", "lineBreakWordStyle", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
final class StaticLayoutFactory33 {
    public static final StaticLayoutFactory33 INSTANCE = new StaticLayoutFactory33();

    private StaticLayoutFactory33() {
    }

    @JvmStatic
    public static final boolean isFallbackLineSpacingEnabled(StaticLayout layout) {
        boolean isFallbackLineSpacingEnabled;
        isFallbackLineSpacingEnabled = layout.isFallbackLineSpacingEnabled();
        return isFallbackLineSpacingEnabled;
    }

    @JvmStatic
    public static final void setLineBreakConfig(StaticLayout.Builder builder, int lineBreakStyle, int lineBreakWordStyle) {
        LineBreakConfig.Builder lineBreakStyle2;
        LineBreakConfig.Builder lineBreakWordStyle2;
        LineBreakConfig build;
        lineBreakStyle2 = ContextCompat$$ExternalSyntheticApiModelOutline0.m7668m().setLineBreakStyle(lineBreakStyle);
        lineBreakWordStyle2 = lineBreakStyle2.setLineBreakWordStyle(lineBreakWordStyle);
        build = lineBreakWordStyle2.build();
        builder.setLineBreakConfig(build);
    }
}
