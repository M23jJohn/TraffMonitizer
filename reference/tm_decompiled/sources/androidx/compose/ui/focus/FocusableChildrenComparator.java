package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OneDimensionalFocusSearch.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\fH\u0002¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/focus/FocusableChildrenComparator;", "Ljava/util/Comparator;", "Landroidx/compose/ui/focus/FocusTargetNode;", "Lkotlin/Comparator;", "<init>", "()V", "compare", "", "a", "b", "pathFromRoot", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class FocusableChildrenComparator implements Comparator<FocusTargetNode> {
    public static final FocusableChildrenComparator INSTANCE = new FocusableChildrenComparator();

    private FocusableChildrenComparator() {
    }

    @Override // java.util.Comparator
    public int compare(FocusTargetNode a, FocusTargetNode b) {
        int i = 0;
        if (!FocusTraversalKt.isEligibleForFocusSearch(a) || !FocusTraversalKt.isEligibleForFocusSearch(b)) {
            if (FocusTraversalKt.isEligibleForFocusSearch(a)) {
                return -1;
            }
            return FocusTraversalKt.isEligibleForFocusSearch(b) ? 1 : 0;
        }
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(a);
        LayoutNode requireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(b);
        if (Intrinsics.areEqual(requireLayoutNode, requireLayoutNode2)) {
            return 0;
        }
        MutableVector<LayoutNode> pathFromRoot = pathFromRoot(requireLayoutNode);
        MutableVector<LayoutNode> pathFromRoot2 = pathFromRoot(requireLayoutNode2);
        int min = Math.min(pathFromRoot.getSize() - 1, pathFromRoot2.getSize() - 1);
        if (min >= 0) {
            while (Intrinsics.areEqual(pathFromRoot.content[i], pathFromRoot2.content[i])) {
                if (i != min) {
                    i++;
                }
            }
            return Intrinsics.compare(pathFromRoot.content[i].getPlaceOrder$ui_release(), pathFromRoot2.content[i].getPlaceOrder$ui_release());
        }
        throw new IllegalStateException("Could not find a common ancestor between the two FocusModifiers.".toString());
    }

    private final MutableVector<LayoutNode> pathFromRoot(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = new MutableVector<>(new LayoutNode[16], 0);
        while (layoutNode != null) {
            mutableVector.add(0, layoutNode);
            layoutNode = layoutNode.getParent$ui_release();
        }
        return mutableVector;
    }
}
