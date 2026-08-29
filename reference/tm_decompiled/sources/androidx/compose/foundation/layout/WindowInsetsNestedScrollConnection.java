package androidx.compose.foundation.layout;

import android.graphics.Insets;
import android.os.CancellationSignal;
import android.view.View;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Velocity;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* compiled from: WindowInsetsConnection.android.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010#\u001a\u0004\u0018\u00010\u0016H\u0082@¢\u0006\u0002\u0010$J\u001f\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+J'\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b.\u0010/J\u001f\u00100\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u00101\u001a\u00020\u001cH\u0002¢\u0006\u0004\b2\u00103J\u0018\u00104\u001a\u0002052\u0006\u0010'\u001a\u000205H\u0096@¢\u0006\u0004\b6\u00107J \u00108\u001a\u0002052\u0006\u0010-\u001a\u0002052\u0006\u0010'\u001a\u000205H\u0096@¢\u0006\u0004\b9\u0010:J(\u0010;\u001a\u0002052\u0006\u0010'\u001a\u0002052\u0006\u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020\u0018H\u0082@¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020 2\u0006\u0010A\u001a\u00020\u001cH\u0002J\u0018\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u00020EH\u0016J\u0006\u0010F\u001a\u00020 J\u0010\u0010G\u001a\u00020 2\u0006\u0010C\u001a\u00020\u0016H\u0016J\u0012\u0010H\u001a\u00020 2\b\u0010C\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010I\u001a\u00020 H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroid/view/WindowInsetsAnimationControlListener;", "windowInsets", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "view", "Landroid/view/View;", "sideCalculator", "Landroidx/compose/foundation/layout/SideCalculator;", "density", "Landroidx/compose/ui/unit/Density;", "<init>", "(Landroidx/compose/foundation/layout/AndroidWindowInsets;Landroid/view/View;Landroidx/compose/foundation/layout/SideCalculator;Landroidx/compose/ui/unit/Density;)V", "getWindowInsets", "()Landroidx/compose/foundation/layout/AndroidWindowInsets;", "getView", "()Landroid/view/View;", "getSideCalculator", "()Landroidx/compose/foundation/layout/SideCalculator;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "animationController", "Landroid/view/WindowInsetsAnimationController;", "isControllerRequested", "", "cancellationSignal", "Landroid/os/CancellationSignal;", "partialConsumption", "", "animationJob", "Lkotlinx/coroutines/Job;", "requestAnimationController", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "getAnimationController", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "scroll", "scrollAmount", "scroll-8S9VItk", "(JF)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fling", "flingAmount", "towardShown", "fling-huYlsQE", "(JFZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adjustInsets", "inset", "onReady", "controller", "types", "", "dispose", "onFinished", "onCancelled", "animationEnded", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class WindowInsetsNestedScrollConnection implements NestedScrollConnection, WindowInsetsAnimationControlListener {
    private WindowInsetsAnimationController animationController;
    private Job animationJob;
    private final CancellationSignal cancellationSignal = new CancellationSignal();
    private CancellableContinuation<? super WindowInsetsAnimationController> continuation;
    private final Density density;
    private boolean isControllerRequested;
    private float partialConsumption;
    private final SideCalculator sideCalculator;
    private final View view;
    private final AndroidWindowInsets windowInsets;

    public WindowInsetsNestedScrollConnection(AndroidWindowInsets androidWindowInsets, View view, SideCalculator sideCalculator, Density density) {
        this.windowInsets = androidWindowInsets;
        this.view = view;
        this.sideCalculator = sideCalculator;
        this.density = density;
    }

    public final AndroidWindowInsets getWindowInsets() {
        return this.windowInsets;
    }

    public final View getView() {
        return this.view;
    }

    public final SideCalculator getSideCalculator() {
        return this.sideCalculator;
    }

    public final Density getDensity() {
        return this.density;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestAnimationController() {
        WindowInsetsController windowInsetsController;
        if (this.isControllerRequested) {
            return;
        }
        this.isControllerRequested = true;
        windowInsetsController = this.view.getWindowInsetsController();
        if (windowInsetsController != null) {
            windowInsetsController.controlWindowInsetsAnimation(this.windowInsets.getType(), -1L, null, this.cancellationSignal, ComponentDialog$$ExternalSyntheticApiModelOutline0.m15m((Object) this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getAnimationController(Continuation<? super WindowInsetsAnimationController> continuation) {
        Object obj = this.animationController;
        if (obj == null) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            this.continuation = cancellableContinuationImpl;
            requestAnimationController();
            obj = cancellableContinuationImpl.getResult();
            if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
        }
        return obj;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public long mo609onPreScrollOzD1aCk(long available, int source) {
        return m962scroll8S9VItk(available, this.sideCalculator.hideMotion(Float.intBitsToFloat((int) (available >> 32)), Float.intBitsToFloat((int) (4294967295L & available))));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo607onPostScrollDzOQY0M(long consumed, long available, int source) {
        return m962scroll8S9VItk(available, this.sideCalculator.showMotion(Float.intBitsToFloat((int) (available >> 32)), Float.intBitsToFloat((int) (4294967295L & available))));
    }

    /* renamed from: scroll-8S9VItk, reason: not valid java name */
    private final long m962scroll8S9VItk(long available, float scrollAmount) {
        Insets hiddenStateInsets;
        Insets shownStateInsets;
        Insets currentInsets;
        Job job = this.animationJob;
        if (job != null) {
            job.cancel((CancellationException) new WindowInsetsAnimationCancelledException());
            this.animationJob = null;
        }
        WindowInsetsAnimationController windowInsetsAnimationController = this.animationController;
        if (scrollAmount != 0.0f) {
            if (this.windowInsets.isVisible() != (scrollAmount > 0.0f) || windowInsetsAnimationController != null) {
                if (windowInsetsAnimationController == null) {
                    this.partialConsumption = 0.0f;
                    requestAnimationController();
                    return this.sideCalculator.mo911consumedOffsetsMKHz9U(available);
                }
                SideCalculator sideCalculator = this.sideCalculator;
                hiddenStateInsets = windowInsetsAnimationController.getHiddenStateInsets();
                int valueOf = sideCalculator.valueOf(hiddenStateInsets);
                SideCalculator sideCalculator2 = this.sideCalculator;
                shownStateInsets = windowInsetsAnimationController.getShownStateInsets();
                int valueOf2 = sideCalculator2.valueOf(shownStateInsets);
                currentInsets = windowInsetsAnimationController.getCurrentInsets();
                int valueOf3 = this.sideCalculator.valueOf(currentInsets);
                if (valueOf3 == (scrollAmount > 0.0f ? valueOf2 : valueOf)) {
                    this.partialConsumption = 0.0f;
                    return Offset.INSTANCE.m4359getZeroF1C5BW0();
                }
                float f = valueOf3 + scrollAmount + this.partialConsumption;
                int coerceIn = RangesKt.coerceIn(Math.round(f), valueOf, valueOf2);
                this.partialConsumption = f - Math.round(f);
                if (coerceIn != valueOf3) {
                    windowInsetsAnimationController.setInsetsAndAlpha(this.sideCalculator.adjustInsets(currentInsets, coerceIn), 1.0f, 0.0f);
                }
                return this.sideCalculator.mo911consumedOffsetsMKHz9U(available);
            }
        }
        return Offset.INSTANCE.m4359getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    public Object mo608onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
        return m961flinghuYlsQE(j, this.sideCalculator.hideMotion(Velocity.m7641getXimpl(j), Velocity.m7642getYimpl(j)), false, continuation);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    public Object mo606onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        return m961flinghuYlsQE(j2, this.sideCalculator.showMotion(Velocity.m7641getXimpl(j2), Velocity.m7642getYimpl(j2)), true, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /* renamed from: fling-huYlsQE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m961flinghuYlsQE(long j, float f, boolean z, Continuation<? super Velocity> continuation) {
        WindowInsetsNestedScrollConnection$fling$1 windowInsetsNestedScrollConnection$fling$1;
        int i;
        Object animationController;
        long j2;
        WindowInsetsAnimationController m16m;
        Insets hiddenStateInsets;
        Insets shownStateInsets;
        Insets currentInsets;
        Ref.FloatRef floatRef;
        long j3;
        long j4;
        float f2 = f;
        if (continuation instanceof WindowInsetsNestedScrollConnection$fling$1) {
            windowInsetsNestedScrollConnection$fling$1 = (WindowInsetsNestedScrollConnection$fling$1) continuation;
            if ((windowInsetsNestedScrollConnection$fling$1.label & Integer.MIN_VALUE) != 0) {
                windowInsetsNestedScrollConnection$fling$1.label -= Integer.MIN_VALUE;
                WindowInsetsNestedScrollConnection$fling$1 windowInsetsNestedScrollConnection$fling$12 = windowInsetsNestedScrollConnection$fling$1;
                Object obj = windowInsetsNestedScrollConnection$fling$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = windowInsetsNestedScrollConnection$fling$12.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Job job = this.animationJob;
                    if (job != null) {
                        job.cancel((CancellationException) new WindowInsetsAnimationCancelledException());
                    }
                    this.animationJob = null;
                    this.partialConsumption = 0.0f;
                    if ((f2 == 0.0f && !z) || (this.animationController == null && this.windowInsets.isVisible() == z)) {
                        return Velocity.m7632boximpl(Velocity.INSTANCE.m7652getZero9UxMQ8M());
                    }
                    windowInsetsNestedScrollConnection$fling$12.J$0 = j;
                    windowInsetsNestedScrollConnection$fling$12.F$0 = f2;
                    windowInsetsNestedScrollConnection$fling$12.label = 1;
                    animationController = getAnimationController(windowInsetsNestedScrollConnection$fling$12);
                    if (animationController != coroutine_suspended) {
                        j2 = j;
                    }
                    return coroutine_suspended;
                }
                if (i != 1) {
                    if (i == 2) {
                        j3 = windowInsetsNestedScrollConnection$fling$12.J$0;
                        floatRef = (Ref.FloatRef) windowInsetsNestedScrollConnection$fling$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        return Velocity.m7632boximpl(this.sideCalculator.mo912consumedVelocityQWom1Mo(j3, floatRef.element));
                    }
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    j4 = windowInsetsNestedScrollConnection$fling$12.J$0;
                    ResultKt.throwOnFailure(obj);
                    return Velocity.m7632boximpl(this.sideCalculator.mo912consumedVelocityQWom1Mo(j4, 0.0f));
                }
                f2 = windowInsetsNestedScrollConnection$fling$12.F$0;
                j2 = windowInsetsNestedScrollConnection$fling$12.J$0;
                ResultKt.throwOnFailure(obj);
                animationController = obj;
                float f3 = f2;
                m16m = ComponentDialog$$ExternalSyntheticApiModelOutline0.m16m(animationController);
                if (m16m != null) {
                    return Velocity.m7632boximpl(Velocity.INSTANCE.m7652getZero9UxMQ8M());
                }
                SideCalculator sideCalculator = this.sideCalculator;
                hiddenStateInsets = m16m.getHiddenStateInsets();
                int valueOf = sideCalculator.valueOf(hiddenStateInsets);
                SideCalculator sideCalculator2 = this.sideCalculator;
                shownStateInsets = m16m.getShownStateInsets();
                int valueOf2 = sideCalculator2.valueOf(shownStateInsets);
                currentInsets = m16m.getCurrentInsets();
                int valueOf3 = this.sideCalculator.valueOf(currentInsets);
                if ((f3 <= 0.0f && valueOf3 == valueOf) || (f3 >= 0.0f && valueOf3 == valueOf2)) {
                    m16m.finish(valueOf3 == valueOf2);
                    this.animationController = null;
                    return Velocity.m7632boximpl(Velocity.INSTANCE.m7652getZero9UxMQ8M());
                }
                SplineBasedFloatDecayAnimationSpec splineBasedFloatDecayAnimationSpec = new SplineBasedFloatDecayAnimationSpec(this.density);
                float flingDistance = valueOf3 + splineBasedFloatDecayAnimationSpec.flingDistance(f3);
                float f4 = valueOf;
                boolean z2 = (flingDistance - f4) / ((float) (valueOf2 - valueOf)) > 0.5f;
                int i2 = z2 ? valueOf2 : valueOf;
                if (flingDistance > valueOf2 || flingDistance < f4) {
                    Ref.FloatRef floatRef2 = new Ref.FloatRef();
                    WindowInsetsNestedScrollConnection$fling$2 windowInsetsNestedScrollConnection$fling$2 = new WindowInsetsNestedScrollConnection$fling$2(this, valueOf3, f3, splineBasedFloatDecayAnimationSpec, valueOf, valueOf2, floatRef2, m16m, z2, null);
                    windowInsetsNestedScrollConnection$fling$12.L$0 = floatRef2;
                    windowInsetsNestedScrollConnection$fling$12.J$0 = j2;
                    windowInsetsNestedScrollConnection$fling$12.label = 2;
                    if (CoroutineScopeKt.coroutineScope(windowInsetsNestedScrollConnection$fling$2, windowInsetsNestedScrollConnection$fling$12) != coroutine_suspended) {
                        floatRef = floatRef2;
                        j3 = j2;
                        return Velocity.m7632boximpl(this.sideCalculator.mo912consumedVelocityQWom1Mo(j3, floatRef.element));
                    }
                } else {
                    WindowInsetsNestedScrollConnection$fling$3 windowInsetsNestedScrollConnection$fling$3 = new WindowInsetsNestedScrollConnection$fling$3(this, valueOf3, i2, f3, m16m, z2, null);
                    windowInsetsNestedScrollConnection$fling$12.J$0 = j2;
                    windowInsetsNestedScrollConnection$fling$12.label = 3;
                    if (CoroutineScopeKt.coroutineScope(windowInsetsNestedScrollConnection$fling$3, windowInsetsNestedScrollConnection$fling$12) != coroutine_suspended) {
                        j4 = j2;
                        return Velocity.m7632boximpl(this.sideCalculator.mo912consumedVelocityQWom1Mo(j4, 0.0f));
                    }
                }
                return coroutine_suspended;
            }
        }
        windowInsetsNestedScrollConnection$fling$1 = new WindowInsetsNestedScrollConnection$fling$1(this, continuation);
        WindowInsetsNestedScrollConnection$fling$1 windowInsetsNestedScrollConnection$fling$122 = windowInsetsNestedScrollConnection$fling$1;
        Object obj2 = windowInsetsNestedScrollConnection$fling$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = windowInsetsNestedScrollConnection$fling$122.label;
        if (i != 0) {
        }
        float f32 = f2;
        m16m = ComponentDialog$$ExternalSyntheticApiModelOutline0.m16m(animationController);
        if (m16m != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adjustInsets(float inset) {
        Insets currentInsets;
        WindowInsetsAnimationController windowInsetsAnimationController = this.animationController;
        if (windowInsetsAnimationController != null) {
            currentInsets = windowInsetsAnimationController.getCurrentInsets();
            windowInsetsAnimationController.setInsetsAndAlpha(this.sideCalculator.adjustInsets(currentInsets, Math.round(inset)), 1.0f, 0.0f);
        }
    }

    public void onReady(WindowInsetsAnimationController controller, int types) {
        this.animationController = controller;
        this.isControllerRequested = false;
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume(controller, new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit onReady$lambda$3;
                    onReady$lambda$3 = WindowInsetsNestedScrollConnection.onReady$lambda$3((Throwable) obj);
                    return onReady$lambda$3;
                }
            });
        }
        this.continuation = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onReady$lambda$3(Throwable th) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit dispose$lambda$4(Throwable th) {
        return Unit.INSTANCE;
    }

    public final void dispose() {
        Insets currentInsets;
        Insets hiddenStateInsets;
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume(null, new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit dispose$lambda$4;
                    dispose$lambda$4 = WindowInsetsNestedScrollConnection.dispose$lambda$4((Throwable) obj);
                    return dispose$lambda$4;
                }
            });
        }
        Job job = this.animationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        WindowInsetsAnimationController windowInsetsAnimationController = this.animationController;
        if (windowInsetsAnimationController != null) {
            currentInsets = windowInsetsAnimationController.getCurrentInsets();
            hiddenStateInsets = windowInsetsAnimationController.getHiddenStateInsets();
            windowInsetsAnimationController.finish(!Intrinsics.areEqual(currentInsets, hiddenStateInsets));
        }
    }

    public void onFinished(WindowInsetsAnimationController controller) {
        animationEnded();
    }

    public void onCancelled(WindowInsetsAnimationController controller) {
        animationEnded();
    }

    private final void animationEnded() {
        boolean isReady;
        WindowInsetsAnimationController windowInsetsAnimationController;
        WindowInsetsAnimationController windowInsetsAnimationController2 = this.animationController;
        if (windowInsetsAnimationController2 != null) {
            isReady = windowInsetsAnimationController2.isReady();
            if (isReady && (windowInsetsAnimationController = this.animationController) != null) {
                windowInsetsAnimationController.finish(this.windowInsets.isVisible());
            }
        }
        this.animationController = null;
        CancellableContinuation<? super WindowInsetsAnimationController> cancellableContinuation = this.continuation;
        if (cancellableContinuation != null) {
            cancellableContinuation.resume(null, new Function1() { // from class: androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit animationEnded$lambda$5;
                    animationEnded$lambda$5 = WindowInsetsNestedScrollConnection.animationEnded$lambda$5((Throwable) obj);
                    return animationEnded$lambda$5;
                }
            });
        }
        this.continuation = null;
        Job job = this.animationJob;
        if (job != null) {
            job.cancel((CancellationException) new WindowInsetsAnimationCancelledException());
        }
        this.animationJob = null;
        this.partialConsumption = 0.0f;
        this.isControllerRequested = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animationEnded$lambda$5(Throwable th) {
        return Unit.INSTANCE;
    }
}
