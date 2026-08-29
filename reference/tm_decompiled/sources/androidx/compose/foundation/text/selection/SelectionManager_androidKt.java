package androidx.compose.foundation.text.selection;

import android.content.Context;
import android.view.KeyEvent;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1;
import androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$2;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyMapping_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifier_androidKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: SelectionManager.android.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a%\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e*\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a\u0014\u0010\u0011\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\tH\u0000¨\u0006\u0013²\u0006\n\u0010\u0014\u001a\u00020\u0015X\u008a\u008e\u0002"}, d2 = {"isCopyKeyEvent", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "isCopyKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "selectionMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/SelectionManager;", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "addSelectionContainerTextContextMenuComponents", "selectionManager", "foundation_release", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SelectionManager_androidKt {
    /* renamed from: isCopyKeyEvent-ZmokQxo, reason: not valid java name */
    public static final boolean m1841isCopyKeyEventZmokQxo(KeyEvent keyEvent) {
        return KeyMapping_androidKt.getPlatformDefaultKeyMapping().mo1304mapZmokQxo(keyEvent) == KeyCommand.COPY;
    }

    public static final Modifier selectionMagnifier(Modifier modifier, SelectionManager selectionManager) {
        return !Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null) ? modifier : ComposedModifierKt.composed$default(modifier, null, new SelectionManager_androidKt$selectionMagnifier$1(selectionManager), 1, null);
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final SelectionManager selectionManager, final ContextMenuState contextMenuState) {
        return new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit contextMenuBuilder$lambda$2;
                contextMenuBuilder$lambda$2 = SelectionManager_androidKt.contextMenuBuilder$lambda$2(SelectionManager.this, contextMenuState, (ContextMenuScope) obj);
                return contextMenuBuilder$lambda$2;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$2(final SelectionManager selectionManager, ContextMenuState contextMenuState, ContextMenuScope contextMenuScope) {
        contextMenuBuilder$lambda$2$selectionItem(contextMenuScope, contextMenuState, TextContextMenuItems.Copy, selectionManager.isNonEmptySelection$foundation_release(), new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit contextMenuBuilder$lambda$2$lambda$0;
                contextMenuBuilder$lambda$2$lambda$0 = SelectionManager_androidKt.contextMenuBuilder$lambda$2$lambda$0(SelectionManager.this);
                return contextMenuBuilder$lambda$2$lambda$0;
            }
        });
        contextMenuBuilder$lambda$2$selectionItem(contextMenuScope, contextMenuState, TextContextMenuItems.SelectAll, !selectionManager.isEntireContainerSelected$foundation_release(), new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit contextMenuBuilder$lambda$2$lambda$1;
                contextMenuBuilder$lambda$2$lambda$1 = SelectionManager_androidKt.contextMenuBuilder$lambda$2$lambda$1(SelectionManager.this);
                return contextMenuBuilder$lambda$2$lambda$1;
            }
        });
        CollectionsKt.listOf((Object[]) new Unit[]{Unit.INSTANCE, Unit.INSTANCE});
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$2$lambda$0(SelectionManager selectionManager) {
        selectionManager.copy$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$2$lambda$1(SelectionManager selectionManager) {
        selectionManager.selectAll$foundation_release();
        return Unit.INSTANCE;
    }

    public static final Modifier addSelectionContainerTextContextMenuComponents(Modifier modifier, final SelectionManager selectionManager) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext(modifier, new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit addSelectionContainerTextContextMenuComponents$lambda$9;
                addSelectionContainerTextContextMenuComponents$lambda$9 = SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$9(SelectionManager.this, (TextContextMenuBuilderScope) obj, (Context) obj2);
                return addSelectionContainerTextContextMenuComponents$lambda$9;
            }
        });
    }

    static /* synthetic */ void addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 8) != 0) {
            function0 = null;
        }
        addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem(textContextMenuBuilderScope, context, textContextMenuItems, z, function0, function02);
    }

    private static final void addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, final Function0<Boolean> function0, final Function0<Unit> function02) {
        ContextMenu_androidKt.textItem(textContextMenuBuilderScope, context.getResources(), textContextMenuItems, z, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem$lambda$3;
                addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem$lambda$3 = SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem$lambda$3(Function0.this, function0, (TextContextMenuSession) obj);
                return addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem$lambda$3(Function0 function0, Function0 function02, TextContextMenuSession textContextMenuSession) {
        function0.invoke();
        if (function02 != null ? ((Boolean) function02.invoke()).booleanValue() : true) {
            textContextMenuSession.close();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$9(final SelectionManager selectionManager, TextContextMenuBuilderScope textContextMenuBuilderScope, final Context context) {
        Pair<AnnotatedString, TextRange> contextTextAndSelection$foundation_release = selectionManager.getContextTextAndSelection$foundation_release();
        PlatformSelectionBehaviors_androidKt.m1788addPlatformTextContextMenuItems71BSaZU(textContextMenuBuilderScope, context, false, contextTextAndSelection$foundation_release != null ? contextTextAndSelection$foundation_release.getFirst() : null, contextTextAndSelection$foundation_release != null ? contextTextAndSelection$foundation_release.getSecond() : null, selectionManager.getPlatformSelectionBehaviors(), new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8;
                addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8 = SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8(SelectionManager.this, context, (TextContextMenuBuilderScope) obj);
                return addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8(final SelectionManager selectionManager, Context context, TextContextMenuBuilderScope textContextMenuBuilderScope) {
        textContextMenuBuilderScope.separator();
        addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem$default(textContextMenuBuilderScope, context, TextContextMenuItems.Copy, selectionManager.isNonEmptySelection$foundation_release(), null, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$4;
                addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$4 = SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$4(SelectionManager.this);
                return addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$4;
            }
        }, 8, null);
        addSelectionContainerTextContextMenuComponents$lambda$9$selectionContainerItem(textContextMenuBuilderScope, context, TextContextMenuItems.SelectAll, !selectionManager.isEntireContainerSelected$foundation_release(), new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$5;
                addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$5 = SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$5(SelectionManager.this);
                return Boolean.valueOf(addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$5);
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$6;
                addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$6 = SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$6(SelectionManager.this);
                return addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$6;
            }
        });
        textContextMenuBuilderScope.separator();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$4(SelectionManager selectionManager) {
        selectionManager.copy$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$5(SelectionManager selectionManager) {
        return (selectionManager.getShowToolbar() && selectionManager.isInTouchMode()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$9$lambda$8$lambda$7$lambda$6(SelectionManager selectionManager) {
        selectionManager.selectAll$foundation_release();
        return Unit.INSTANCE;
    }

    private static final void contextMenuBuilder$lambda$2$selectionItem(ContextMenuScope contextMenuScope, ContextMenuState contextMenuState, TextContextMenuItems textContextMenuItems, boolean z, Function0<Unit> function0) {
        if (z) {
            ContextMenuScope.item$default(contextMenuScope, new ContextMenu_androidKt$TextItem$1(textContextMenuItems), null, false, null, new ContextMenu_androidKt$TextItem$2(function0, contextMenuState), 14, null);
        }
    }
}
