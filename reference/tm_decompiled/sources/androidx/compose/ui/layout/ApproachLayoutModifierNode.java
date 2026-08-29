package androidx.compose.ui.layout;

import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApproachLayoutModifierNode.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\b\u001a\u00020\u0003*\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J#\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0015\u001a\u00020\r*\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H&¢\u0006\u0004\b\u0017\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\u001c\u0010\u001e\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0016J\u001c\u0010!\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\"À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/ApproachLayoutModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "isMeasurementApproachInProgress", "", "lookaheadSize", "Landroidx/compose/ui/unit/IntSize;", "isMeasurementApproachInProgress-ozmzZPI", "(J)Z", "isPlacementApproachInProgress", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "lookaheadCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "approachMeasure", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "approachMeasure-3p2s80s", "(Landroidx/compose/ui/layout/ApproachMeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minApproachIntrinsicWidth", "", "Landroidx/compose/ui/layout/ApproachIntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minApproachIntrinsicHeight", "width", "maxApproachIntrinsicWidth", "maxApproachIntrinsicHeight", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ApproachLayoutModifierNode extends LayoutModifierNode {
    /* renamed from: approachMeasure-3p2s80s */
    MeasureResult mo113approachMeasure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j);

    /* renamed from: isMeasurementApproachInProgress-ozmzZPI */
    boolean mo114isMeasurementApproachInProgressozmzZPI(long lookaheadSize);

    boolean isPlacementApproachInProgress(Placeable.PlacementScope placementScope, LayoutCoordinates layoutCoordinates);

    int maxApproachIntrinsicHeight(ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    int maxApproachIntrinsicWidth(ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    MeasureResult mo108measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j);

    int minApproachIntrinsicHeight(ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    int minApproachIntrinsicWidth(ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i);

    /* compiled from: ApproachLayoutModifierNode.kt */
    /* renamed from: androidx.compose.ui.layout.ApproachLayoutModifierNode$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$isPlacementApproachInProgress(ApproachLayoutModifierNode _this, Placeable.PlacementScope placementScope, LayoutCoordinates layoutCoordinates) {
            return false;
        }

        /* renamed from: $default$measure-3p2s80s, reason: not valid java name */
        public static MeasureResult m6033$default$measure3p2s80s(ApproachLayoutModifierNode _this, MeasureScope measureScope, Measurable measurable, long j) {
            final Placeable mo6055measureBRTryo0 = measurable.mo6055measureBRTryo0(j);
            return MeasureScope.CC.layout$default(measureScope, mo6055measureBRTryo0.getWidth(), mo6055measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$measure$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                    Placeable.PlacementScope.place$default(placementScope, Placeable.this, 0, 0, 0.0f, 4, null);
                }
            }, 4, null);
        }

        public static int $default$minApproachIntrinsicWidth(final ApproachLayoutModifierNode _this, ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
            NodeCoordinator coordinator = _this.getNode().getCoordinator();
            Intrinsics.checkNotNull(coordinator);
            LookaheadDelegate lookaheadDelegate = coordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            if (lookaheadDelegate.getHasMeasureResult()) {
                return NodeMeasuringIntrinsics.INSTANCE.minWidth$ui_release(new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$minApproachIntrinsicWidth$1
                    @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo6034measure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
                        return ApproachLayoutModifierNode.this.mo113approachMeasure3p2s80s(approachMeasureScope, measurable, j);
                    }
                }, approachIntrinsicMeasureScope, intrinsicMeasurable, i);
            }
            return intrinsicMeasurable.minIntrinsicWidth(i);
        }

        public static int $default$minApproachIntrinsicHeight(final ApproachLayoutModifierNode _this, ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
            NodeCoordinator coordinator = _this.getNode().getCoordinator();
            Intrinsics.checkNotNull(coordinator);
            LookaheadDelegate lookaheadDelegate = coordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            if (lookaheadDelegate.getHasMeasureResult()) {
                return NodeMeasuringIntrinsics.INSTANCE.minHeight$ui_release(new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$minApproachIntrinsicHeight$1
                    @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo6034measure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
                        return ApproachLayoutModifierNode.this.mo113approachMeasure3p2s80s(approachMeasureScope, measurable, j);
                    }
                }, approachIntrinsicMeasureScope, intrinsicMeasurable, i);
            }
            return intrinsicMeasurable.minIntrinsicHeight(i);
        }

        public static int $default$maxApproachIntrinsicWidth(final ApproachLayoutModifierNode _this, ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
            NodeCoordinator coordinator = _this.getNode().getCoordinator();
            Intrinsics.checkNotNull(coordinator);
            LookaheadDelegate lookaheadDelegate = coordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            if (lookaheadDelegate.getHasMeasureResult()) {
                return NodeMeasuringIntrinsics.INSTANCE.maxWidth$ui_release(new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$maxApproachIntrinsicWidth$1
                    @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo6034measure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
                        return ApproachLayoutModifierNode.this.mo113approachMeasure3p2s80s(approachMeasureScope, measurable, j);
                    }
                }, approachIntrinsicMeasureScope, intrinsicMeasurable, i);
            }
            return intrinsicMeasurable.maxIntrinsicWidth(i);
        }

        public static int $default$maxApproachIntrinsicHeight(final ApproachLayoutModifierNode _this, ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
            NodeCoordinator coordinator = _this.getNode().getCoordinator();
            Intrinsics.checkNotNull(coordinator);
            LookaheadDelegate lookaheadDelegate = coordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            if (lookaheadDelegate.getHasMeasureResult()) {
                return NodeMeasuringIntrinsics.INSTANCE.maxHeight$ui_release(new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$maxApproachIntrinsicHeight$1
                    @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
                    /* renamed from: measure-3p2s80s, reason: not valid java name */
                    public final MeasureResult mo6034measure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
                        return ApproachLayoutModifierNode.this.mo113approachMeasure3p2s80s(approachMeasureScope, measurable, j);
                    }
                }, approachIntrinsicMeasureScope, intrinsicMeasurable, i);
            }
            return intrinsicMeasurable.maxIntrinsicHeight(i);
        }
    }
}
