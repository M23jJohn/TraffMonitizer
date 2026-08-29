package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* compiled from: LazyLayoutScrollScope.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0082\b\u001a\u0014\u0010\f\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a2\u0010\u0010\u001a\u00020\b*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0080@¢\u0006\u0002\u0010\u0015\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"TargetDistance", "Landroidx/compose/ui/unit/Dp;", "F", "BoundDistance", "MinimumDistance", "DEBUG", "", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "isItemVisible", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "index", "", "animateScrollToItem", "scrollOffset", "numOfItemsForTeleport", "density", "Landroidx/compose/ui/unit/Density;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;IIILandroidx/compose/ui/unit/Density;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutScrollScopeKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.m7399constructorimpl(2500);
    private static final float BoundDistance = Dp.m7399constructorimpl(1500);
    private static final float MinimumDistance = Dp.m7399constructorimpl(50);

    private static final void debugLog(Function0<String> function0) {
    }

    public static final boolean isItemVisible(LazyLayoutScrollScope lazyLayoutScrollScope, int i) {
        return i <= lazyLayoutScrollScope.getLastVisibleItemIndex() && lazyLayoutScrollScope.getFirstVisibleItemIndex() <= i;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(19:(1:(2:30|31))|(1:(17:82|39|(1:41)(1:76)|42|(1:44)(1:75)|45|46|47|48|49|50|51|52|53|54|(8:56|21|22|23|24|25|26|(4:28|30|31|(0)(4:33|34|35|(1:37))))|57)(1:83))(0)|38|39|(0)(0)|42|(0)(0)|45|46|47|48|49|50|51|52|53|54|(0)|57) */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01da, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01db, code lost:
    
        r21 = r5;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00eb A[Catch: ItemFoundInScroll -> 0x01e8, TryCatch #4 {ItemFoundInScroll -> 0x01e8, blocks: (B:26:0x00e7, B:28:0x00eb, B:30:0x00f1, B:39:0x0120, B:42:0x015c, B:45:0x0164), top: B:25:0x00e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x007c  */
    /* JADX WARN: Type inference failed for: r8v0, types: [T, androidx.compose.animation.core.AnimationState] */
    /* JADX WARN: Type inference failed for: r8v16, types: [T, androidx.compose.animation.core.AnimationState] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x01b4 -> B:21:0x0073). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object animateScrollToItem(LazyLayoutScrollScope lazyLayoutScrollScope, int i, int i2, int i3, Density density, Continuation<? super Unit> continuation) {
        LazyLayoutScrollScopeKt$animateScrollToItem$1 lazyLayoutScrollScopeKt$animateScrollToItem$1;
        Object coroutine_suspended;
        int i4;
        float f;
        final LazyLayoutScrollScope lazyLayoutScrollScope2;
        int i5;
        int i6;
        int i7;
        float mo459toPx0680j_4;
        float mo459toPx0680j_42;
        float mo459toPx0680j_43;
        Ref.BooleanRef booleanRef;
        Ref.ObjectRef objectRef;
        final int i8;
        final int i9;
        final float f2;
        final Ref.IntRef intRef;
        float f3;
        LazyLayoutScrollScopeKt$animateScrollToItem$1 lazyLayoutScrollScopeKt$animateScrollToItem$12;
        float f4;
        int i10;
        int i11;
        LazyLayoutScrollScope lazyLayoutScrollScope3;
        AnimationState copy$default;
        Float boxFloat;
        int i12;
        Function1 function1;
        LazyLayoutScrollScope lazyLayoutScrollScope4;
        int i13;
        int i14;
        LazyLayoutScrollScopeKt$animateScrollToItem$1 lazyLayoutScrollScopeKt$animateScrollToItem$13;
        int calculateDistanceTo$default;
        float f5;
        final float f6;
        AnimationState animationState;
        Float boxFloat2;
        boolean z;
        Function1 function12;
        final LazyLayoutScrollScope lazyLayoutScrollScope5;
        final int i15;
        int i16;
        if (continuation instanceof LazyLayoutScrollScopeKt$animateScrollToItem$1) {
            lazyLayoutScrollScopeKt$animateScrollToItem$1 = (LazyLayoutScrollScopeKt$animateScrollToItem$1) continuation;
            if ((lazyLayoutScrollScopeKt$animateScrollToItem$1.label & Integer.MIN_VALUE) != 0) {
                lazyLayoutScrollScopeKt$animateScrollToItem$1.label -= Integer.MIN_VALUE;
                Object obj = lazyLayoutScrollScopeKt$animateScrollToItem$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i4 = lazyLayoutScrollScopeKt$animateScrollToItem$1.label;
                int i17 = 0;
                int i18 = 1;
                if (i4 != 0) {
                    f = 0.0f;
                    ResultKt.throwOnFailure(obj);
                    if (!(((float) i) >= 0.0f)) {
                        InlineClassHelperKt.throwIllegalArgumentException("Index should be non-negative");
                    }
                    try {
                        mo459toPx0680j_4 = density.mo459toPx0680j_4(TargetDistance);
                        mo459toPx0680j_42 = density.mo459toPx0680j_4(BoundDistance);
                        mo459toPx0680j_43 = density.mo459toPx0680j_4(MinimumDistance);
                        booleanRef = new Ref.BooleanRef();
                        booleanRef.element = true;
                        objectRef = new Ref.ObjectRef();
                        objectRef.element = AnimationStateKt.AnimationState$default(0.0f, 0.0f, 0L, 0L, false, 30, null);
                    } catch (ItemFoundInScroll e) {
                        e = e;
                        lazyLayoutScrollScope2 = lazyLayoutScrollScope;
                        i5 = 0;
                    }
                    if (isItemVisible(lazyLayoutScrollScope, i)) {
                        lazyLayoutScrollScope2 = lazyLayoutScrollScope;
                        i5 = 0;
                        try {
                            throw new ItemFoundInScroll(LazyLayoutScrollScope.CC.calculateDistanceTo$default(lazyLayoutScrollScope2, i, 0, 2, null), (AnimationState) objectRef.element);
                        } catch (ItemFoundInScroll e2) {
                            e = e2;
                            i6 = i2;
                            i7 = i;
                            copy$default = AnimationStateKt.copy$default((AnimationState) e.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                            final float itemOffset = e.getItemOffset() + i6;
                            final Ref.FloatRef floatRef = new Ref.FloatRef();
                            boxFloat = Boxing.boxFloat(itemOffset);
                            if (((Number) copy$default.getVelocity()).floatValue() == f) {
                            }
                            function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    Unit animateScrollToItem$lambda$19;
                                    animateScrollToItem$lambda$19 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$19(itemOffset, floatRef, lazyLayoutScrollScope2, (AnimationScope) obj2);
                                    return animateScrollToItem$lambda$19;
                                }
                            };
                            lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0 = lazyLayoutScrollScope2;
                            lazyLayoutScrollScopeKt$animateScrollToItem$1.L$1 = null;
                            lazyLayoutScrollScopeKt$animateScrollToItem$1.L$2 = null;
                            lazyLayoutScrollScopeKt$animateScrollToItem$1.L$3 = null;
                            lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0 = i7;
                            lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1 = i6;
                            lazyLayoutScrollScopeKt$animateScrollToItem$1.label = 2;
                            if (SuspendAnimationKt.animateTo$default(copy$default, boxFloat, null, i12 ^ 1, function1, lazyLayoutScrollScopeKt$animateScrollToItem$1, 2, null) != coroutine_suspended) {
                            }
                            return coroutine_suspended;
                        }
                    }
                    try {
                        int i19 = i > lazyLayoutScrollScope.getFirstVisibleItemIndex() ? 1 : 0;
                        Ref.IntRef intRef2 = new Ref.IntRef();
                        intRef2.element = 1;
                        i8 = i2;
                        i9 = i3;
                        f2 = mo459toPx0680j_42;
                        intRef = intRef2;
                        f3 = mo459toPx0680j_4;
                        lazyLayoutScrollScopeKt$animateScrollToItem$12 = lazyLayoutScrollScopeKt$animateScrollToItem$1;
                        f4 = mo459toPx0680j_43;
                        i10 = i;
                        i11 = i19;
                        lazyLayoutScrollScope3 = lazyLayoutScrollScope;
                        if (booleanRef.element) {
                        }
                    } catch (ItemFoundInScroll e3) {
                        e = e3;
                        lazyLayoutScrollScope2 = lazyLayoutScrollScope;
                        i6 = i2;
                        i7 = i;
                        i5 = 0;
                        copy$default = AnimationStateKt.copy$default((AnimationState) e.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                        final float itemOffset2 = e.getItemOffset() + i6;
                        final Ref.FloatRef floatRef2 = new Ref.FloatRef();
                        boxFloat = Boxing.boxFloat(itemOffset2);
                        if (((Number) copy$default.getVelocity()).floatValue() == f) {
                        }
                        function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                Unit animateScrollToItem$lambda$19;
                                animateScrollToItem$lambda$19 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$19(itemOffset2, floatRef2, lazyLayoutScrollScope2, (AnimationScope) obj2);
                                return animateScrollToItem$lambda$19;
                            }
                        };
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0 = lazyLayoutScrollScope2;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$1 = null;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$2 = null;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$3 = null;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0 = i7;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1 = i6;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.label = 2;
                        if (SuspendAnimationKt.animateTo$default(copy$default, boxFloat, null, i12 ^ 1, function1, lazyLayoutScrollScopeKt$animateScrollToItem$1, 2, null) != coroutine_suspended) {
                        }
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                if (i4 != 1) {
                    if (i4 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    i13 = lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1;
                    i14 = lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0;
                    lazyLayoutScrollScope4 = (LazyLayoutScrollScope) lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    lazyLayoutScrollScope4.snapToItem(i14, i13);
                    return Unit.INSTANCE;
                }
                i11 = lazyLayoutScrollScopeKt$animateScrollToItem$1.I$3;
                float f7 = lazyLayoutScrollScopeKt$animateScrollToItem$1.F$2;
                float f8 = lazyLayoutScrollScopeKt$animateScrollToItem$1.F$1;
                f3 = lazyLayoutScrollScopeKt$animateScrollToItem$1.F$0;
                int i20 = lazyLayoutScrollScopeKt$animateScrollToItem$1.I$2;
                int i21 = lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1;
                i7 = lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0;
                Ref.IntRef intRef3 = (Ref.IntRef) lazyLayoutScrollScopeKt$animateScrollToItem$1.L$3;
                f = 0.0f;
                Ref.ObjectRef objectRef2 = (Ref.ObjectRef) lazyLayoutScrollScopeKt$animateScrollToItem$1.L$2;
                Ref.BooleanRef booleanRef2 = (Ref.BooleanRef) lazyLayoutScrollScopeKt$animateScrollToItem$1.L$1;
                LazyLayoutScrollScope lazyLayoutScrollScope6 = (LazyLayoutScrollScope) lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    objectRef = objectRef2;
                    booleanRef = booleanRef2;
                    f2 = f8;
                    i16 = i20;
                    i8 = i21;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12 = lazyLayoutScrollScopeKt$animateScrollToItem$1;
                    f4 = f7;
                    lazyLayoutScrollScope3 = lazyLayoutScrollScope6;
                    i10 = i7;
                } catch (ItemFoundInScroll e4) {
                    e = e4;
                    lazyLayoutScrollScope2 = lazyLayoutScrollScope6;
                    i5 = 0;
                    i6 = i21;
                }
                try {
                    try {
                        intRef3.element++;
                    } catch (ItemFoundInScroll e5) {
                        e = e5;
                        i7 = i10;
                        lazyLayoutScrollScopeKt$animateScrollToItem$13 = lazyLayoutScrollScopeKt$animateScrollToItem$12;
                        i6 = i8;
                        lazyLayoutScrollScope2 = lazyLayoutScrollScope3;
                    }
                    i9 = i16;
                    intRef = intRef3;
                    i17 = 0;
                    i18 = 1;
                } catch (ItemFoundInScroll e6) {
                    e = e6;
                    i7 = i10;
                    lazyLayoutScrollScopeKt$animateScrollToItem$1 = lazyLayoutScrollScopeKt$animateScrollToItem$12;
                    i6 = i8;
                    i5 = 0;
                    lazyLayoutScrollScope2 = lazyLayoutScrollScope3;
                    copy$default = AnimationStateKt.copy$default((AnimationState) e.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                    final float itemOffset22 = e.getItemOffset() + i6;
                    final Ref.FloatRef floatRef22 = new Ref.FloatRef();
                    boxFloat = Boxing.boxFloat(itemOffset22);
                    if (((Number) copy$default.getVelocity()).floatValue() == f) {
                    }
                    function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            Unit animateScrollToItem$lambda$19;
                            animateScrollToItem$lambda$19 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$19(itemOffset22, floatRef22, lazyLayoutScrollScope2, (AnimationScope) obj2);
                            return animateScrollToItem$lambda$19;
                        }
                    };
                    lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0 = lazyLayoutScrollScope2;
                    lazyLayoutScrollScopeKt$animateScrollToItem$1.L$1 = null;
                    lazyLayoutScrollScopeKt$animateScrollToItem$1.L$2 = null;
                    lazyLayoutScrollScopeKt$animateScrollToItem$1.L$3 = null;
                    lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0 = i7;
                    lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1 = i6;
                    lazyLayoutScrollScopeKt$animateScrollToItem$1.label = 2;
                    if (SuspendAnimationKt.animateTo$default(copy$default, boxFloat, null, i12 ^ 1, function1, lazyLayoutScrollScopeKt$animateScrollToItem$1, 2, null) != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (booleanRef.element && lazyLayoutScrollScope3.getItemCount() > 0) {
                    try {
                        try {
                            calculateDistanceTo$default = LazyLayoutScrollScope.CC.calculateDistanceTo$default(lazyLayoutScrollScope3, i10, i17, 2, null) + i8;
                        } catch (ItemFoundInScroll e7) {
                            e = e7;
                            lazyLayoutScrollScopeKt$animateScrollToItem$13 = lazyLayoutScrollScopeKt$animateScrollToItem$12;
                            lazyLayoutScrollScope2 = lazyLayoutScrollScope5;
                            i7 = i15;
                            i6 = i8;
                        }
                    } catch (ItemFoundInScroll e8) {
                        e = e8;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1 = lazyLayoutScrollScopeKt$animateScrollToItem$13;
                        i5 = 0;
                        copy$default = AnimationStateKt.copy$default((AnimationState) e.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                        final float itemOffset222 = e.getItemOffset() + i6;
                        final Ref.FloatRef floatRef222 = new Ref.FloatRef();
                        boxFloat = Boxing.boxFloat(itemOffset222);
                        if (((Number) copy$default.getVelocity()).floatValue() == f) {
                        }
                        function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                Unit animateScrollToItem$lambda$19;
                                animateScrollToItem$lambda$19 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$19(itemOffset222, floatRef222, lazyLayoutScrollScope2, (AnimationScope) obj2);
                                return animateScrollToItem$lambda$19;
                            }
                        };
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0 = lazyLayoutScrollScope2;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$1 = null;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$2 = null;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$3 = null;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0 = i7;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1 = i6;
                        lazyLayoutScrollScopeKt$animateScrollToItem$1.label = 2;
                        if (SuspendAnimationKt.animateTo$default(copy$default, boxFloat, null, i12 ^ 1, function1, lazyLayoutScrollScopeKt$animateScrollToItem$1, 2, null) != coroutine_suspended) {
                        }
                        return coroutine_suspended;
                    }
                    if (Math.abs(calculateDistanceTo$default) < f3) {
                        if (i11 != 0) {
                            f6 = f3;
                            objectRef.element = AnimationStateKt.copy$default((AnimationState) objectRef.element, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                            final Ref.FloatRef floatRef3 = new Ref.FloatRef();
                            animationState = (AnimationState) objectRef.element;
                            boxFloat2 = Boxing.boxFloat(f6);
                            z = (((Number) ((AnimationState) objectRef.element).getVelocity()).floatValue() != f ? i18 : i17) ^ i18;
                            final boolean z2 = i11 == 0 ? i18 : i17;
                            lazyLayoutScrollScope5 = lazyLayoutScrollScope3;
                            i15 = i10;
                            final Ref.BooleanRef booleanRef3 = booleanRef;
                            final Ref.ObjectRef objectRef3 = objectRef;
                            function12 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    Unit animateScrollToItem$lambda$15;
                                    animateScrollToItem$lambda$15 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$15(LazyLayoutScrollScope.this, i15, f6, floatRef3, booleanRef3, z2, f2, intRef, i9, i8, objectRef3, (AnimationScope) obj2);
                                    return animateScrollToItem$lambda$15;
                                }
                            };
                            lazyLayoutScrollScope2 = lazyLayoutScrollScope5;
                            i7 = i15;
                            float f9 = f2;
                            intRef3 = intRef;
                            i16 = i9;
                            i6 = i8;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.L$0 = lazyLayoutScrollScope2;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.L$1 = booleanRef3;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.L$2 = objectRef3;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.L$3 = intRef3;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.I$0 = i7;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.I$1 = i6;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.I$2 = i16;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.F$0 = f3;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.F$1 = f9;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.F$2 = f4;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.I$3 = i11;
                            lazyLayoutScrollScopeKt$animateScrollToItem$12.label = 1;
                            lazyLayoutScrollScopeKt$animateScrollToItem$13 = lazyLayoutScrollScopeKt$animateScrollToItem$12;
                            if (SuspendAnimationKt.animateTo$default(animationState, boxFloat2, null, z, function12, lazyLayoutScrollScopeKt$animateScrollToItem$13, 2, null) != coroutine_suspended) {
                                objectRef = objectRef3;
                                booleanRef = booleanRef3;
                                lazyLayoutScrollScope3 = lazyLayoutScrollScope2;
                                lazyLayoutScrollScopeKt$animateScrollToItem$12 = lazyLayoutScrollScopeKt$animateScrollToItem$13;
                                f2 = f9;
                                i8 = i6;
                                i10 = i7;
                                intRef3.element++;
                                i9 = i16;
                                intRef = intRef3;
                                i17 = 0;
                                i18 = 1;
                                if (booleanRef.element) {
                                    calculateDistanceTo$default = LazyLayoutScrollScope.CC.calculateDistanceTo$default(lazyLayoutScrollScope3, i10, i17, 2, null) + i8;
                                    if (Math.abs(calculateDistanceTo$default) < f3) {
                                        try {
                                            f5 = Math.max(Math.abs(calculateDistanceTo$default), f4);
                                            if (i11 == 0) {
                                                f5 = -f5;
                                            }
                                        } catch (ItemFoundInScroll e9) {
                                            e = e9;
                                            i7 = i10;
                                            lazyLayoutScrollScopeKt$animateScrollToItem$1 = lazyLayoutScrollScopeKt$animateScrollToItem$12;
                                            i5 = i17;
                                            i6 = i8;
                                            lazyLayoutScrollScope2 = lazyLayoutScrollScope3;
                                            copy$default = AnimationStateKt.copy$default((AnimationState) e.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                                            final float itemOffset2222 = e.getItemOffset() + i6;
                                            final Ref.FloatRef floatRef2222 = new Ref.FloatRef();
                                            boxFloat = Boxing.boxFloat(itemOffset2222);
                                            if (((Number) copy$default.getVelocity()).floatValue() == f) {
                                            }
                                            function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda1
                                                @Override // kotlin.jvm.functions.Function1
                                                public final Object invoke(Object obj2) {
                                                    Unit animateScrollToItem$lambda$19;
                                                    animateScrollToItem$lambda$19 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$19(itemOffset2222, floatRef2222, lazyLayoutScrollScope2, (AnimationScope) obj2);
                                                    return animateScrollToItem$lambda$19;
                                                }
                                            };
                                            lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0 = lazyLayoutScrollScope2;
                                            lazyLayoutScrollScopeKt$animateScrollToItem$1.L$1 = null;
                                            lazyLayoutScrollScopeKt$animateScrollToItem$1.L$2 = null;
                                            lazyLayoutScrollScopeKt$animateScrollToItem$1.L$3 = null;
                                            lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0 = i7;
                                            lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1 = i6;
                                            lazyLayoutScrollScopeKt$animateScrollToItem$1.label = 2;
                                            if (SuspendAnimationKt.animateTo$default(copy$default, boxFloat, null, i12 ^ 1, function1, lazyLayoutScrollScopeKt$animateScrollToItem$1, 2, null) != coroutine_suspended) {
                                            }
                                            return coroutine_suspended;
                                        }
                                    }
                                }
                            }
                            return coroutine_suspended;
                        }
                        f5 = -f3;
                    }
                    f6 = f5;
                    objectRef.element = AnimationStateKt.copy$default((AnimationState) objectRef.element, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                    final Ref.FloatRef floatRef32 = new Ref.FloatRef();
                    animationState = (AnimationState) objectRef.element;
                    boxFloat2 = Boxing.boxFloat(f6);
                    z = (((Number) ((AnimationState) objectRef.element).getVelocity()).floatValue() != f ? i18 : i17) ^ i18;
                    if (i11 == 0) {
                    }
                    lazyLayoutScrollScope5 = lazyLayoutScrollScope3;
                    i15 = i10;
                    final Ref.BooleanRef booleanRef32 = booleanRef;
                    final Ref.ObjectRef objectRef32 = objectRef;
                    function12 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            Unit animateScrollToItem$lambda$15;
                            animateScrollToItem$lambda$15 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$15(LazyLayoutScrollScope.this, i15, f6, floatRef32, booleanRef32, z2, f2, intRef, i9, i8, objectRef32, (AnimationScope) obj2);
                            return animateScrollToItem$lambda$15;
                        }
                    };
                    lazyLayoutScrollScope2 = lazyLayoutScrollScope5;
                    i7 = i15;
                    float f92 = f2;
                    intRef3 = intRef;
                    i16 = i9;
                    i6 = i8;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.L$0 = lazyLayoutScrollScope2;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.L$1 = booleanRef32;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.L$2 = objectRef32;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.L$3 = intRef3;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.I$0 = i7;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.I$1 = i6;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.I$2 = i16;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.F$0 = f3;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.F$1 = f92;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.F$2 = f4;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.I$3 = i11;
                    lazyLayoutScrollScopeKt$animateScrollToItem$12.label = 1;
                    lazyLayoutScrollScopeKt$animateScrollToItem$13 = lazyLayoutScrollScopeKt$animateScrollToItem$12;
                    if (SuspendAnimationKt.animateTo$default(animationState, boxFloat2, null, z, function12, lazyLayoutScrollScopeKt$animateScrollToItem$13, 2, null) != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
                copy$default = AnimationStateKt.copy$default((AnimationState) e.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                final float itemOffset22222 = e.getItemOffset() + i6;
                final Ref.FloatRef floatRef22222 = new Ref.FloatRef();
                boxFloat = Boxing.boxFloat(itemOffset22222);
                i12 = ((Number) copy$default.getVelocity()).floatValue() == f ? 1 : i5;
                function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        Unit animateScrollToItem$lambda$19;
                        animateScrollToItem$lambda$19 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$19(itemOffset22222, floatRef22222, lazyLayoutScrollScope2, (AnimationScope) obj2);
                        return animateScrollToItem$lambda$19;
                    }
                };
                lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0 = lazyLayoutScrollScope2;
                lazyLayoutScrollScopeKt$animateScrollToItem$1.L$1 = null;
                lazyLayoutScrollScopeKt$animateScrollToItem$1.L$2 = null;
                lazyLayoutScrollScopeKt$animateScrollToItem$1.L$3 = null;
                lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0 = i7;
                lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1 = i6;
                lazyLayoutScrollScopeKt$animateScrollToItem$1.label = 2;
                if (SuspendAnimationKt.animateTo$default(copy$default, boxFloat, null, i12 ^ 1, function1, lazyLayoutScrollScopeKt$animateScrollToItem$1, 2, null) != coroutine_suspended) {
                    lazyLayoutScrollScope4 = lazyLayoutScrollScope2;
                    i13 = i6;
                    i14 = i7;
                    lazyLayoutScrollScope4.snapToItem(i14, i13);
                    return Unit.INSTANCE;
                }
                return coroutine_suspended;
            }
        }
        lazyLayoutScrollScopeKt$animateScrollToItem$1 = new LazyLayoutScrollScopeKt$animateScrollToItem$1(continuation);
        Object obj2 = lazyLayoutScrollScopeKt$animateScrollToItem$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i4 = lazyLayoutScrollScopeKt$animateScrollToItem$1.label;
        int i172 = 0;
        int i182 = 1;
        if (i4 != 0) {
        }
        copy$default = AnimationStateKt.copy$default((AnimationState) e.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
        final float itemOffset222222 = e.getItemOffset() + i6;
        final Ref.FloatRef floatRef222222 = new Ref.FloatRef();
        boxFloat = Boxing.boxFloat(itemOffset222222);
        if (((Number) copy$default.getVelocity()).floatValue() == f) {
        }
        function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj22) {
                Unit animateScrollToItem$lambda$19;
                animateScrollToItem$lambda$19 = LazyLayoutScrollScopeKt.animateScrollToItem$lambda$19(itemOffset222222, floatRef222222, lazyLayoutScrollScope2, (AnimationScope) obj22);
                return animateScrollToItem$lambda$19;
            }
        };
        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$0 = lazyLayoutScrollScope2;
        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$1 = null;
        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$2 = null;
        lazyLayoutScrollScopeKt$animateScrollToItem$1.L$3 = null;
        lazyLayoutScrollScopeKt$animateScrollToItem$1.I$0 = i7;
        lazyLayoutScrollScopeKt$animateScrollToItem$1.I$1 = i6;
        lazyLayoutScrollScopeKt$animateScrollToItem$1.label = 2;
        if (SuspendAnimationKt.animateTo$default(copy$default, boxFloat, null, i12 ^ 1, function1, lazyLayoutScrollScopeKt$animateScrollToItem$1, 2, null) != coroutine_suspended) {
        }
        return coroutine_suspended;
    }

    private static final boolean animateScrollToItem$isOvershot(boolean z, LazyLayoutScrollScope lazyLayoutScrollScope, int i, int i2) {
        if (z) {
            if (lazyLayoutScrollScope.getFirstVisibleItemIndex() > i) {
                return true;
            }
            return lazyLayoutScrollScope.getFirstVisibleItemIndex() == i && lazyLayoutScrollScope.getFirstVisibleItemScrollOffset() > i2;
        }
        if (lazyLayoutScrollScope.getFirstVisibleItemIndex() < i) {
            return true;
        }
        return lazyLayoutScrollScope.getFirstVisibleItemIndex() == i && lazyLayoutScrollScope.getFirstVisibleItemScrollOffset() < i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit animateScrollToItem$lambda$15(LazyLayoutScrollScope lazyLayoutScrollScope, int i, float f, Ref.FloatRef floatRef, Ref.BooleanRef booleanRef, boolean z, float f2, Ref.IntRef intRef, int i2, int i3, Ref.ObjectRef objectRef, AnimationScope animationScope) {
        float coerceAtLeast;
        if (!isItemVisible(lazyLayoutScrollScope, i)) {
            if (f > 0.0f) {
                coerceAtLeast = RangesKt.coerceAtMost(((Number) animationScope.getValue()).floatValue(), f);
            } else {
                coerceAtLeast = RangesKt.coerceAtLeast(((Number) animationScope.getValue()).floatValue(), f);
            }
            float f3 = coerceAtLeast - floatRef.element;
            float scrollBy = lazyLayoutScrollScope.scrollBy(f3);
            if (!isItemVisible(lazyLayoutScrollScope, i) && !animateScrollToItem$isOvershot(z, lazyLayoutScrollScope, i, i3)) {
                if (f3 != scrollBy) {
                    animationScope.cancelAnimation();
                    booleanRef.element = false;
                    return Unit.INSTANCE;
                }
                floatRef.element += f3;
                if (z) {
                    if (((Number) animationScope.getValue()).floatValue() > f2) {
                        animationScope.cancelAnimation();
                    }
                } else if (((Number) animationScope.getValue()).floatValue() < (-f2)) {
                    animationScope.cancelAnimation();
                }
                if (z) {
                    if (intRef.element >= 2 && i - lazyLayoutScrollScope.getLastVisibleItemIndex() > i2) {
                        lazyLayoutScrollScope.snapToItem(i - i2, 0);
                    }
                } else if (intRef.element >= 2 && lazyLayoutScrollScope.getFirstVisibleItemIndex() - i > i2) {
                    lazyLayoutScrollScope.snapToItem(i2 + i, 0);
                }
            }
        }
        if (animateScrollToItem$isOvershot(z, lazyLayoutScrollScope, i, i3)) {
            lazyLayoutScrollScope.snapToItem(i, i3);
            booleanRef.element = false;
            animationScope.cancelAnimation();
            return Unit.INSTANCE;
        }
        if (isItemVisible(lazyLayoutScrollScope, i)) {
            throw new ItemFoundInScroll(LazyLayoutScrollScope.CC.calculateDistanceTo$default(lazyLayoutScrollScope, i, 0, 2, null), (AnimationState) objectRef.element);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animateScrollToItem$lambda$19(float f, Ref.FloatRef floatRef, LazyLayoutScrollScope lazyLayoutScrollScope, AnimationScope animationScope) {
        float f2 = 0.0f;
        if (f > 0.0f) {
            f2 = RangesKt.coerceAtMost(((Number) animationScope.getValue()).floatValue(), f);
        } else if (f < 0.0f) {
            f2 = RangesKt.coerceAtLeast(((Number) animationScope.getValue()).floatValue(), f);
        }
        float f3 = f2 - floatRef.element;
        if (f3 != lazyLayoutScrollScope.scrollBy(f3) || f2 != ((Number) animationScope.getValue()).floatValue()) {
            animationScope.cancelAnimation();
        }
        floatRef.element += f3;
        return Unit.INSTANCE;
    }
}
