package androidx.compose.foundation.text.input.internal.selection;

import android.content.Context;
import android.os.Build;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuState_androidKt;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1;
import androidx.compose.foundation.text.MenuItemsAvailability;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifier_androidKt;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors_androidKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: TextFieldSelectionState.android.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aR\u0010\u0000\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u001d\u0010\u000b\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0002\b\u0004H\u0000\u001a\u001c\u0010\u000e\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¨\u0006\u0012"}, d2 = {"contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "itemsAvailability", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "onMenuItemClicked", "Lkotlin/Function2;", "Landroidx/compose/foundation/text/TextContextMenuItems;", "addBasicTextFieldTextContextMenuComponents", "Landroidx/compose/ui/Modifier;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionState_androidKt {
    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final TextFieldSelectionState textFieldSelectionState, final ContextMenuState contextMenuState, final State<MenuItemsAvailability> state, final Function2<? super TextFieldSelectionState, ? super TextContextMenuItems, Unit> function2) {
        return new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit contextMenuBuilder$lambda$1;
                contextMenuBuilder$lambda$1 = TextFieldSelectionState_androidKt.contextMenuBuilder$lambda$1(State.this, contextMenuState, function2, textFieldSelectionState, (ContextMenuScope) obj);
                return contextMenuBuilder$lambda$1;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$1(State state, ContextMenuState contextMenuState, Function2 function2, TextFieldSelectionState textFieldSelectionState, ContextMenuScope contextMenuScope) {
        int m1371unboximpl = ((MenuItemsAvailability) state.getValue()).m1371unboximpl();
        contextMenuBuilder$lambda$1$textFieldItem(contextMenuScope, contextMenuState, function2, textFieldSelectionState, TextContextMenuItems.Cut, MenuItemsAvailability.m1366getCanCutimpl(m1371unboximpl));
        contextMenuBuilder$lambda$1$textFieldItem(contextMenuScope, contextMenuState, function2, textFieldSelectionState, TextContextMenuItems.Copy, MenuItemsAvailability.m1365getCanCopyimpl(m1371unboximpl));
        contextMenuBuilder$lambda$1$textFieldItem(contextMenuScope, contextMenuState, function2, textFieldSelectionState, TextContextMenuItems.Paste, MenuItemsAvailability.m1367getCanPasteimpl(m1371unboximpl));
        contextMenuBuilder$lambda$1$textFieldItem(contextMenuScope, contextMenuState, function2, textFieldSelectionState, TextContextMenuItems.SelectAll, MenuItemsAvailability.m1368getCanSelectAllimpl(m1371unboximpl));
        if (Build.VERSION.SDK_INT >= 26) {
            contextMenuBuilder$lambda$1$textFieldItem(contextMenuScope, contextMenuState, function2, textFieldSelectionState, TextContextMenuItems.Autofill, MenuItemsAvailability.m1364getCanAutofillimpl(m1371unboximpl));
        }
        return Unit.INSTANCE;
    }

    public static final Modifier addBasicTextFieldTextContextMenuComponents(Modifier modifier, final TextFieldSelectionState textFieldSelectionState, final CoroutineScope coroutineScope) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext(modifier, new Function2() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$10;
                addBasicTextFieldTextContextMenuComponents$lambda$10 = TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$10(TextFieldSelectionState.this, coroutineScope, (TextContextMenuBuilderScope) obj, (Context) obj2);
                return addBasicTextFieldTextContextMenuComponents$lambda$10;
            }
        });
    }

    static /* synthetic */ void addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems, boolean z, TextToolbarState textToolbarState, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 16) != 0) {
            textToolbarState = TextToolbarState.None;
        }
        TextToolbarState textToolbarState2 = textToolbarState;
        if ((i & 32) != 0) {
            function0 = null;
        }
        addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3(textContextMenuBuilderScope, context, textFieldSelectionState, textContextMenuItems, z, textToolbarState2, function0, function02);
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, final TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems, boolean z, final TextToolbarState textToolbarState, final Function0<Boolean> function0, final Function0<Unit> function02) {
        ContextMenu_androidKt.textItem(textContextMenuBuilderScope, context.getResources(), textContextMenuItems, z, new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3$lambda$2;
                addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3$lambda$2 = TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3$lambda$2(Function0.this, function0, textFieldSelectionState, textToolbarState, (TextContextMenuSession) obj);
                return addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3$lambda$2(Function0 function0, Function0 function02, TextFieldSelectionState textFieldSelectionState, TextToolbarState textToolbarState, TextContextMenuSession textContextMenuSession) {
        function0.invoke();
        if (function02 != null ? ((Boolean) function02.invoke()).booleanValue() : true) {
            textContextMenuSession.close();
        }
        textFieldSelectionState.updateTextToolbarState(textToolbarState);
        return Unit.INSTANCE;
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem(TextContextMenuBuilderScope textContextMenuBuilderScope, final CoroutineScope coroutineScope, Context context, TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems, boolean z, final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3$default(textContextMenuBuilderScope, context, textFieldSelectionState, textContextMenuItems, z, null, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem$lambda$4;
                addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem$lambda$4 = TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem$lambda$4(CoroutineScope.this, function1);
                return addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem$lambda$4;
            }
        }, 48, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem$lambda$4(CoroutineScope coroutineScope, Function1 function1) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$textFieldSuspendItem$1$1(function1, null), 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$10(final TextFieldSelectionState textFieldSelectionState, final CoroutineScope coroutineScope, TextContextMenuBuilderScope textContextMenuBuilderScope, final Context context) {
        PlatformSelectionBehaviors_androidKt.m1788addPlatformTextContextMenuItems71BSaZU(textContextMenuBuilderScope, context, textFieldSelectionState.getEditable$foundation_release(), textFieldSelectionState.getTextFieldState().getVisualText().getText(), TextRange.m6836boximpl(textFieldSelectionState.getTextFieldState().getVisualText().getSelection()), textFieldSelectionState.getPlatformSelectionBehaviors(), new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9;
                addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9 = TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9(TextFieldSelectionState.this, coroutineScope, context, (TextContextMenuBuilderScope) obj);
                return addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9(final TextFieldSelectionState textFieldSelectionState, CoroutineScope coroutineScope, Context context, TextContextMenuBuilderScope textContextMenuBuilderScope) {
        textContextMenuBuilderScope.separator();
        addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, textFieldSelectionState, TextContextMenuItems.Cut, textFieldSelectionState.canCut(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$1(textFieldSelectionState, null));
        addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, textFieldSelectionState, TextContextMenuItems.Copy, textFieldSelectionState.canCopy(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$2(textFieldSelectionState, null));
        addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, textFieldSelectionState, TextContextMenuItems.Paste, textFieldSelectionState.canPaste(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$3(textFieldSelectionState, null));
        addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3(textContextMenuBuilderScope, context, textFieldSelectionState, TextContextMenuItems.SelectAll, textFieldSelectionState.canSelectAll(), TextToolbarState.Selection, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$5;
                addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$5 = TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$5(TextFieldSelectionState.this);
                return Boolean.valueOf(addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$5);
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$6;
                addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$6 = TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$6(TextFieldSelectionState.this);
                return addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$6;
            }
        });
        if (Build.VERSION.SDK_INT >= 26) {
            addBasicTextFieldTextContextMenuComponents$lambda$10$textFieldItem$3$default(textContextMenuBuilderScope, context, textFieldSelectionState, TextContextMenuItems.Autofill, textFieldSelectionState.canAutofill(), null, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$7;
                    addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$7 = TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$7(TextFieldSelectionState.this);
                    return addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$7;
                }
            }, 48, null);
        }
        textContextMenuBuilderScope.separator();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$5(TextFieldSelectionState textFieldSelectionState) {
        return !textFieldSelectionState.getTextToolbarShown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$6(TextFieldSelectionState textFieldSelectionState) {
        textFieldSelectionState.selectAll();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$10$lambda$9$lambda$8$lambda$7(TextFieldSelectionState textFieldSelectionState) {
        textFieldSelectionState.autofill();
        return Unit.INSTANCE;
    }

    private static final void contextMenuBuilder$lambda$1$textFieldItem(ContextMenuScope contextMenuScope, final ContextMenuState contextMenuState, final Function2<? super TextFieldSelectionState, ? super TextContextMenuItems, Unit> function2, final TextFieldSelectionState textFieldSelectionState, final TextContextMenuItems textContextMenuItems, boolean z) {
        if (z) {
            ContextMenuScope.item$default(contextMenuScope, new ContextMenu_androidKt$TextItem$1(textContextMenuItems), null, false, null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$contextMenuBuilder$lambda$1$textFieldItem$$inlined$TextItem$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    function2.invoke(textFieldSelectionState, textContextMenuItems);
                    ContextMenuState_androidKt.close(ContextMenuState.this);
                }
            }, 14, null);
        }
    }
}
