package androidx.compose.foundation.text.selection;

import android.content.Context;
import android.os.Build;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1;
import androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$2;
import androidx.compose.foundation.text.MenuItemsAvailability;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifier_androidKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: TextFieldSelectionManager.android.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u001c\u0010\b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0000\u001a3\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f*\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0015²\u0006\n\u0010\u0016\u001a\u00020\u0017X\u008a\u008e\u0002"}, d2 = {"isShiftPressed", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "textFieldMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "addBasicTextFieldTextContextMenuComponents", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "contextMenuState", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "itemsAvailability", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "foundation_release", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionManager_androidKt {
    public static final boolean isShiftPressed(PointerEvent pointerEvent) {
        return false;
    }

    public static final Modifier textFieldMagnifier(Modifier modifier, TextFieldSelectionManager textFieldSelectionManager) {
        return !Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null) ? modifier : ComposedModifierKt.composed$default(modifier, null, new TextFieldSelectionManager_androidKt$textFieldMagnifier$1(textFieldSelectionManager), 1, null);
    }

    public static final Modifier addBasicTextFieldTextContextMenuComponents(Modifier modifier, final TextFieldSelectionManager textFieldSelectionManager, final CoroutineScope coroutineScope) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext(modifier, new Function2() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$8;
                addBasicTextFieldTextContextMenuComponents$lambda$8 = TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$8(TextFieldSelectionManager.this, coroutineScope, (TextContextMenuBuilderScope) obj, (Context) obj2);
                return addBasicTextFieldTextContextMenuComponents$lambda$8;
            }
        });
    }

    static /* synthetic */ void addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 8) != 0) {
            function0 = null;
        }
        addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem(textContextMenuBuilderScope, context, textContextMenuItems, z, function0, function02);
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, final Function0<Boolean> function0, final Function0<Unit> function02) {
        ContextMenu_androidKt.textItem(textContextMenuBuilderScope, context.getResources(), textContextMenuItems, z, new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem$lambda$0;
                addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem$lambda$0 = TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem$lambda$0(Function0.this, function0, (TextContextMenuSession) obj);
                return addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem$lambda$0(Function0 function0, Function0 function02, TextContextMenuSession textContextMenuSession) {
        function0.invoke();
        if (function02 != null ? ((Boolean) function02.invoke()).booleanValue() : true) {
            textContextMenuSession.close();
        }
        return Unit.INSTANCE;
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem(TextContextMenuBuilderScope textContextMenuBuilderScope, final CoroutineScope coroutineScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem$default(textContextMenuBuilderScope, context, textContextMenuItems, z, null, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem$lambda$1;
                addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem$lambda$1 = TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem$lambda$1(CoroutineScope.this, function1);
                return addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem$lambda$1;
            }
        }, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem$lambda$1(CoroutineScope coroutineScope, Function1 function1) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$textFieldSuspendItem$1$1(function1, null), 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$8(final TextFieldSelectionManager textFieldSelectionManager, final CoroutineScope coroutineScope, TextContextMenuBuilderScope textContextMenuBuilderScope, final Context context) {
        boolean editable = textFieldSelectionManager.getEditable();
        AnnotatedString transformedText$foundation_release = textFieldSelectionManager.getTransformedText$foundation_release();
        TextRange textRange = null;
        String text = transformedText$foundation_release != null ? transformedText$foundation_release.getText() : null;
        TextRange latestSelection = textFieldSelectionManager.getLatestSelection();
        if (latestSelection != null) {
            long packedValue = latestSelection.getPackedValue();
            OffsetMapping offsetMapping = textFieldSelectionManager.getOffsetMapping();
            textRange = TextRange.m6836boximpl(TextRangeKt.TextRange(offsetMapping.originalToTransformed(TextRange.m6848getStartimpl(packedValue)), offsetMapping.originalToTransformed(TextRange.m6843getEndimpl(packedValue))));
        }
        PlatformSelectionBehaviors_androidKt.m1788addPlatformTextContextMenuItems71BSaZU(textContextMenuBuilderScope, context, editable, text, textRange, textFieldSelectionManager.getPlatformSelectionBehaviors(), new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7;
                addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7 = TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7(TextFieldSelectionManager.this, coroutineScope, context, (TextContextMenuBuilderScope) obj);
                return addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7(final TextFieldSelectionManager textFieldSelectionManager, CoroutineScope coroutineScope, Context context, TextContextMenuBuilderScope textContextMenuBuilderScope) {
        textContextMenuBuilderScope.separator();
        addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, TextContextMenuItems.Cut, textFieldSelectionManager.canCut$foundation_release(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$1(textFieldSelectionManager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, TextContextMenuItems.Copy, textFieldSelectionManager.canCopy$foundation_release(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$2(textFieldSelectionManager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldSuspendItem(textContextMenuBuilderScope, coroutineScope, context, TextContextMenuItems.Paste, textFieldSelectionManager.canPaste$foundation_release(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$3(textFieldSelectionManager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem(textContextMenuBuilderScope, context, TextContextMenuItems.SelectAll, textFieldSelectionManager.canSelectAll$foundation_release(), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$3;
                addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$3 = TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$3(TextFieldSelectionManager.this);
                return Boolean.valueOf(addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$3);
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$4;
                addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$4 = TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$4(TextFieldSelectionManager.this);
                return addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$4;
            }
        });
        if (Build.VERSION.SDK_INT >= 26) {
            addBasicTextFieldTextContextMenuComponents$lambda$8$textFieldItem$default(textContextMenuBuilderScope, context, TextContextMenuItems.Autofill, textFieldSelectionManager.canAutofill$foundation_release(), null, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$5;
                    addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$5 = TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$5(TextFieldSelectionManager.this);
                    return addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$5;
                }
            }, 8, null);
        }
        textContextMenuBuilderScope.separator();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$3(TextFieldSelectionManager textFieldSelectionManager) {
        return !textFieldSelectionManager.getTextToolbarShown$foundation_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$4(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.selectAll$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$8$lambda$7$lambda$6$lambda$5(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.autofill$foundation_release();
        return Unit.INSTANCE;
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final TextFieldSelectionManager textFieldSelectionManager, final ContextMenuState contextMenuState, final State<MenuItemsAvailability> state) {
        return new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit contextMenuBuilder$lambda$15;
                contextMenuBuilder$lambda$15 = TextFieldSelectionManager_androidKt.contextMenuBuilder$lambda$15(State.this, textFieldSelectionManager, contextMenuState, (ContextMenuScope) obj);
                return contextMenuBuilder$lambda$15;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$15(State state, final TextFieldSelectionManager textFieldSelectionManager, ContextMenuState contextMenuState, ContextMenuScope contextMenuScope) {
        int m1371unboximpl = ((MenuItemsAvailability) state.getValue()).m1371unboximpl();
        contextMenuBuilder$lambda$15$textFieldItem$9(contextMenuScope, contextMenuState, TextContextMenuItems.Cut, MenuItemsAvailability.m1366getCanCutimpl(m1371unboximpl), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit contextMenuBuilder$lambda$15$lambda$10;
                contextMenuBuilder$lambda$15$lambda$10 = TextFieldSelectionManager_androidKt.contextMenuBuilder$lambda$15$lambda$10(TextFieldSelectionManager.this);
                return contextMenuBuilder$lambda$15$lambda$10;
            }
        });
        contextMenuBuilder$lambda$15$textFieldItem$9(contextMenuScope, contextMenuState, TextContextMenuItems.Copy, MenuItemsAvailability.m1365getCanCopyimpl(m1371unboximpl), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit contextMenuBuilder$lambda$15$lambda$11;
                contextMenuBuilder$lambda$15$lambda$11 = TextFieldSelectionManager_androidKt.contextMenuBuilder$lambda$15$lambda$11(TextFieldSelectionManager.this);
                return contextMenuBuilder$lambda$15$lambda$11;
            }
        });
        contextMenuBuilder$lambda$15$textFieldItem$9(contextMenuScope, contextMenuState, TextContextMenuItems.Paste, MenuItemsAvailability.m1367getCanPasteimpl(m1371unboximpl), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit contextMenuBuilder$lambda$15$lambda$12;
                contextMenuBuilder$lambda$15$lambda$12 = TextFieldSelectionManager_androidKt.contextMenuBuilder$lambda$15$lambda$12(TextFieldSelectionManager.this);
                return contextMenuBuilder$lambda$15$lambda$12;
            }
        });
        contextMenuBuilder$lambda$15$textFieldItem$9(contextMenuScope, contextMenuState, TextContextMenuItems.SelectAll, MenuItemsAvailability.m1368getCanSelectAllimpl(m1371unboximpl), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit contextMenuBuilder$lambda$15$lambda$13;
                contextMenuBuilder$lambda$15$lambda$13 = TextFieldSelectionManager_androidKt.contextMenuBuilder$lambda$15$lambda$13(TextFieldSelectionManager.this);
                return contextMenuBuilder$lambda$15$lambda$13;
            }
        });
        if (Build.VERSION.SDK_INT >= 26) {
            contextMenuBuilder$lambda$15$textFieldItem$9(contextMenuScope, contextMenuState, TextContextMenuItems.Autofill, MenuItemsAvailability.m1364getCanAutofillimpl(m1371unboximpl), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit contextMenuBuilder$lambda$15$lambda$14;
                    contextMenuBuilder$lambda$15$lambda$14 = TextFieldSelectionManager_androidKt.contextMenuBuilder$lambda$15$lambda$14(TextFieldSelectionManager.this);
                    return contextMenuBuilder$lambda$15$lambda$14;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$15$lambda$10(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.cut$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$15$lambda$11(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.copy$foundation_release(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$15$lambda$12(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.paste$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$15$lambda$13(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.selectAll$foundation_release();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$15$lambda$14(TextFieldSelectionManager textFieldSelectionManager) {
        textFieldSelectionManager.autofill$foundation_release();
        return Unit.INSTANCE;
    }

    private static final void contextMenuBuilder$lambda$15$textFieldItem$9(ContextMenuScope contextMenuScope, ContextMenuState contextMenuState, TextContextMenuItems textContextMenuItems, boolean z, Function0<Unit> function0) {
        if (z) {
            ContextMenuScope.item$default(contextMenuScope, new ContextMenu_androidKt$TextItem$1(textContextMenuItems), null, false, null, new ContextMenu_androidKt$TextItem$2(function0, contextMenuState), 14, null);
        }
    }
}
