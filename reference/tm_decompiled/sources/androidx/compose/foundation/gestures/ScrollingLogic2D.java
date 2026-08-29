package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: Scrollable2D.kt */
@Metadata(d1 = {"\u0000}\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u001c\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ#\u0010!\u001a\u00020 *\u00020\u001a2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0017H\u0002¢\u0006\u0004\b$\u0010%J\u0015\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020 ¢\u0006\u0004\b(\u0010)J\u0015\u0010&\u001a\u00020\f2\u0006\u0010*\u001a\u00020+¢\u0006\u0004\b,\u0010)J\u0017\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020 H\u0016¢\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020 2\u0006\u0010.\u001a\u00020 H\u0002¢\u0006\u0004\b2\u00100J\u0018\u00103\u001a\u0002042\u0006\u00105\u001a\u00020+H\u0086@¢\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u00020\f2\u0006\u00109\u001a\u00020 H\u0002¢\u0006\u0004\b:\u0010)J\u0018\u0010;\u001a\u00020+2\u0006\u0010<\u001a\u00020+H\u0096@¢\u0006\u0004\b=\u00107J\u0006\u0010>\u001a\u00020\fJA\u0010.\u001a\u0002042\b\b\u0002\u0010?\u001a\u00020@2'\u0010A\u001a#\b\u0001\u0012\u0004\u0012\u00020C\u0012\n\u0012\b\u0012\u0004\u0012\u0002040D\u0012\u0006\u0012\u0004\u0018\u00010E0B¢\u0006\u0002\bFH\u0086@¢\u0006\u0002\u0010GJ(\u0010H\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollingLogic2D;", "Landroidx/compose/foundation/gestures/ScrollLogic;", "scrollableState", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "nestedScrollDispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "isScrollableNodeAttached", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/gestures/Scrollable2DState;Landroidx/compose/foundation/OverscrollEffect;Landroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;Lkotlin/jvm/functions/Function0;)V", "getScrollableState", "()Landroidx/compose/foundation/gestures/Scrollable2DState;", "setScrollableState", "(Landroidx/compose/foundation/gestures/Scrollable2DState;)V", "value", "isFlinging", "()Z", "latestScrollSource", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "I", "outerStateScope", "Landroidx/compose/foundation/gestures/Scroll2DScope;", "nestedScrollScope", "androidx/compose/foundation/gestures/ScrollingLogic2D$nestedScrollScope$1", "Landroidx/compose/foundation/gestures/ScrollingLogic2D$nestedScrollScope$1;", "performScrollForOverscroll", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "performScroll", "delta", "source", "performScroll-3eAAhYA", "(Landroidx/compose/foundation/gestures/Scroll2DScope;JI)J", "shouldDispatchOverscroll", "offset", "shouldDispatchOverscroll-k-4lQ0M", "(J)Z", "velocity", "Landroidx/compose/ui/unit/Velocity;", "shouldDispatchOverscroll-TH1AsA0", "performRawScroll", "scroll", "performRawScroll-MK-Hz9U", "(J)J", "dispatchRawDelta", "dispatchRawDelta-MK-Hz9U", "onScrollStopped", "", "initialVelocity", "onScrollStopped-sF-c-tU", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldCancelFling", "pixels", "shouldCancelFling-k-4lQ0M", "doFlingAnimation", "available", "doFlingAnimation-QWom1Mo", "shouldScrollImmediately", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/NestedScrollScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class ScrollingLogic2D implements ScrollLogic {
    private FlingBehavior flingBehavior;
    private boolean isFlinging;
    private final Function0<Boolean> isScrollableNodeAttached;
    private int latestScrollSource = NestedScrollSource.INSTANCE.m5817getUserInputWNlRxjI();
    private NestedScrollDispatcher nestedScrollDispatcher;
    private final ScrollingLogic2D$nestedScrollScope$1 nestedScrollScope;
    private Scroll2DScope outerStateScope;
    private OverscrollEffect overscrollEffect;
    private final Function1<Offset, Offset> performScrollForOverscroll;
    private Scrollable2DState scrollableState;

    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.foundation.gestures.ScrollingLogic2D$nestedScrollScope$1] */
    public ScrollingLogic2D(Scrollable2DState scrollable2DState, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, NestedScrollDispatcher nestedScrollDispatcher, Function0<Boolean> function0) {
        Scroll2DScope scroll2DScope;
        this.scrollableState = scrollable2DState;
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        this.isScrollableNodeAttached = function0;
        scroll2DScope = Scrollable2DKt.NoOpScrollScope;
        this.outerStateScope = scroll2DScope;
        this.nestedScrollScope = new NestedScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic2D$nestedScrollScope$1
            @Override // androidx.compose.foundation.gestures.NestedScrollScope
            /* renamed from: scrollBy-OzD1aCk */
            public long mo563scrollByOzD1aCk(long offset, int source) {
                Scroll2DScope scroll2DScope2;
                long m632performScroll3eAAhYA;
                scroll2DScope2 = ScrollingLogic2D.this.outerStateScope;
                m632performScroll3eAAhYA = ScrollingLogic2D.this.m632performScroll3eAAhYA(scroll2DScope2, offset, source);
                return m632performScroll3eAAhYA;
            }

            @Override // androidx.compose.foundation.gestures.NestedScrollScope
            /* renamed from: scrollByWithOverscroll-OzD1aCk */
            public long mo564scrollByWithOverscrollOzD1aCk(long offset, int source) {
                OverscrollEffect overscrollEffect2;
                Scroll2DScope scroll2DScope2;
                long m632performScroll3eAAhYA;
                int i;
                Function1<? super Offset, Offset> function1;
                ScrollingLogic2D.this.latestScrollSource = source;
                overscrollEffect2 = ScrollingLogic2D.this.overscrollEffect;
                if (overscrollEffect2 == null || !ScrollingLogic2D.this.m636shouldDispatchOverscrollk4lQ0M(offset)) {
                    scroll2DScope2 = ScrollingLogic2D.this.outerStateScope;
                    m632performScroll3eAAhYA = ScrollingLogic2D.this.m632performScroll3eAAhYA(scroll2DScope2, offset, source);
                    return m632performScroll3eAAhYA;
                }
                i = ScrollingLogic2D.this.latestScrollSource;
                function1 = ScrollingLogic2D.this.performScrollForOverscroll;
                return overscrollEffect2.mo276applyToScrollRhakbz0(offset, i, function1);
            }
        };
        this.performScrollForOverscroll = new Function1() { // from class: androidx.compose.foundation.gestures.ScrollingLogic2D$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Offset performScrollForOverscroll$lambda$1;
                performScrollForOverscroll$lambda$1 = ScrollingLogic2D.performScrollForOverscroll$lambda$1(ScrollingLogic2D.this, (Offset) obj);
                return performScrollForOverscroll$lambda$1;
            }
        };
    }

    public final Scrollable2DState getScrollableState() {
        return this.scrollableState;
    }

    public final void setScrollableState(Scrollable2DState scrollable2DState) {
        this.scrollableState = scrollable2DState;
    }

    @Override // androidx.compose.foundation.gestures.ScrollLogic
    /* renamed from: isFlinging, reason: from getter */
    public boolean getIsFlinging() {
        return this.isFlinging;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset performScrollForOverscroll$lambda$1(ScrollingLogic2D scrollingLogic2D, Offset offset) {
        return Offset.m4332boximpl(scrollingLogic2D.m632performScroll3eAAhYA(scrollingLogic2D.outerStateScope, offset.m4353unboximpl(), scrollingLogic2D.latestScrollSource));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: performScroll-3eAAhYA, reason: not valid java name */
    public final long m632performScroll3eAAhYA(Scroll2DScope scroll2DScope, long j, int i) {
        long m5801dispatchPreScrollOzD1aCk = this.nestedScrollDispatcher.m5801dispatchPreScrollOzD1aCk(j, i);
        long m4347minusMKHz9U = Offset.m4347minusMKHz9U(j, m5801dispatchPreScrollOzD1aCk);
        long mo503scrollByMKHz9U = scroll2DScope.mo503scrollByMKHz9U(m4347minusMKHz9U);
        return Offset.m4348plusMKHz9U(Offset.m4348plusMKHz9U(m5801dispatchPreScrollOzD1aCk, mo503scrollByMKHz9U), this.nestedScrollDispatcher.m5799dispatchPostScrollDzOQY0M(mo503scrollByMKHz9U, Offset.m4347minusMKHz9U(m4347minusMKHz9U, mo503scrollByMKHz9U), i));
    }

    /* renamed from: shouldDispatchOverscroll-k-4lQ0M, reason: not valid java name */
    public final boolean m636shouldDispatchOverscrollk4lQ0M(long offset) {
        return this.scrollableState.mo501canScrollk4lQ0M(offset);
    }

    /* renamed from: shouldDispatchOverscroll-TH1AsA0, reason: not valid java name */
    public final boolean m635shouldDispatchOverscrollTH1AsA0(long velocity) {
        Scrollable2DState scrollable2DState = this.scrollableState;
        float m7641getXimpl = Velocity.m7641getXimpl(velocity);
        float m7642getYimpl = Velocity.m7642getYimpl(velocity);
        return scrollable2DState.mo501canScrollk4lQ0M(Offset.m4335constructorimpl((Float.floatToRawIntBits(m7642getYimpl) & 4294967295L) | (Float.floatToRawIntBits(m7641getXimpl) << 32)));
    }

    @Override // androidx.compose.foundation.gestures.ScrollLogic
    /* renamed from: performRawScroll-MK-Hz9U */
    public long mo594performRawScrollMKHz9U(long scroll) {
        if (this.scrollableState.isScrollInProgress()) {
            return Offset.INSTANCE.m4359getZeroF1C5BW0();
        }
        return m631dispatchRawDeltaMKHz9U(scroll);
    }

    /* renamed from: dispatchRawDelta-MK-Hz9U, reason: not valid java name */
    private final long m631dispatchRawDeltaMKHz9U(long scroll) {
        return this.scrollableState.mo502dispatchRawDeltaMKHz9U(scroll);
    }

    /* renamed from: onScrollStopped-sF-c-tU, reason: not valid java name */
    public final Object m634onScrollStoppedsFctU(long j, Continuation<? super Unit> continuation) {
        ScrollingLogic2D$onScrollStopped$performFling$1 scrollingLogic2D$onScrollStopped$performFling$1 = new ScrollingLogic2D$onScrollStopped$performFling$1(this, null);
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        if (overscrollEffect != null && m635shouldDispatchOverscrollTH1AsA0(j)) {
            Object mo275applyToFlingBMRW4eQ = overscrollEffect.mo275applyToFlingBMRW4eQ(j, scrollingLogic2D$onScrollStopped$performFling$1, continuation);
            return mo275applyToFlingBMRW4eQ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mo275applyToFlingBMRW4eQ : Unit.INSTANCE;
        }
        Object invoke = scrollingLogic2D$onScrollStopped$performFling$1.invoke(Velocity.m7632boximpl(j), continuation);
        return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: shouldCancelFling-k-4lQ0M, reason: not valid java name */
    public final boolean m633shouldCancelFlingk4lQ0M(long pixels) {
        return (this.scrollableState.mo501canScrollk4lQ0M(pixels) && this.isScrollableNodeAttached.invoke().booleanValue()) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.compose.foundation.gestures.ScrollLogic
    /* renamed from: doFlingAnimation-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object mo593doFlingAnimationQWom1Mo(long j, Continuation<? super Velocity> continuation) {
        ScrollingLogic2D$doFlingAnimation$1 scrollingLogic2D$doFlingAnimation$1;
        int i;
        ScrollingLogic2D scrollingLogic2D;
        Throwable th;
        Ref.LongRef longRef;
        if (continuation instanceof ScrollingLogic2D$doFlingAnimation$1) {
            scrollingLogic2D$doFlingAnimation$1 = (ScrollingLogic2D$doFlingAnimation$1) continuation;
            if ((scrollingLogic2D$doFlingAnimation$1.label & Integer.MIN_VALUE) != 0) {
                scrollingLogic2D$doFlingAnimation$1.label -= Integer.MIN_VALUE;
                Object obj = scrollingLogic2D$doFlingAnimation$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = scrollingLogic2D$doFlingAnimation$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.LongRef longRef2 = new Ref.LongRef();
                    longRef2.element = j;
                    this.isFlinging = true;
                    try {
                        MutatePriority mutatePriority = MutatePriority.Default;
                        scrollingLogic2D = this;
                        try {
                            ScrollingLogic2D$doFlingAnimation$2 scrollingLogic2D$doFlingAnimation$2 = new ScrollingLogic2D$doFlingAnimation$2(scrollingLogic2D, j, longRef2, null);
                            scrollingLogic2D$doFlingAnimation$1.L$0 = longRef2;
                            scrollingLogic2D$doFlingAnimation$1.label = 1;
                            if (scroll(mutatePriority, scrollingLogic2D$doFlingAnimation$2, scrollingLogic2D$doFlingAnimation$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            longRef = longRef2;
                        } catch (Throwable th2) {
                            th = th2;
                            th = th;
                            scrollingLogic2D.isFlinging = false;
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        scrollingLogic2D = this;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    longRef = (Ref.LongRef) scrollingLogic2D$doFlingAnimation$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        scrollingLogic2D = this;
                    } catch (Throwable th4) {
                        th = th4;
                        scrollingLogic2D = this;
                        scrollingLogic2D.isFlinging = false;
                        throw th;
                    }
                }
                scrollingLogic2D.isFlinging = false;
                return Velocity.m7632boximpl(longRef.element);
            }
        }
        scrollingLogic2D$doFlingAnimation$1 = new ScrollingLogic2D$doFlingAnimation$1(this, continuation);
        Object obj2 = scrollingLogic2D$doFlingAnimation$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = scrollingLogic2D$doFlingAnimation$1.label;
        if (i != 0) {
        }
        scrollingLogic2D.isFlinging = false;
        return Velocity.m7632boximpl(longRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long doFlingAnimation_QWom1Mo$toDecomposedOffset(float f, long j) {
        float m598getAngleTH1AsA0;
        float m598getAngleTH1AsA02;
        float m598getAngleTH1AsA03;
        m598getAngleTH1AsA0 = Scrollable2DKt.m598getAngleTH1AsA0(j);
        if (!Float.isNaN(m598getAngleTH1AsA0)) {
            m598getAngleTH1AsA02 = Scrollable2DKt.m598getAngleTH1AsA0(j);
            float abs = Math.abs(((float) Math.cos(m598getAngleTH1AsA02)) * f) * Math.signum(Velocity.m7641getXimpl(j));
            m598getAngleTH1AsA03 = Scrollable2DKt.m598getAngleTH1AsA0(j);
            float abs2 = Math.abs(((float) Math.sin(m598getAngleTH1AsA03)) * f) * Math.signum(Velocity.m7642getYimpl(j));
            return Offset.m4335constructorimpl((Float.floatToRawIntBits(abs) << 32) | (4294967295L & Float.floatToRawIntBits(abs2)));
        }
        return Offset.m4335constructorimpl((Float.floatToRawIntBits(0.0f) << 32) | (4294967295L & Float.floatToRawIntBits(f)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long doFlingAnimation_QWom1Mo$toDecomposedVelocity(float f, long j) {
        float m598getAngleTH1AsA0;
        float m598getAngleTH1AsA02;
        float m598getAngleTH1AsA03;
        m598getAngleTH1AsA0 = Scrollable2DKt.m598getAngleTH1AsA0(j);
        if (!Float.isNaN(m598getAngleTH1AsA0)) {
            m598getAngleTH1AsA02 = Scrollable2DKt.m598getAngleTH1AsA0(j);
            float abs = Math.abs(((float) Math.cos(m598getAngleTH1AsA02)) * f) * Math.signum(Velocity.m7641getXimpl(j));
            m598getAngleTH1AsA03 = Scrollable2DKt.m598getAngleTH1AsA0(j);
            return VelocityKt.Velocity(abs, Math.abs(((float) Math.sin(m598getAngleTH1AsA03)) * f) * Math.signum(Velocity.m7642getYimpl(j)));
        }
        return VelocityKt.Velocity(0.0f, f);
    }

    public final boolean shouldScrollImmediately() {
        if (this.scrollableState.isScrollInProgress()) {
            return true;
        }
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        return overscrollEffect != null ? overscrollEffect.isInProgress() : false;
    }

    public static /* synthetic */ Object scroll$default(ScrollingLogic2D scrollingLogic2D, MutatePriority mutatePriority, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return scrollingLogic2D.scroll(mutatePriority, function2, continuation);
    }

    public final Object scroll(MutatePriority mutatePriority, Function2<? super NestedScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object scroll = this.scrollableState.scroll(mutatePriority, new ScrollingLogic2D$scroll$2(this, function2, null), continuation);
        return scroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scroll : Unit.INSTANCE;
    }

    public final boolean update(Scrollable2DState scrollableState, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, NestedScrollDispatcher nestedScrollDispatcher) {
        boolean z;
        if (Intrinsics.areEqual(this.scrollableState, scrollableState)) {
            z = false;
        } else {
            this.scrollableState = scrollableState;
            z = true;
        }
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float doFlingAnimation_QWom1Mo$toMagnitudeFloat(long j) {
        double d = 2;
        return (float) Math.sqrt(((float) Math.pow(Float.intBitsToFloat((int) (j >> 32)), d)) + ((float) Math.pow(Float.intBitsToFloat((int) (j & 4294967295L)), d)));
    }
}
