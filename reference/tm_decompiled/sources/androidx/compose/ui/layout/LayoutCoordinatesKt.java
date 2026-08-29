package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.Metadata;

/* compiled from: LayoutCoordinates.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u000f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\u000f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\u000f\u0010\u0005\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\u0002\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0002\u001a\u000f\u0010\t\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\n\u0010\n\u001a\u00020\u0007*\u00020\u0002\u001a\n\u0010\u000b\u001a\u00020\u0002*\u00020\u0002¨\u0006\f"}, d2 = {"positionInRoot", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInWindow", "positionOnScreen", "boundsInRoot", "Landroidx/compose/ui/geometry/Rect;", "boundsInWindow", "positionInParent", "boundsInParent", "findRootCoordinates", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutCoordinatesKt {
    public static final long positionInRoot(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo6060localToRootMKHz9U(Offset.INSTANCE.m4359getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo6062localToWindowMKHz9U(Offset.INSTANCE.m4359getZeroF1C5BW0());
    }

    public static final long positionOnScreen(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo6061localToScreenMKHz9U(Offset.INSTANCE.m4359getZeroF1C5BW0());
    }

    public static final Rect boundsInRoot(LayoutCoordinates layoutCoordinates) {
        return LayoutCoordinates.CC.localBoundingBoxOf$default(findRootCoordinates(layoutCoordinates), layoutCoordinates, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates findRootCoordinates = findRootCoordinates(layoutCoordinates);
        float mo6057getSizeYbymL2g = (int) (findRootCoordinates.mo6057getSizeYbymL2g() >> 32);
        float mo6057getSizeYbymL2g2 = (int) (findRootCoordinates.mo6057getSizeYbymL2g() & 4294967295L);
        Rect localBoundingBoxOf$default = LayoutCoordinates.CC.localBoundingBoxOf$default(findRootCoordinates, layoutCoordinates, false, 2, null);
        float left = localBoundingBoxOf$default.getLeft();
        if (left < 0.0f) {
            left = 0.0f;
        }
        if (left > mo6057getSizeYbymL2g) {
            left = mo6057getSizeYbymL2g;
        }
        float top = localBoundingBoxOf$default.getTop();
        if (top < 0.0f) {
            top = 0.0f;
        }
        if (top > mo6057getSizeYbymL2g2) {
            top = mo6057getSizeYbymL2g2;
        }
        float right = localBoundingBoxOf$default.getRight();
        if (right < 0.0f) {
            right = 0.0f;
        }
        if (right <= mo6057getSizeYbymL2g) {
            mo6057getSizeYbymL2g = right;
        }
        float bottom = localBoundingBoxOf$default.getBottom();
        float f = bottom >= 0.0f ? bottom : 0.0f;
        if (f <= mo6057getSizeYbymL2g2) {
            mo6057getSizeYbymL2g2 = f;
        }
        if (left == mo6057getSizeYbymL2g || top == mo6057getSizeYbymL2g2) {
            return Rect.INSTANCE.getZero();
        }
        long mo6062localToWindowMKHz9U = findRootCoordinates.mo6062localToWindowMKHz9U(Offset.m4335constructorimpl((Float.floatToRawIntBits(left) << 32) | (Float.floatToRawIntBits(top) & 4294967295L)));
        long mo6062localToWindowMKHz9U2 = findRootCoordinates.mo6062localToWindowMKHz9U(Offset.m4335constructorimpl((Float.floatToRawIntBits(top) & 4294967295L) | (Float.floatToRawIntBits(mo6057getSizeYbymL2g) << 32)));
        long mo6062localToWindowMKHz9U3 = findRootCoordinates.mo6062localToWindowMKHz9U(Offset.m4335constructorimpl((Float.floatToRawIntBits(mo6057getSizeYbymL2g) << 32) | (Float.floatToRawIntBits(mo6057getSizeYbymL2g2) & 4294967295L)));
        long mo6062localToWindowMKHz9U4 = findRootCoordinates.mo6062localToWindowMKHz9U(Offset.m4335constructorimpl((Float.floatToRawIntBits(mo6057getSizeYbymL2g2) & 4294967295L) | (Float.floatToRawIntBits(left) << 32)));
        float intBitsToFloat = Float.intBitsToFloat((int) (mo6062localToWindowMKHz9U >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (mo6062localToWindowMKHz9U2 >> 32));
        float intBitsToFloat3 = Float.intBitsToFloat((int) (mo6062localToWindowMKHz9U4 >> 32));
        float intBitsToFloat4 = Float.intBitsToFloat((int) (mo6062localToWindowMKHz9U3 >> 32));
        float min = Math.min(intBitsToFloat, Math.min(intBitsToFloat2, Math.min(intBitsToFloat3, intBitsToFloat4)));
        float max = Math.max(intBitsToFloat, Math.max(intBitsToFloat2, Math.max(intBitsToFloat3, intBitsToFloat4)));
        float intBitsToFloat5 = Float.intBitsToFloat((int) (mo6062localToWindowMKHz9U & 4294967295L));
        float intBitsToFloat6 = Float.intBitsToFloat((int) (mo6062localToWindowMKHz9U2 & 4294967295L));
        float intBitsToFloat7 = Float.intBitsToFloat((int) (mo6062localToWindowMKHz9U4 & 4294967295L));
        float intBitsToFloat8 = Float.intBitsToFloat((int) (4294967295L & mo6062localToWindowMKHz9U3));
        return new Rect(min, Math.min(intBitsToFloat5, Math.min(intBitsToFloat6, Math.min(intBitsToFloat7, intBitsToFloat8))), max, Math.max(intBitsToFloat5, Math.max(intBitsToFloat6, Math.max(intBitsToFloat7, intBitsToFloat8))));
    }

    public static final long positionInParent(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.mo6058localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m4359getZeroF1C5BW0()) : Offset.INSTANCE.m4359getZeroF1C5BW0();
    }

    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        Rect localBoundingBoxOf$default;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return (parentLayoutCoordinates == null || (localBoundingBoxOf$default = LayoutCoordinates.CC.localBoundingBoxOf$default(parentLayoutCoordinates, layoutCoordinates, false, 2, null)) == null) ? new Rect(0.0f, 0.0f, (int) (layoutCoordinates.mo6057getSizeYbymL2g() >> 32), (int) (layoutCoordinates.mo6057getSizeYbymL2g() & 4294967295L)) : localBoundingBoxOf$default;
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator = layoutCoordinates2 instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates2 : null;
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator wrappedBy = nodeCoordinator.getWrappedBy();
        while (true) {
            NodeCoordinator nodeCoordinator2 = wrappedBy;
            NodeCoordinator nodeCoordinator3 = nodeCoordinator;
            nodeCoordinator = nodeCoordinator2;
            if (nodeCoordinator != null) {
                wrappedBy = nodeCoordinator.getWrappedBy();
            } else {
                return nodeCoordinator3;
            }
        }
    }
}
