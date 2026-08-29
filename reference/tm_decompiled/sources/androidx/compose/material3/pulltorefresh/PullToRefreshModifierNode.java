package androidx.compose.material3.pulltorefresh;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollNodeKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: PullToRefresh.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u00106\u001a\u00020\bH\u0082@¢\u0006\u0002\u00107J\u000e\u00108\u001a\u00020\bH\u0082@¢\u0006\u0002\u00107J\b\u00109\u001a\u00020\u0010H\u0002J\u001a\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;H\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010>J\b\u0010?\u001a\u00020\bH\u0016J*\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010B\u001a\u00020CH\u0016ø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020G2\u0006\u0010<\u001a\u00020GH\u0096@ø\u0001\u0000¢\u0006\u0004\bH\u0010IJ\"\u0010J\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010B\u001a\u00020CH\u0016ø\u0001\u0000¢\u0006\u0004\bK\u0010LJ\u0016\u0010M\u001a\u00020\u00102\u0006\u0010N\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010OJ\u0006\u0010P\u001a\u00020\bR\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R+\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u001b\"\u0004\b\u001e\u0010\u001dR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0012R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010\f\u001a\u00020\rX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0017R\u0014\u0010.\u001a\u00020/8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R+\u00102\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00108B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\u0019\u001a\u0004\b3\u0010\u0012\"\u0004\b4\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Q"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "isRefreshing", "", "onRefresh", "Lkotlin/Function0;", "", "enabled", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "threshold", "Landroidx/compose/ui/unit/Dp;", "(ZLkotlin/jvm/functions/Function0;ZLandroidx/compose/material3/pulltorefresh/PullToRefreshState;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "adjustedDistancePulled", "", "getAdjustedDistancePulled", "()F", "<set-?>", "distancePulled", "getDistancePulled", "setDistancePulled", "(F)V", "distancePulled$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getEnabled", "()Z", "setEnabled", "(Z)V", "setRefreshing", "nestedScrollNode", "Landroidx/compose/ui/node/DelegatableNode;", "getOnRefresh", "()Lkotlin/jvm/functions/Function0;", "setOnRefresh", "(Lkotlin/jvm/functions/Function0;)V", NotificationCompat.CATEGORY_PROGRESS, "getProgress", "getState", "()Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "setState", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;)V", "getThreshold-D9Ej5fM", "setThreshold-0680j_4", "F", "thresholdPx", "", "getThresholdPx", "()I", "verticalOffset", "getVerticalOffset", "setVerticalOffset", "verticalOffset$delegate", "animateToHidden", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToThreshold", "calculateVerticalOffset", "consumeAvailableOffset", "Landroidx/compose/ui/geometry/Offset;", "available", "consumeAvailableOffset-MK-Hz9U", "(J)J", "onAttach", "onPostScroll", "consumed", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "onRelease", "velocity", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PullToRefreshModifierNode extends DelegatingNode implements CompositionLocalConsumerModifierNode, NestedScrollConnection {
    public static final int $stable = 8;

    /* renamed from: distancePulled$delegate, reason: from kotlin metadata */
    private final MutableFloatState distancePulled;
    private boolean enabled;
    private boolean isRefreshing;
    private DelegatableNode nestedScrollNode;
    private Function0<Unit> onRefresh;
    private PullToRefreshState state;
    private float threshold;

    /* renamed from: verticalOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState verticalOffset;

    public /* synthetic */ PullToRefreshModifierNode(boolean z, Function0 function0, boolean z2, PullToRefreshState pullToRefreshState, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, function0, z2, pullToRefreshState, f);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    public /* synthetic */ Object mo606onPostFlingRZ2iAVY(long j, long j2, Continuation continuation) {
        return NestedScrollConnection.CC.m5792onPostFlingRZ2iAVY$suspendImpl(this, j, j2, continuation);
    }

    /* renamed from: isRefreshing, reason: from getter */
    public final boolean getIsRefreshing() {
        return this.isRefreshing;
    }

    public final void setRefreshing(boolean z) {
        this.isRefreshing = z;
    }

    public final Function0<Unit> getOnRefresh() {
        return this.onRefresh;
    }

    public final void setOnRefresh(Function0<Unit> function0) {
        this.onRefresh = function0;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final PullToRefreshState getState() {
        return this.state;
    }

    public final void setState(PullToRefreshState pullToRefreshState) {
        this.state = pullToRefreshState;
    }

    /* renamed from: getThreshold-D9Ej5fM, reason: not valid java name and from getter */
    public final float getThreshold() {
        return this.threshold;
    }

    /* renamed from: setThreshold-0680j_4, reason: not valid java name */
    public final void m3188setThreshold0680j_4(float f) {
        this.threshold = f;
    }

    private PullToRefreshModifierNode(boolean z, Function0<Unit> function0, boolean z2, PullToRefreshState pullToRefreshState, float f) {
        this.isRefreshing = z;
        this.onRefresh = function0;
        this.enabled = z2;
        this.state = pullToRefreshState;
        this.threshold = f;
        this.nestedScrollNode = NestedScrollNodeKt.nestedScrollModifierNode(this, null);
        this.verticalOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.distancePulled = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getVerticalOffset() {
        return this.verticalOffset.getFloatValue();
    }

    private final void setVerticalOffset(float f) {
        this.verticalOffset.setFloatValue(f);
    }

    private final float getDistancePulled() {
        return this.distancePulled.getFloatValue();
    }

    private final void setDistancePulled(float f) {
        this.distancePulled.setFloatValue(f);
    }

    private final float getAdjustedDistancePulled() {
        return getDistancePulled() * 0.5f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getThresholdPx() {
        return ((Density) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalDensity())).mo453roundToPx0680j_4(this.threshold);
    }

    private final float getProgress() {
        return getAdjustedDistancePulled() / getThresholdPx();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        delegate(this.nestedScrollNode);
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new PullToRefreshModifierNode$onAttach$1(this, null), 3, null);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public long mo609onPreScrollOzD1aCk(long available, int source) {
        if (!this.state.isAnimating() && this.enabled) {
            if (NestedScrollSource.m5805equalsimpl0(source, NestedScrollSource.INSTANCE.m5817getUserInputWNlRxjI()) && Offset.m4344getYimpl(available) < 0.0f) {
                return m3186consumeAvailableOffsetMKHz9U(available);
            }
            return Offset.INSTANCE.m4359getZeroF1C5BW0();
        }
        return Offset.INSTANCE.m4359getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo607onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.state.isAnimating() && this.enabled) {
            if (NestedScrollSource.m5805equalsimpl0(source, NestedScrollSource.INSTANCE.m5817getUserInputWNlRxjI())) {
                long m3186consumeAvailableOffsetMKHz9U = m3186consumeAvailableOffsetMKHz9U(available);
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new PullToRefreshModifierNode$onPostScroll$1(this, null), 3, null);
                return m3186consumeAvailableOffsetMKHz9U;
            }
            return Offset.INSTANCE.m4359getZeroF1C5BW0();
        }
        return Offset.INSTANCE.m4359getZeroF1C5BW0();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object mo608onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
        PullToRefreshModifierNode$onPreFling$1 pullToRefreshModifierNode$onPreFling$1;
        int i;
        float f;
        if (continuation instanceof PullToRefreshModifierNode$onPreFling$1) {
            pullToRefreshModifierNode$onPreFling$1 = (PullToRefreshModifierNode$onPreFling$1) continuation;
            if ((pullToRefreshModifierNode$onPreFling$1.label & Integer.MIN_VALUE) != 0) {
                pullToRefreshModifierNode$onPreFling$1.label -= Integer.MIN_VALUE;
                Object obj = pullToRefreshModifierNode$onPreFling$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pullToRefreshModifierNode$onPreFling$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    float m7642getYimpl = Velocity.m7642getYimpl(j);
                    pullToRefreshModifierNode$onPreFling$1.F$0 = 0.0f;
                    pullToRefreshModifierNode$onPreFling$1.label = 1;
                    obj = onRelease(m7642getYimpl, pullToRefreshModifierNode$onPreFling$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    f = 0.0f;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    f = pullToRefreshModifierNode$onPreFling$1.F$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Velocity.m7632boximpl(VelocityKt.Velocity(f, ((Number) obj).floatValue()));
            }
        }
        pullToRefreshModifierNode$onPreFling$1 = new PullToRefreshModifierNode$onPreFling$1(this, continuation);
        Object obj2 = pullToRefreshModifierNode$onPreFling$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pullToRefreshModifierNode$onPreFling$1.label;
        if (i != 0) {
        }
        return Velocity.m7632boximpl(VelocityKt.Velocity(f, ((Number) obj2).floatValue()));
    }

    public final void update() {
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new PullToRefreshModifierNode$update$1(this, null), 3, null);
    }

    /* renamed from: consumeAvailableOffset-MK-Hz9U, reason: not valid java name */
    private final long m3186consumeAvailableOffsetMKHz9U(long available) {
        float distancePulled;
        if (this.isRefreshing) {
            distancePulled = 0.0f;
        } else {
            float coerceAtLeast = RangesKt.coerceAtLeast(getDistancePulled() + Offset.m4344getYimpl(available), 0.0f);
            distancePulled = coerceAtLeast - getDistancePulled();
            setDistancePulled(coerceAtLeast);
            setVerticalOffset(calculateVerticalOffset());
        }
        return OffsetKt.Offset(0.0f, distancePulled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object onRelease(float f, Continuation<? super Float> continuation) {
        PullToRefreshModifierNode$onRelease$1 pullToRefreshModifierNode$onRelease$1;
        int i;
        PullToRefreshModifierNode pullToRefreshModifierNode;
        if (continuation instanceof PullToRefreshModifierNode$onRelease$1) {
            pullToRefreshModifierNode$onRelease$1 = (PullToRefreshModifierNode$onRelease$1) continuation;
            if ((pullToRefreshModifierNode$onRelease$1.label & Integer.MIN_VALUE) != 0) {
                pullToRefreshModifierNode$onRelease$1.label -= Integer.MIN_VALUE;
                Object obj = pullToRefreshModifierNode$onRelease$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pullToRefreshModifierNode$onRelease$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.isRefreshing) {
                        return Boxing.boxFloat(0.0f);
                    }
                    if (getAdjustedDistancePulled() > getThresholdPx()) {
                        pullToRefreshModifierNode$onRelease$1.L$0 = this;
                        pullToRefreshModifierNode$onRelease$1.F$0 = f;
                        pullToRefreshModifierNode$onRelease$1.label = 1;
                        if (animateToThreshold(pullToRefreshModifierNode$onRelease$1) != coroutine_suspended) {
                            pullToRefreshModifierNode = this;
                            pullToRefreshModifierNode.onRefresh.invoke();
                        }
                    } else {
                        pullToRefreshModifierNode$onRelease$1.L$0 = this;
                        pullToRefreshModifierNode$onRelease$1.F$0 = f;
                        pullToRefreshModifierNode$onRelease$1.label = 2;
                        if (animateToHidden(pullToRefreshModifierNode$onRelease$1) != coroutine_suspended) {
                            pullToRefreshModifierNode = this;
                        }
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    f = pullToRefreshModifierNode$onRelease$1.F$0;
                    pullToRefreshModifierNode = (PullToRefreshModifierNode) pullToRefreshModifierNode$onRelease$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    pullToRefreshModifierNode.onRefresh.invoke();
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    f = pullToRefreshModifierNode$onRelease$1.F$0;
                    pullToRefreshModifierNode = (PullToRefreshModifierNode) pullToRefreshModifierNode$onRelease$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                if (pullToRefreshModifierNode.getDistancePulled() != 0.0f || f < 0.0f) {
                    f = 0.0f;
                }
                pullToRefreshModifierNode.setDistancePulled(0.0f);
                return Boxing.boxFloat(f);
            }
        }
        pullToRefreshModifierNode$onRelease$1 = new PullToRefreshModifierNode$onRelease$1(this, continuation);
        Object obj2 = pullToRefreshModifierNode$onRelease$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pullToRefreshModifierNode$onRelease$1.label;
        if (i != 0) {
        }
        if (pullToRefreshModifierNode.getDistancePulled() != 0.0f) {
        }
        f = 0.0f;
        pullToRefreshModifierNode.setDistancePulled(0.0f);
        return Boxing.boxFloat(f);
    }

    private final float calculateVerticalOffset() {
        if (getAdjustedDistancePulled() <= getThresholdPx()) {
            return getAdjustedDistancePulled();
        }
        float coerceIn = RangesKt.coerceIn(Math.abs(getProgress()) - 1.0f, 0.0f, 2.0f);
        return getThresholdPx() + (getThresholdPx() * (coerceIn - (((float) Math.pow(coerceIn, 2)) / 4)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object animateToThreshold(Continuation<? super Unit> continuation) {
        PullToRefreshModifierNode$animateToThreshold$1 pullToRefreshModifierNode$animateToThreshold$1;
        int i;
        PullToRefreshModifierNode pullToRefreshModifierNode;
        if (continuation instanceof PullToRefreshModifierNode$animateToThreshold$1) {
            pullToRefreshModifierNode$animateToThreshold$1 = (PullToRefreshModifierNode$animateToThreshold$1) continuation;
            if ((pullToRefreshModifierNode$animateToThreshold$1.label & Integer.MIN_VALUE) != 0) {
                pullToRefreshModifierNode$animateToThreshold$1.label -= Integer.MIN_VALUE;
                Object obj = pullToRefreshModifierNode$animateToThreshold$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pullToRefreshModifierNode$animateToThreshold$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    PullToRefreshState pullToRefreshState = this.state;
                    pullToRefreshModifierNode$animateToThreshold$1.L$0 = this;
                    pullToRefreshModifierNode$animateToThreshold$1.label = 1;
                    if (pullToRefreshState.animateToThreshold(pullToRefreshModifierNode$animateToThreshold$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pullToRefreshModifierNode = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    pullToRefreshModifierNode = (PullToRefreshModifierNode) pullToRefreshModifierNode$animateToThreshold$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                pullToRefreshModifierNode.setDistancePulled(pullToRefreshModifierNode.getThresholdPx());
                pullToRefreshModifierNode.setVerticalOffset(pullToRefreshModifierNode.getThresholdPx());
                return Unit.INSTANCE;
            }
        }
        pullToRefreshModifierNode$animateToThreshold$1 = new PullToRefreshModifierNode$animateToThreshold$1(this, continuation);
        Object obj2 = pullToRefreshModifierNode$animateToThreshold$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pullToRefreshModifierNode$animateToThreshold$1.label;
        if (i != 0) {
        }
        pullToRefreshModifierNode.setDistancePulled(pullToRefreshModifierNode.getThresholdPx());
        pullToRefreshModifierNode.setVerticalOffset(pullToRefreshModifierNode.getThresholdPx());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object animateToHidden(Continuation<? super Unit> continuation) {
        PullToRefreshModifierNode$animateToHidden$1 pullToRefreshModifierNode$animateToHidden$1;
        int i;
        PullToRefreshModifierNode pullToRefreshModifierNode;
        if (continuation instanceof PullToRefreshModifierNode$animateToHidden$1) {
            pullToRefreshModifierNode$animateToHidden$1 = (PullToRefreshModifierNode$animateToHidden$1) continuation;
            if ((pullToRefreshModifierNode$animateToHidden$1.label & Integer.MIN_VALUE) != 0) {
                pullToRefreshModifierNode$animateToHidden$1.label -= Integer.MIN_VALUE;
                Object obj = pullToRefreshModifierNode$animateToHidden$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = pullToRefreshModifierNode$animateToHidden$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    PullToRefreshState pullToRefreshState = this.state;
                    pullToRefreshModifierNode$animateToHidden$1.L$0 = this;
                    pullToRefreshModifierNode$animateToHidden$1.label = 1;
                    if (pullToRefreshState.animateToHidden(pullToRefreshModifierNode$animateToHidden$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pullToRefreshModifierNode = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    pullToRefreshModifierNode = (PullToRefreshModifierNode) pullToRefreshModifierNode$animateToHidden$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                pullToRefreshModifierNode.setDistancePulled(0.0f);
                pullToRefreshModifierNode.setVerticalOffset(0.0f);
                return Unit.INSTANCE;
            }
        }
        pullToRefreshModifierNode$animateToHidden$1 = new PullToRefreshModifierNode$animateToHidden$1(this, continuation);
        Object obj2 = pullToRefreshModifierNode$animateToHidden$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = pullToRefreshModifierNode$animateToHidden$1.label;
        if (i != 0) {
        }
        pullToRefreshModifierNode.setDistancePulled(0.0f);
        pullToRefreshModifierNode.setVerticalOffset(0.0f);
        return Unit.INSTANCE;
    }
}
