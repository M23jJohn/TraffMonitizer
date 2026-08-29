package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* compiled from: AppBar.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"androidx/compose/material3/ExitAlwaysScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ExitAlwaysScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitAlwaysScrollBehavior this$0;

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    public /* synthetic */ Object mo608onPreFlingQWom1Mo(long j, Continuation continuation) {
        return NestedScrollConnection.CC.m5793onPreFlingQWom1Mo$suspendImpl(this, j, continuation);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public /* synthetic */ long mo609onPreScrollOzD1aCk(long j, int i) {
        return NestedScrollConnection.CC.m5787$default$onPreScrollOzD1aCk(this, j, i);
    }

    ExitAlwaysScrollBehavior$nestedScrollConnection$1(ExitAlwaysScrollBehavior exitAlwaysScrollBehavior) {
        this.this$0 = exitAlwaysScrollBehavior;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo607onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue()) {
            return Offset.INSTANCE.m4359getZeroF1C5BW0();
        }
        BottomAppBarState state = this.this$0.getState();
        state.setContentOffset(state.getContentOffset() + Offset.m4344getYimpl(consumed));
        if ((this.this$0.getState().getHeightOffset() == 0.0f || this.this$0.getState().getHeightOffset() == this.this$0.getState().getHeightOffsetLimit()) && Offset.m4344getYimpl(consumed) == 0.0f && Offset.m4344getYimpl(available) > 0.0f) {
            this.this$0.getState().setContentOffset(0.0f);
        }
        this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m4344getYimpl(consumed));
        return Offset.INSTANCE.m4359getZeroF1C5BW0();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object mo606onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        Object obj;
        Object coroutine_suspended;
        int i;
        ExitAlwaysScrollBehavior$nestedScrollConnection$1 exitAlwaysScrollBehavior$nestedScrollConnection$1;
        long j3;
        long j4;
        if (continuation instanceof ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1) {
            exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 = (ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1) continuation;
            if ((exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
                ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1;
                obj = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.L$0 = this;
                    exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.J$0 = j2;
                    exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.label = 1;
                    obj = NestedScrollConnection.CC.m5792onPostFlingRZ2iAVY$suspendImpl(this, j, j2, exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12);
                    if (obj != coroutine_suspended) {
                        exitAlwaysScrollBehavior$nestedScrollConnection$1 = this;
                        j3 = j2;
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    j4 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.J$0;
                    ResultKt.throwOnFailure(obj);
                    return Velocity.m7632boximpl(Velocity.m7645plusAH228Gc(j4, ((Velocity) obj).getPackedValue()));
                }
                j3 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.J$0;
                exitAlwaysScrollBehavior$nestedScrollConnection$1 = (ExitAlwaysScrollBehavior$nestedScrollConnection$1) exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.L$0;
                ResultKt.throwOnFailure(obj);
                long packedValue = ((Velocity) obj).getPackedValue();
                BottomAppBarState state = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState();
                float m7642getYimpl = Velocity.m7642getYimpl(j3);
                DecayAnimationSpec<Float> flingAnimationSpec = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getFlingAnimationSpec();
                AnimationSpec<Float> snapAnimationSpec = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getSnapAnimationSpec();
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.L$0 = null;
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.J$0 = packedValue;
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12.label = 2;
                obj = AppBarKt.settleAppBarBottom(state, m7642getYimpl, flingAnimationSpec, snapAnimationSpec, exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$12);
                if (obj != coroutine_suspended) {
                    j4 = packedValue;
                    return Velocity.m7632boximpl(Velocity.m7645plusAH228Gc(j4, ((Velocity) obj).getPackedValue()));
                }
                return coroutine_suspended;
            }
        }
        exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
        ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$122 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        obj = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$122.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$122.label;
        if (i != 0) {
        }
        long packedValue2 = ((Velocity) obj).getPackedValue();
        BottomAppBarState state2 = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState();
        float m7642getYimpl2 = Velocity.m7642getYimpl(j3);
        DecayAnimationSpec<Float> flingAnimationSpec2 = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getFlingAnimationSpec();
        AnimationSpec<Float> snapAnimationSpec2 = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getSnapAnimationSpec();
        exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$122.L$0 = null;
        exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$122.J$0 = packedValue2;
        exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$122.label = 2;
        obj = AppBarKt.settleAppBarBottom(state2, m7642getYimpl2, flingAnimationSpec2, snapAnimationSpec2, exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$122);
        if (obj != coroutine_suspended) {
        }
        return coroutine_suspended;
    }
}
