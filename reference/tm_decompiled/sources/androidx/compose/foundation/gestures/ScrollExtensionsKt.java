package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.Scrollable2DState;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Ref;

/* compiled from: ScrollExtensions.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0086@¢\u0006\u0002\u0010\u0006\u001a,\u0010\u0000\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00072\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005H\u0086@¢\u0006\u0004\b\t\u0010\n\u001a\u001a\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@¢\u0006\u0002\u0010\f\u001a\u001c\u0010\u000b\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0007H\u0086@¢\u0006\u0004\b\r\u0010\u000e\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"animateScrollBy", "", "Landroidx/compose/foundation/gestures/ScrollableState;", "value", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/foundation/gestures/ScrollableState;FLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "animateScrollBy-ubNVwUQ", "(Landroidx/compose/foundation/gestures/Scrollable2DState;JLandroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollBy", "(Landroidx/compose/foundation/gestures/ScrollableState;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollBy-d-4ec7I", "(Landroidx/compose/foundation/gestures/Scrollable2DState;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopScroll", "", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "(Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/foundation/gestures/Scrollable2DState;Landroidx/compose/foundation/MutatePriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ScrollExtensionsKt {
    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object animateScrollBy(ScrollableState scrollableState, float f, AnimationSpec<Float> animationSpec, Continuation<? super Float> continuation) {
        ScrollExtensionsKt$animateScrollBy$1 scrollExtensionsKt$animateScrollBy$1;
        int i;
        Ref.FloatRef floatRef;
        if (continuation instanceof ScrollExtensionsKt$animateScrollBy$1) {
            scrollExtensionsKt$animateScrollBy$1 = (ScrollExtensionsKt$animateScrollBy$1) continuation;
            if ((scrollExtensionsKt$animateScrollBy$1.label & Integer.MIN_VALUE) != 0) {
                scrollExtensionsKt$animateScrollBy$1.label -= Integer.MIN_VALUE;
                ScrollExtensionsKt$animateScrollBy$1 scrollExtensionsKt$animateScrollBy$12 = scrollExtensionsKt$animateScrollBy$1;
                Object obj = scrollExtensionsKt$animateScrollBy$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = scrollExtensionsKt$animateScrollBy$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.FloatRef floatRef2 = new Ref.FloatRef();
                    ScrollExtensionsKt$animateScrollBy$2 scrollExtensionsKt$animateScrollBy$2 = new ScrollExtensionsKt$animateScrollBy$2(f, animationSpec, floatRef2, null);
                    scrollExtensionsKt$animateScrollBy$12.L$0 = floatRef2;
                    scrollExtensionsKt$animateScrollBy$12.label = 1;
                    if (ScrollableState.CC.scroll$default(scrollableState, null, scrollExtensionsKt$animateScrollBy$2, scrollExtensionsKt$animateScrollBy$12, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    floatRef = floatRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    floatRef = (Ref.FloatRef) scrollExtensionsKt$animateScrollBy$12.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxFloat(floatRef.element);
            }
        }
        scrollExtensionsKt$animateScrollBy$1 = new ScrollExtensionsKt$animateScrollBy$1(continuation);
        ScrollExtensionsKt$animateScrollBy$1 scrollExtensionsKt$animateScrollBy$122 = scrollExtensionsKt$animateScrollBy$1;
        Object obj2 = scrollExtensionsKt$animateScrollBy$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = scrollExtensionsKt$animateScrollBy$122.label;
        if (i != 0) {
        }
        return Boxing.boxFloat(floatRef.element);
    }

    public static /* synthetic */ Object animateScrollBy$default(ScrollableState scrollableState, float f, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animateScrollBy(scrollableState, f, animationSpec, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: animateScrollBy-ubNVwUQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object m590animateScrollByubNVwUQ(Scrollable2DState scrollable2DState, long j, AnimationSpec<Offset> animationSpec, Continuation<? super Offset> continuation) {
        ScrollExtensionsKt$animateScrollBy$3 scrollExtensionsKt$animateScrollBy$3;
        int i;
        Ref.LongRef longRef;
        if (continuation instanceof ScrollExtensionsKt$animateScrollBy$3) {
            scrollExtensionsKt$animateScrollBy$3 = (ScrollExtensionsKt$animateScrollBy$3) continuation;
            if ((scrollExtensionsKt$animateScrollBy$3.label & Integer.MIN_VALUE) != 0) {
                scrollExtensionsKt$animateScrollBy$3.label -= Integer.MIN_VALUE;
                ScrollExtensionsKt$animateScrollBy$3 scrollExtensionsKt$animateScrollBy$32 = scrollExtensionsKt$animateScrollBy$3;
                Object obj = scrollExtensionsKt$animateScrollBy$32.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = scrollExtensionsKt$animateScrollBy$32.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.LongRef longRef2 = new Ref.LongRef();
                    longRef2.element = Offset.INSTANCE.m4359getZeroF1C5BW0();
                    ScrollExtensionsKt$animateScrollBy$4 scrollExtensionsKt$animateScrollBy$4 = new ScrollExtensionsKt$animateScrollBy$4(j, animationSpec, longRef2, null);
                    scrollExtensionsKt$animateScrollBy$32.L$0 = longRef2;
                    scrollExtensionsKt$animateScrollBy$32.label = 1;
                    if (Scrollable2DState.CC.scroll$default(scrollable2DState, null, scrollExtensionsKt$animateScrollBy$4, scrollExtensionsKt$animateScrollBy$32, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    longRef = longRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    longRef = (Ref.LongRef) scrollExtensionsKt$animateScrollBy$32.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Offset.m4332boximpl(longRef.element);
            }
        }
        scrollExtensionsKt$animateScrollBy$3 = new ScrollExtensionsKt$animateScrollBy$3(continuation);
        ScrollExtensionsKt$animateScrollBy$3 scrollExtensionsKt$animateScrollBy$322 = scrollExtensionsKt$animateScrollBy$3;
        Object obj2 = scrollExtensionsKt$animateScrollBy$322.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = scrollExtensionsKt$animateScrollBy$322.label;
        if (i != 0) {
        }
        return Offset.m4332boximpl(longRef.element);
    }

    /* renamed from: animateScrollBy-ubNVwUQ$default, reason: not valid java name */
    public static /* synthetic */ Object m591animateScrollByubNVwUQ$default(Scrollable2DState scrollable2DState, long j, AnimationSpec animationSpec, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return m590animateScrollByubNVwUQ(scrollable2DState, j, animationSpec, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object scrollBy(ScrollableState scrollableState, float f, Continuation<? super Float> continuation) {
        ScrollExtensionsKt$scrollBy$1 scrollExtensionsKt$scrollBy$1;
        int i;
        Ref.FloatRef floatRef;
        if (continuation instanceof ScrollExtensionsKt$scrollBy$1) {
            scrollExtensionsKt$scrollBy$1 = (ScrollExtensionsKt$scrollBy$1) continuation;
            if ((scrollExtensionsKt$scrollBy$1.label & Integer.MIN_VALUE) != 0) {
                scrollExtensionsKt$scrollBy$1.label -= Integer.MIN_VALUE;
                ScrollExtensionsKt$scrollBy$1 scrollExtensionsKt$scrollBy$12 = scrollExtensionsKt$scrollBy$1;
                Object obj = scrollExtensionsKt$scrollBy$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = scrollExtensionsKt$scrollBy$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.FloatRef floatRef2 = new Ref.FloatRef();
                    ScrollExtensionsKt$scrollBy$2 scrollExtensionsKt$scrollBy$2 = new ScrollExtensionsKt$scrollBy$2(floatRef2, f, null);
                    scrollExtensionsKt$scrollBy$12.L$0 = floatRef2;
                    scrollExtensionsKt$scrollBy$12.label = 1;
                    if (ScrollableState.CC.scroll$default(scrollableState, null, scrollExtensionsKt$scrollBy$2, scrollExtensionsKt$scrollBy$12, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    floatRef = floatRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    floatRef = (Ref.FloatRef) scrollExtensionsKt$scrollBy$12.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxFloat(floatRef.element);
            }
        }
        scrollExtensionsKt$scrollBy$1 = new ScrollExtensionsKt$scrollBy$1(continuation);
        ScrollExtensionsKt$scrollBy$1 scrollExtensionsKt$scrollBy$122 = scrollExtensionsKt$scrollBy$1;
        Object obj2 = scrollExtensionsKt$scrollBy$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = scrollExtensionsKt$scrollBy$122.label;
        if (i != 0) {
        }
        return Boxing.boxFloat(floatRef.element);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* renamed from: scrollBy-d-4ec7I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object m592scrollByd4ec7I(Scrollable2DState scrollable2DState, long j, Continuation<? super Offset> continuation) {
        ScrollExtensionsKt$scrollBy$3 scrollExtensionsKt$scrollBy$3;
        int i;
        Ref.LongRef longRef;
        if (continuation instanceof ScrollExtensionsKt$scrollBy$3) {
            scrollExtensionsKt$scrollBy$3 = (ScrollExtensionsKt$scrollBy$3) continuation;
            if ((scrollExtensionsKt$scrollBy$3.label & Integer.MIN_VALUE) != 0) {
                scrollExtensionsKt$scrollBy$3.label -= Integer.MIN_VALUE;
                ScrollExtensionsKt$scrollBy$3 scrollExtensionsKt$scrollBy$32 = scrollExtensionsKt$scrollBy$3;
                Object obj = scrollExtensionsKt$scrollBy$32.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = scrollExtensionsKt$scrollBy$32.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.LongRef longRef2 = new Ref.LongRef();
                    longRef2.element = Offset.INSTANCE.m4359getZeroF1C5BW0();
                    ScrollExtensionsKt$scrollBy$4 scrollExtensionsKt$scrollBy$4 = new ScrollExtensionsKt$scrollBy$4(longRef2, j, null);
                    scrollExtensionsKt$scrollBy$32.L$0 = longRef2;
                    scrollExtensionsKt$scrollBy$32.label = 1;
                    if (Scrollable2DState.CC.scroll$default(scrollable2DState, null, scrollExtensionsKt$scrollBy$4, scrollExtensionsKt$scrollBy$32, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    longRef = longRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    longRef = (Ref.LongRef) scrollExtensionsKt$scrollBy$32.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Offset.m4332boximpl(longRef.element);
            }
        }
        scrollExtensionsKt$scrollBy$3 = new ScrollExtensionsKt$scrollBy$3(continuation);
        ScrollExtensionsKt$scrollBy$3 scrollExtensionsKt$scrollBy$322 = scrollExtensionsKt$scrollBy$3;
        Object obj2 = scrollExtensionsKt$scrollBy$322.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = scrollExtensionsKt$scrollBy$322.label;
        if (i != 0) {
        }
        return Offset.m4332boximpl(longRef.element);
    }

    public static /* synthetic */ Object stopScroll$default(ScrollableState scrollableState, MutatePriority mutatePriority, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopScroll(scrollableState, mutatePriority, (Continuation<? super Unit>) continuation);
    }

    public static final Object stopScroll(ScrollableState scrollableState, MutatePriority mutatePriority, Continuation<? super Unit> continuation) {
        Object scroll = scrollableState.scroll(mutatePriority, new ScrollExtensionsKt$stopScroll$2(null), continuation);
        return scroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scroll : Unit.INSTANCE;
    }

    public static /* synthetic */ Object stopScroll$default(Scrollable2DState scrollable2DState, MutatePriority mutatePriority, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return stopScroll(scrollable2DState, mutatePriority, (Continuation<? super Unit>) continuation);
    }

    public static final Object stopScroll(Scrollable2DState scrollable2DState, MutatePriority mutatePriority, Continuation<? super Unit> continuation) {
        Object scroll = scrollable2DState.scroll(mutatePriority, new ScrollExtensionsKt$stopScroll$4(null), continuation);
        return scroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? scroll : Unit.INSTANCE;
    }
}
