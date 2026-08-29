package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* compiled from: ExpressiveNavigationBar.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\b\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\tR\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/material3/ExpressiveNavigationBarItemDefaults;", "", "()V", "defaultExpressiveNavigationBarItemColors", "Landroidx/compose/material3/NavigationItemColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultExpressiveNavigationBarItemColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/NavigationItemColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/NavigationItemColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ExpressiveNavigationBarItemDefaults {
    public static final int $stable = 0;
    public static final ExpressiveNavigationBarItemDefaults INSTANCE = new ExpressiveNavigationBarItemDefaults();

    private ExpressiveNavigationBarItemDefaults() {
    }

    public final NavigationItemColors colors(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1959617551, "C(colors)272@11438L11:ExpressiveNavigationBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1959617551, i, -1, "androidx.compose.material3.ExpressiveNavigationBarItemDefaults.colors (ExpressiveNavigationBar.kt:272)");
        }
        NavigationItemColors defaultExpressiveNavigationBarItemColors$material3_release = getDefaultExpressiveNavigationBarItemColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return defaultExpressiveNavigationBarItemColors$material3_release;
    }

    public final NavigationItemColors getDefaultExpressiveNavigationBarItemColors$material3_release(ColorScheme colorScheme) {
        ColorSchemeKeyTokens colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2;
        ColorSchemeKeyTokens colorSchemeKeyTokens3;
        ColorSchemeKeyTokens colorSchemeKeyTokens4;
        ColorSchemeKeyTokens colorSchemeKeyTokens5;
        ColorSchemeKeyTokens colorSchemeKeyTokens6;
        ColorSchemeKeyTokens colorSchemeKeyTokens7;
        NavigationItemColors defaultExpressiveNavigationBarItemColorsCached = colorScheme.getDefaultExpressiveNavigationBarItemColorsCached();
        if (defaultExpressiveNavigationBarItemColorsCached != null) {
            return defaultExpressiveNavigationBarItemColorsCached;
        }
        colorSchemeKeyTokens = ExpressiveNavigationBarKt.ActiveIconColor;
        long fromToken = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens);
        colorSchemeKeyTokens2 = ExpressiveNavigationBarKt.ActiveLabelTextColor;
        long fromToken2 = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens2);
        colorSchemeKeyTokens3 = ExpressiveNavigationBarKt.ActiveIndicatorColor;
        long fromToken3 = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens3);
        colorSchemeKeyTokens4 = ExpressiveNavigationBarKt.InactiveIconColor;
        long fromToken4 = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens4);
        colorSchemeKeyTokens5 = ExpressiveNavigationBarKt.InactiveLabelTextColor;
        long fromToken5 = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens5);
        colorSchemeKeyTokens6 = ExpressiveNavigationBarKt.InactiveIconColor;
        long m4614copywmQWz5c$default = Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens6), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
        colorSchemeKeyTokens7 = ExpressiveNavigationBarKt.InactiveLabelTextColor;
        NavigationItemColors navigationItemColors = new NavigationItemColors(fromToken, fromToken2, fromToken3, fromToken4, fromToken5, m4614copywmQWz5c$default, Color.m4614copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens7), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultExpressiveNavigationBarItemColorsCached$material3_release(navigationItemColors);
        return navigationItemColors;
    }
}
