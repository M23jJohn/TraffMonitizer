package androidx.compose.foundation.text.contextmenu.internal;

import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function5;

/* compiled from: DefaultTextContextMenuDropdownProvider.android.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt {
    public static final ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt INSTANCE = new ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt();
    private static Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> lambda$129995601 = ComposableLambdaKt.composableLambdaInstance(129995601, false, new Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt$lambda$129995601$1
        @Override // kotlin.jvm.functions.Function5
        public /* bridge */ /* synthetic */ Unit invoke(TextContextMenuSession textContextMenuSession, TextContextMenuDataProvider textContextMenuDataProvider, Function0<? extends LayoutCoordinates> function0, Composer composer, Integer num) {
            invoke(textContextMenuSession, textContextMenuDataProvider, function0, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(TextContextMenuSession textContextMenuSession, TextContextMenuDataProvider textContextMenuDataProvider, Function0<? extends LayoutCoordinates> function0, Composer composer, int i) {
            int i2;
            ComposerKt.sourceInformation(composer, "CN(session,dataProvider,anchorLayoutCoordinates)75@3942L63:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
            if ((i & 6) == 0) {
                i2 = ((i & 8) == 0 ? composer.changed(textContextMenuSession) : composer.changedInstance(textContextMenuSession) ? 4 : 2) | i;
            } else {
                i2 = i;
            }
            if ((i & 48) == 0) {
                i2 |= (i & 64) == 0 ? composer.changed(textContextMenuDataProvider) : composer.changedInstance(textContextMenuDataProvider) ? 32 : 16;
            }
            if ((i & 384) == 0) {
                i2 |= composer.changedInstance(function0) ? 256 : 128;
            }
            if (composer.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(129995601, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda$129995601.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:75)");
                }
                DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu(textContextMenuSession, textContextMenuDataProvider, function0, composer, i2 & 1022);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });
    private static Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> lambda$636288403 = ComposableLambdaKt.composableLambdaInstance(636288403, false, new Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt$lambda$636288403$1
        @Override // kotlin.jvm.functions.Function5
        public /* bridge */ /* synthetic */ Unit invoke(TextContextMenuSession textContextMenuSession, TextContextMenuDataProvider textContextMenuDataProvider, Function0<? extends LayoutCoordinates> function0, Composer composer, Integer num) {
            invoke(textContextMenuSession, textContextMenuDataProvider, function0, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(TextContextMenuSession textContextMenuSession, TextContextMenuDataProvider textContextMenuDataProvider, Function0<? extends LayoutCoordinates> function0, Composer composer, int i) {
            int i2;
            ComposerKt.sourceInformation(composer, "CN(session,dataProvider,anchorLayoutCoordinates)90@4405L63:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
            if ((i & 6) == 0) {
                i2 = ((i & 8) == 0 ? composer.changed(textContextMenuSession) : composer.changedInstance(textContextMenuSession) ? 4 : 2) | i;
            } else {
                i2 = i;
            }
            if ((i & 48) == 0) {
                i2 |= (i & 64) == 0 ? composer.changed(textContextMenuDataProvider) : composer.changedInstance(textContextMenuDataProvider) ? 32 : 16;
            }
            if ((i & 384) == 0) {
                i2 |= composer.changedInstance(function0) ? 256 : 128;
            }
            if (composer.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(636288403, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda$636288403.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:90)");
                }
                DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu(textContextMenuSession, textContextMenuDataProvider, function0, composer, i2 & 1022);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda$-1357803046, reason: not valid java name */
    private static Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> f2lambda$1357803046 = ComposableLambdaKt.composableLambdaInstance(-1357803046, false, new Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt$lambda$-1357803046$1
        @Override // kotlin.jvm.functions.Function5
        public /* bridge */ /* synthetic */ Unit invoke(TextContextMenuSession textContextMenuSession, TextContextMenuDataProvider textContextMenuDataProvider, Function0<? extends LayoutCoordinates> function0, Composer composer, Integer num) {
            invoke(textContextMenuSession, textContextMenuDataProvider, function0, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(TextContextMenuSession textContextMenuSession, TextContextMenuDataProvider textContextMenuDataProvider, Function0<? extends LayoutCoordinates> function0, Composer composer, int i) {
            int i2;
            ComposerKt.sourceInformation(composer, "CN(session,dataProvider,anchorLayoutCoordinates)99@4699L63:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
            if ((i & 6) == 0) {
                i2 = ((i & 8) == 0 ? composer.changed(textContextMenuSession) : composer.changedInstance(textContextMenuSession) ? 4 : 2) | i;
            } else {
                i2 = i;
            }
            if ((i & 48) == 0) {
                i2 |= (i & 64) == 0 ? composer.changed(textContextMenuDataProvider) : composer.changedInstance(textContextMenuDataProvider) ? 32 : 16;
            }
            if ((i & 384) == 0) {
                i2 |= composer.changedInstance(function0) ? 256 : 128;
            }
            if (composer.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1357803046, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda$-1357803046.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:99)");
                }
                DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu(textContextMenuSession, textContextMenuDataProvider, function0, composer, i2 & 1022);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* renamed from: getLambda$-1357803046$foundation_release, reason: not valid java name */
    public final Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> m1440getLambda$1357803046$foundation_release() {
        return f2lambda$1357803046;
    }

    public final Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> getLambda$129995601$foundation_release() {
        return lambda$129995601;
    }

    public final Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> getLambda$636288403$foundation_release() {
        return lambda$636288403;
    }
}
