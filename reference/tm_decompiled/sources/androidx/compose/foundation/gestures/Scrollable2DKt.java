package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* compiled from: Scrollable2D.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aB\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u001a\u001c\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000fH\u0082@¢\u0006\u0004\b\u0012\u0010\u0013\"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0014\u001a\u00020\u0015*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0018\u0010\u0019\u001a\u00020\u0015*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018¨\u0006\u001b"}, d2 = {"scrollable2D", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "enabled", "", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "NoOpScrollScope", "Landroidx/compose/foundation/gestures/Scroll2DScope;", "semanticsScrollBy", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/foundation/gestures/ScrollingLogic2D;", "offset", "semanticsScrollBy-d-4ec7I", "(Landroidx/compose/foundation/gestures/ScrollingLogic2D;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "magnitude", "", "Landroidx/compose/ui/unit/Velocity;", "getMagnitude-TH1AsA0", "(J)F", "angle", "getAngle-TH1AsA0", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Scrollable2DKt {
    private static final Scroll2DScope NoOpScrollScope = new Scroll2DScope() { // from class: androidx.compose.foundation.gestures.Scrollable2DKt$NoOpScrollScope$1
        @Override // androidx.compose.foundation.gestures.Scroll2DScope
        /* renamed from: scrollBy-MK-Hz9U */
        public long mo503scrollByMKHz9U(long delta) {
            return delta;
        }
    };

    public static /* synthetic */ Modifier scrollable2D$default(Modifier modifier, Scrollable2DState scrollable2DState, boolean z, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return scrollable2D(modifier, scrollable2DState, z, (i & 4) != 0 ? null : overscrollEffect, (i & 8) != 0 ? null : flingBehavior, (i & 16) != 0 ? null : mutableInteractionSource);
    }

    public static final Modifier scrollable2D(Modifier modifier, Scrollable2DState scrollable2DState, boolean z, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource) {
        return modifier.then(new Scrollable2DElement(scrollable2DState, overscrollEffect, z, flingBehavior, mutableInteractionSource));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: semanticsScrollBy-d-4ec7I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object m600semanticsScrollByd4ec7I(ScrollingLogic2D scrollingLogic2D, long j, Continuation<? super Offset> continuation) {
        Scrollable2DKt$semanticsScrollBy$1 scrollable2DKt$semanticsScrollBy$1;
        int i;
        Ref.LongRef longRef;
        if (continuation instanceof Scrollable2DKt$semanticsScrollBy$1) {
            scrollable2DKt$semanticsScrollBy$1 = (Scrollable2DKt$semanticsScrollBy$1) continuation;
            if ((scrollable2DKt$semanticsScrollBy$1.label & Integer.MIN_VALUE) != 0) {
                scrollable2DKt$semanticsScrollBy$1.label -= Integer.MIN_VALUE;
                Object obj = scrollable2DKt$semanticsScrollBy$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = scrollable2DKt$semanticsScrollBy$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.LongRef longRef2 = new Ref.LongRef();
                    longRef2.element = Offset.INSTANCE.m4359getZeroF1C5BW0();
                    MutatePriority mutatePriority = MutatePriority.Default;
                    Function2<? super NestedScrollScope, ? super Continuation<? super Unit>, ? extends Object> scrollable2DKt$semanticsScrollBy$2 = new Scrollable2DKt$semanticsScrollBy$2(j, longRef2, null);
                    scrollable2DKt$semanticsScrollBy$1.L$0 = longRef2;
                    scrollable2DKt$semanticsScrollBy$1.label = 1;
                    if (scrollingLogic2D.scroll(mutatePriority, scrollable2DKt$semanticsScrollBy$2, scrollable2DKt$semanticsScrollBy$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    longRef = longRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    longRef = (Ref.LongRef) scrollable2DKt$semanticsScrollBy$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Offset.m4332boximpl(longRef.element);
            }
        }
        scrollable2DKt$semanticsScrollBy$1 = new Scrollable2DKt$semanticsScrollBy$1(continuation);
        Object obj2 = scrollable2DKt$semanticsScrollBy$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = scrollable2DKt$semanticsScrollBy$1.label;
        if (i != 0) {
        }
        return Offset.m4332boximpl(longRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getMagnitude-TH1AsA0, reason: not valid java name */
    public static final float m599getMagnitudeTH1AsA0(long j) {
        double d = 2;
        return (float) Math.sqrt(((float) Math.pow(Velocity.m7641getXimpl(j), d)) + ((float) Math.pow(Velocity.m7642getYimpl(j), d)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAngle-TH1AsA0, reason: not valid java name */
    public static final float m598getAngleTH1AsA0(long j) {
        return (float) Math.atan2(Velocity.m7642getYimpl(j), Velocity.m7641getXimpl(j));
    }
}
