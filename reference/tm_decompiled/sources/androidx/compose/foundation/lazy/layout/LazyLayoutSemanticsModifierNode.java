package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: LazyLayoutSemantics.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ4\u0010\u001e\u001a\u00020\u001f2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\f\u0010 \u001a\u00020\u001f*\u00020!H\u0016J\b\u0010\"\u001a\u00020\u001fH\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticsModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "state", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "userScrollEnabled", "", "reverseScrolling", "<init>", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;Landroidx/compose/foundation/gestures/Orientation;ZZ)V", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "isVertical", "collectionInfo", "Landroidx/compose/ui/semantics/CollectionInfo;", "getCollectionInfo", "()Landroidx/compose/ui/semantics/CollectionInfo;", "scrollAxisRange", "Landroidx/compose/ui/semantics/ScrollAxisRange;", "indexForKeyMapping", "Lkotlin/Function1;", "", "", "scrollToIndexAction", "update", "", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "updateCachedSemanticsValues", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class LazyLayoutSemanticsModifierNode extends Modifier.Node implements SemanticsModifierNode {
    private final Function1<Object, Integer> indexForKeyMapping = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsModifierNode$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            int indexForKeyMapping$lambda$0;
            indexForKeyMapping$lambda$0 = LazyLayoutSemanticsModifierNode.indexForKeyMapping$lambda$0(LazyLayoutSemanticsModifierNode.this, obj);
            return Integer.valueOf(indexForKeyMapping$lambda$0);
        }
    };
    private Function0<? extends LazyLayoutItemProvider> itemProviderLambda;
    private Orientation orientation;
    private boolean reverseScrolling;
    private ScrollAxisRange scrollAxisRange;
    private Function1<? super Integer, Boolean> scrollToIndexAction;
    private LazyLayoutSemanticState state;
    private boolean userScrollEnabled;

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    /* renamed from: getShouldClearDescendantSemantics */
    public /* synthetic */ boolean getIsClearingSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldClearDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldMergeDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean isImportantForBounds() {
        return SemanticsModifierNode.CC.$default$isImportantForBounds(this);
    }

    public LazyLayoutSemanticsModifierNode(Function0<? extends LazyLayoutItemProvider> function0, LazyLayoutSemanticState lazyLayoutSemanticState, Orientation orientation, boolean z, boolean z2) {
        this.itemProviderLambda = function0;
        this.state = lazyLayoutSemanticState;
        this.orientation = orientation;
        this.userScrollEnabled = z;
        this.reverseScrolling = z2;
        updateCachedSemanticsValues();
    }

    private final boolean isVertical() {
        return this.orientation == Orientation.Vertical;
    }

    private final CollectionInfo getCollectionInfo() {
        return this.state.collectionInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int indexForKeyMapping$lambda$0(LazyLayoutSemanticsModifierNode lazyLayoutSemanticsModifierNode, Object obj) {
        LazyLayoutItemProvider invoke = lazyLayoutSemanticsModifierNode.itemProviderLambda.invoke();
        int itemCount = invoke.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (Intrinsics.areEqual(invoke.getKey(i), obj)) {
                return i;
            }
        }
        return -1;
    }

    public final void update(Function0<? extends LazyLayoutItemProvider> itemProviderLambda, LazyLayoutSemanticState state, Orientation orientation, boolean userScrollEnabled, boolean reverseScrolling) {
        this.itemProviderLambda = itemProviderLambda;
        this.state = state;
        if (this.orientation != orientation) {
            this.orientation = orientation;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (this.userScrollEnabled == userScrollEnabled && this.reverseScrolling == reverseScrolling) {
            return;
        }
        this.userScrollEnabled = userScrollEnabled;
        this.reverseScrolling = reverseScrolling;
        updateCachedSemanticsValues();
        SemanticsModifierNodeKt.invalidateSemantics(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        SemanticsPropertiesKt.indexForKey(semanticsPropertyReceiver, this.indexForKeyMapping);
        if (isVertical()) {
            ScrollAxisRange scrollAxisRange = this.scrollAxisRange;
            if (scrollAxisRange == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scrollAxisRange");
                scrollAxisRange = null;
            }
            SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        } else {
            ScrollAxisRange scrollAxisRange2 = this.scrollAxisRange;
            if (scrollAxisRange2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scrollAxisRange");
                scrollAxisRange2 = null;
            }
            SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange2);
        }
        Function1<? super Integer, Boolean> function1 = this.scrollToIndexAction;
        if (function1 != null) {
            SemanticsPropertiesKt.scrollToIndex$default(semanticsPropertyReceiver, null, function1, 1, null);
        }
        SemanticsPropertiesKt.getScrollViewportLength$default(semanticsPropertyReceiver, null, new Function0() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsModifierNode$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Float applySemantics$lambda$2;
                applySemantics$lambda$2 = LazyLayoutSemanticsModifierNode.applySemantics$lambda$2(LazyLayoutSemanticsModifierNode.this);
                return applySemantics$lambda$2;
            }
        }, 1, null);
        SemanticsPropertiesKt.setCollectionInfo(semanticsPropertyReceiver, getCollectionInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Float applySemantics$lambda$2(LazyLayoutSemanticsModifierNode lazyLayoutSemanticsModifierNode) {
        return Float.valueOf(lazyLayoutSemanticsModifierNode.state.getViewport() - lazyLayoutSemanticsModifierNode.state.getContentPadding());
    }

    private final void updateCachedSemanticsValues() {
        this.scrollAxisRange = new ScrollAxisRange(new Function0() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsModifierNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                float updateCachedSemanticsValues$lambda$3;
                updateCachedSemanticsValues$lambda$3 = LazyLayoutSemanticsModifierNode.updateCachedSemanticsValues$lambda$3(LazyLayoutSemanticsModifierNode.this);
                return Float.valueOf(updateCachedSemanticsValues$lambda$3);
            }
        }, new Function0() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsModifierNode$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                float updateCachedSemanticsValues$lambda$4;
                updateCachedSemanticsValues$lambda$4 = LazyLayoutSemanticsModifierNode.updateCachedSemanticsValues$lambda$4(LazyLayoutSemanticsModifierNode.this);
                return Float.valueOf(updateCachedSemanticsValues$lambda$4);
            }
        }, this.reverseScrolling);
        this.scrollToIndexAction = this.userScrollEnabled ? new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsModifierNode$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean updateCachedSemanticsValues$lambda$6;
                updateCachedSemanticsValues$lambda$6 = LazyLayoutSemanticsModifierNode.updateCachedSemanticsValues$lambda$6(LazyLayoutSemanticsModifierNode.this, ((Integer) obj).intValue());
                return Boolean.valueOf(updateCachedSemanticsValues$lambda$6);
            }
        } : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float updateCachedSemanticsValues$lambda$3(LazyLayoutSemanticsModifierNode lazyLayoutSemanticsModifierNode) {
        return lazyLayoutSemanticsModifierNode.state.getScrollOffset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float updateCachedSemanticsValues$lambda$4(LazyLayoutSemanticsModifierNode lazyLayoutSemanticsModifierNode) {
        return lazyLayoutSemanticsModifierNode.state.getMaxScrollOffset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean updateCachedSemanticsValues$lambda$6(LazyLayoutSemanticsModifierNode lazyLayoutSemanticsModifierNode, int i) {
        LazyLayoutItemProvider invoke = lazyLayoutSemanticsModifierNode.itemProviderLambda.invoke();
        if (!(i >= 0 && i < invoke.getItemCount())) {
            InlineClassHelperKt.throwIllegalArgumentException("Can't scroll to index " + i + ", it is out of bounds [0, " + invoke.getItemCount() + ')');
        }
        BuildersKt__Builders_commonKt.launch$default(lazyLayoutSemanticsModifierNode.getCoroutineScope(), null, null, new LazyLayoutSemanticsModifierNode$updateCachedSemanticsValues$3$2(lazyLayoutSemanticsModifierNode, i, null), 3, null);
        return true;
    }
}
