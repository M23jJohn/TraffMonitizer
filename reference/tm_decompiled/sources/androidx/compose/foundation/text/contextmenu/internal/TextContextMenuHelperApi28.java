package androidx.compose.foundation.text.contextmenu.internal;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.textclassifier.TextClassification;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuTextClassificationItem;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: DefaultTextContextMenuDropdownProvider.android.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0015\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0003¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0003¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/internal/TextContextMenuHelperApi28;", "", "<init>", "()V", "textClassificationItem", "", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "context", "Landroid/content/Context;", "component", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuTextClassificationItem;", "IconBox", "icon", "Landroid/graphics/drawable/Icon;", "(Landroid/graphics/drawable/Icon;Landroidx/compose/runtime/Composer;I)V", "drawable", "Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;Landroidx/compose/runtime/Composer;I)V", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class TextContextMenuHelperApi28 {
    public static final TextContextMenuHelperApi28 INSTANCE = new TextContextMenuHelperApi28();

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IconBox$lambda$4(TextContextMenuHelperApi28 textContextMenuHelperApi28, Icon icon, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(icon, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IconBox$lambda$5(TextContextMenuHelperApi28 textContextMenuHelperApi28, Icon icon, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(icon, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IconBox$lambda$9(TextContextMenuHelperApi28 textContextMenuHelperApi28, Drawable drawable, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(drawable, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private TextContextMenuHelperApi28() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x005d, code lost:
    
        if (r1 != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void textClassificationItem(ContextMenuScope contextMenuScope, final Context context, TextContextMenuTextClassificationItem textContextMenuTextClassificationItem) {
        List actions;
        boolean shouldShowIcon;
        final Drawable icon;
        if (context == null) {
            return;
        }
        int index = textContextMenuTextClassificationItem.getIndex();
        final TextClassification textClassification = textContextMenuTextClassificationItem.getTextClassification();
        if (index < 0) {
            Function2<Composer, Integer, String> function2 = new Function2<Composer, Integer, String>() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$textClassificationItem$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ String invoke(Composer composer, Integer num) {
                    return invoke(composer, num.intValue());
                }

                public final String invoke(Composer composer, int i) {
                    CharSequence label;
                    composer.startReplaceGroup(950061013);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(950061013, i, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:246)");
                    }
                    label = textClassification.getLabel();
                    String valueOf = String.valueOf(label);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    return valueOf;
                }
            };
            icon = textClassification.getIcon();
            ContextMenuScope.item$default(contextMenuScope, function2, null, false, icon != null ? ComposableLambdaKt.composableLambdaInstance(-1123224187, true, new Function3<Color, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$textClassificationItem$2$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Color color, Composer composer, Integer num) {
                    m1456invokeek8zF_U(color.m4625unboximpl(), composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke-ek8zF_U, reason: not valid java name */
                public final void m1456invokeek8zF_U(long j, Composer composer, int i) {
                    ComposerKt.sourceInformation(composer, "CN(color:c#ui.graphics.Color)247@9913L13:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
                    if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1123224187, i, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous>.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:247)");
                    }
                    TextContextMenuHelperApi28.INSTANCE.IconBox(icon, composer, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }) : null, new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit textClassificationItem$lambda$1;
                    textClassificationItem$lambda$1 = TextContextMenuHelperApi28.textClassificationItem$lambda$1(context, textClassification);
                    return textClassificationItem$lambda$1;
                }
            }, 6, null);
            return;
        }
        actions = textClassification.getActions();
        final RemoteAction m = ComponentDialog$$ExternalSyntheticApiModelOutline0.m(actions.get(index));
        boolean z = index == 0;
        Function2<Composer, Integer, String> function22 = new Function2<Composer, Integer, String>() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$textClassificationItem$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ String invoke(Composer composer, Integer num) {
                return invoke(composer, num.intValue());
            }

            public final String invoke(Composer composer, int i) {
                CharSequence title;
                composer.startReplaceGroup(-1376593684);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1376593684, i, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:254)");
                }
                title = m.getTitle();
                String obj = title.toString();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return obj;
            }
        };
        if (!z) {
            shouldShowIcon = m.shouldShowIcon();
        }
        r3 = ComposableLambdaKt.composableLambdaInstance(-1261173016, true, new Function3<Color, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$textClassificationItem$5
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Color color, Composer composer, Integer num) {
                m1457invokeek8zF_U(color.m4625unboximpl(), composer, num.intValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-ek8zF_U, reason: not valid java name */
            public final void m1457invokeek8zF_U(long j, Composer composer, int i) {
                Icon icon2;
                ComposerKt.sourceInformation(composer, "CN(it:c#ui.graphics.Color)257@10329L20:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
                if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1261173016, i, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:257)");
                }
                TextContextMenuHelperApi28 textContextMenuHelperApi28 = TextContextMenuHelperApi28.INSTANCE;
                icon2 = m.getIcon();
                textContextMenuHelperApi28.IconBox(icon2, composer, 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        });
        ContextMenuScope.item$default(contextMenuScope, function22, null, false, r3, new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit textClassificationItem$lambda$2;
                textClassificationItem$lambda$2 = TextContextMenuHelperApi28.textClassificationItem$lambda$2(m);
                return textClassificationItem$lambda$2;
            }
        }, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit textClassificationItem$lambda$1(Context context, TextClassification textClassification) {
        TextClassificationHelperApi28.INSTANCE.sendLegacyIntent(context, textClassification);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit textClassificationItem$lambda$2(RemoteAction remoteAction) {
        PendingIntent actionIntent;
        TextClassificationHelperApi28 textClassificationHelperApi28 = TextClassificationHelperApi28.INSTANCE;
        actionIntent = remoteAction.getActionIntent();
        textClassificationHelperApi28.sendPendingIntent(actionIntent);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void IconBox(final Icon icon, Composer composer, final int i) {
        int i2;
        ScopeUpdateScope endRestartGroup;
        Function2<? super Composer, ? super Integer, Unit> function2;
        Composer startRestartGroup = composer.startRestartGroup(2116504409);
        ComposerKt.sourceInformation(startRestartGroup, "C(IconBox)N(icon)268@10623L7,269@10654L54,270@10727L17:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(icon) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(this) ? 32 : 16;
        }
        if (!startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2116504409, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.IconBox (DefaultTextContextMenuDropdownProvider.android.kt:267)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1853757489, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            boolean changed = startRestartGroup.changed(icon) | startRestartGroup.changed(context);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = icon.loadDrawable(context);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            Drawable drawable = (Drawable) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (drawable != null) {
                IconBox(drawable, startRestartGroup, i2 & 112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    function2 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            Unit IconBox$lambda$4;
                            IconBox$lambda$4 = TextContextMenuHelperApi28.IconBox$lambda$4(TextContextMenuHelperApi28.this, icon, i, (Composer) obj, ((Integer) obj2).intValue());
                            return IconBox$lambda$4;
                        }
                    };
                    endRestartGroup.updateScope(function2);
                }
                return;
            }
        }
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            function2 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit IconBox$lambda$5;
                    IconBox$lambda$5 = TextContextMenuHelperApi28.IconBox$lambda$5(TextContextMenuHelperApi28.this, icon, i, (Composer) obj, ((Integer) obj2).intValue());
                    return IconBox$lambda$5;
                }
            };
            endRestartGroup.updateScope(function2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void IconBox(final Drawable drawable, Composer composer, final int i) {
        int i2;
        Composer startRestartGroup = composer.startRestartGroup(257732500);
        ComposerKt.sourceInformation(startRestartGroup, "C(IconBox)N(drawable)276@10890L217,275@10822L295:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        if ((i & 6) == 0) {
            i2 = (startRestartGroup.changedInstance(drawable) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(257732500, i2, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.IconBox (DefaultTextContextMenuDropdownProvider.android.kt:274)");
            }
            Modifier m930size3ABfNKs = SizeKt.m930size3ABfNKs(Modifier.INSTANCE, ContextMenuSpec.INSTANCE.m431getIconSizeD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1427166163, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(drawable);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit IconBox$lambda$8$lambda$7;
                        IconBox$lambda$8$lambda$7 = TextContextMenuHelperApi28.IconBox$lambda$8$lambda$7(drawable, (DrawScope) obj);
                        return IconBox$lambda$8$lambda$7;
                    }
                };
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            BoxKt.Box(DrawModifierKt.drawBehind(m930size3ABfNKs, (Function1) rememberedValue), startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Unit IconBox$lambda$9;
                    IconBox$lambda$9 = TextContextMenuHelperApi28.IconBox$lambda$9(TextContextMenuHelperApi28.this, drawable, i, (Composer) obj, ((Integer) obj2).intValue());
                    return IconBox$lambda$9;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IconBox$lambda$8$lambda$7(Drawable drawable, DrawScope drawScope) {
        Canvas canvas = drawScope.getDrawContext().getCanvas();
        drawable.setBounds(0, 0, (int) Float.intBitsToFloat((int) (drawScope.mo5121getSizeNHjbRc() >> 32)), (int) Float.intBitsToFloat((int) (drawScope.mo5121getSizeNHjbRc() & 4294967295L)));
        drawable.draw(AndroidCanvas_androidKt.getNativeCanvas(canvas));
        return Unit.INSTANCE;
    }
}
