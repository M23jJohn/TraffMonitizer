package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.LongPressResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.core.location.LocationRequestCompat;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: TapGestureDetector.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2", f = "TapGestureDetector.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TapGestureDetectorKt$detectTapGestures$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Offset, Unit> $onDoubleTap;
    final /* synthetic */ Function1<Offset, Unit> $onLongPress;
    final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
    final /* synthetic */ Function1<Offset, Unit> $onTap;
    final /* synthetic */ PointerInputScope $this_detectTapGestures;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TapGestureDetectorKt$detectTapGestures$2(PointerInputScope pointerInputScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function12, Function1<? super Offset, Unit> function13, Continuation<? super TapGestureDetectorKt$detectTapGestures$2> continuation) {
        super(2, continuation);
        this.$this_detectTapGestures = pointerInputScope;
        this.$onPress = function3;
        this.$onLongPress = function1;
        this.$onDoubleTap = function12;
        this.$onTap = function13;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TapGestureDetectorKt$detectTapGestures$2 tapGestureDetectorKt$detectTapGestures$2 = new TapGestureDetectorKt$detectTapGestures$2(this.$this_detectTapGestures, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, continuation);
        tapGestureDetectorKt$detectTapGestures$2.L$0 = obj;
        return tapGestureDetectorKt$detectTapGestures$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TapGestureDetectorKt$detectTapGestures$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            PressGestureScopeImpl pressGestureScopeImpl = new PressGestureScopeImpl(this.$this_detectTapGestures);
            this.label = 1;
            if (ForEachGestureKt.awaitEachGesture(this.$this_detectTapGestures, new AnonymousClass1(coroutineScope, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, pressGestureScopeImpl, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* compiled from: TapGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1", f = "TapGestureDetector.kt", i = {0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 5, 6, 6, 6, 6, 7}, l = {105, 116, 119, 122, 149, 167, 169, 180}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "resetJob", "$this$awaitEachGesture", "down", "resetJob", "resetJob", "$this$awaitEachGesture", "upOrCancel", "cancelOrReleaseJob", "resetJob", "upOrCancel", "$this$awaitEachGesture", "resetJob", "upOrCancel", "secondDown", "resetJob"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0"})
    /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineScope $$this$coroutineScope;
        final /* synthetic */ Function1<Offset, Unit> $onDoubleTap;
        final /* synthetic */ Function1<Offset, Unit> $onLongPress;
        final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
        final /* synthetic */ Function1<Offset, Unit> $onTap;
        final /* synthetic */ PressGestureScopeImpl $pressScope;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(CoroutineScope coroutineScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function12, Function1<? super Offset, Unit> function13, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$$this$coroutineScope = coroutineScope;
            this.$onPress = function3;
            this.$onLongPress = function1;
            this.$onDoubleTap = function12;
            this.$onTap = function13;
            this.$pressScope = pressGestureScopeImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$coroutineScope, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, this.$pressScope, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:94:0x0117, code lost:
        
            if (r2 == r1) goto L84;
         */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0254  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x028d  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x02a2  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x02c5  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x01cf  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x01e0  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0170  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x019c  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0184  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x00d5  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x00f0  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0107  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object awaitFirstDown$default;
            AwaitPointerEventScope awaitPointerEventScope;
            PointerInputChange pointerInputChange;
            CoroutineStart coroutineStartForCurrentDispatchBehavior;
            Job launch$default;
            Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3;
            Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function32;
            Object waitForLongPress$default;
            Object waitForUpOrCancellation$default;
            AwaitPointerEventScope awaitPointerEventScope2;
            PointerInputChange pointerInputChange2;
            Job launchAwaitingReset$default;
            Object awaitSecondDown;
            PointerInputChange pointerInputChange3;
            Job job;
            AwaitPointerEventScope awaitPointerEventScope3;
            Object consumeUntilUp;
            Job job2;
            PointerInputChange pointerInputChange4;
            CoroutineStart coroutineStartForCurrentDispatchBehavior2;
            Job launch$default2;
            Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function33;
            Object waitForLongPress$default2;
            PointerInputChange pointerInputChange5;
            Object waitForUpOrCancellation$default2;
            PointerInputChange pointerInputChange6;
            PointerInputChange pointerInputChange7;
            LongPressResult longPressResult;
            Object consumeUntilUp2;
            Job job3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    AwaitPointerEventScope awaitPointerEventScope4 = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = awaitPointerEventScope4;
                    this.label = 1;
                    awaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope4, false, null, this, 3, null);
                    if (awaitFirstDown$default != coroutine_suspended) {
                        awaitPointerEventScope = awaitPointerEventScope4;
                        pointerInputChange = (PointerInputChange) awaitFirstDown$default;
                        pointerInputChange.consume();
                        CoroutineScope coroutineScope = this.$$this$coroutineScope;
                        coroutineStartForCurrentDispatchBehavior = TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior();
                        launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, coroutineStartForCurrentDispatchBehavior, new TapGestureDetectorKt$detectTapGestures$2$1$resetJob$1(this.$pressScope, null), 1, null);
                        function3 = this.$onPress;
                        function32 = TapGestureDetectorKt.NoPressGesture;
                        if (function3 != function32) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, launch$default, null, new C00071(this.$onPress, this.$pressScope, pointerInputChange, null), 2, null);
                        }
                        if (this.$onLongPress == null) {
                            this.L$0 = awaitPointerEventScope;
                            this.L$1 = pointerInputChange;
                            this.L$2 = launch$default;
                            this.label = 3;
                            waitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope, null, this, 1, null);
                            break;
                        } else {
                            this.L$0 = awaitPointerEventScope;
                            this.L$1 = launch$default;
                            this.label = 2;
                            waitForUpOrCancellation$default = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope, null, this, 1, null);
                            if (waitForUpOrCancellation$default != coroutine_suspended) {
                                awaitPointerEventScope2 = awaitPointerEventScope;
                                pointerInputChange2 = (PointerInputChange) waitForUpOrCancellation$default;
                                Job job4 = launch$default;
                                if (pointerInputChange2 == null) {
                                    launchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job4, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                                } else {
                                    pointerInputChange2.consume();
                                    launchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job4, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                                }
                                if (pointerInputChange2 != null) {
                                    if (this.$onDoubleTap == null) {
                                        Function1<Offset, Unit> function1 = this.$onTap;
                                        if (function1 != null) {
                                            function1.invoke(Offset.m4332boximpl(pointerInputChange2.getPosition()));
                                        }
                                    } else {
                                        this.L$0 = awaitPointerEventScope2;
                                        this.L$1 = pointerInputChange2;
                                        this.L$2 = launchAwaitingReset$default;
                                        this.label = 5;
                                        awaitSecondDown = TapGestureDetectorKt.awaitSecondDown(awaitPointerEventScope2, pointerInputChange2, this);
                                        if (awaitSecondDown != coroutine_suspended) {
                                            AwaitPointerEventScope awaitPointerEventScope5 = awaitPointerEventScope2;
                                            pointerInputChange3 = pointerInputChange2;
                                            job = launchAwaitingReset$default;
                                            awaitPointerEventScope3 = awaitPointerEventScope5;
                                            pointerInputChange4 = (PointerInputChange) awaitSecondDown;
                                            if (pointerInputChange4 != null) {
                                                Function1<Offset, Unit> function12 = this.$onTap;
                                                if (function12 != null) {
                                                    function12.invoke(Offset.m4332boximpl(pointerInputChange3.getPosition()));
                                                }
                                            } else {
                                                CoroutineScope coroutineScope2 = this.$$this$coroutineScope;
                                                coroutineStartForCurrentDispatchBehavior2 = TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior();
                                                launch$default2 = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, coroutineStartForCurrentDispatchBehavior2, new AnonymousClass5(job, this.$pressScope, null), 1, null);
                                                Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function34 = this.$onPress;
                                                function33 = TapGestureDetectorKt.NoPressGesture;
                                                if (function34 != function33) {
                                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, launch$default2, null, new AnonymousClass6(this.$onPress, this.$pressScope, pointerInputChange4, null), 2, null);
                                                }
                                                if (this.$onLongPress == null) {
                                                    this.L$0 = launch$default2;
                                                    this.L$1 = pointerInputChange3;
                                                    this.L$2 = null;
                                                    this.label = 6;
                                                    waitForUpOrCancellation$default2 = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope3, null, this, 1, null);
                                                    if (waitForUpOrCancellation$default2 != coroutine_suspended) {
                                                        pointerInputChange6 = pointerInputChange3;
                                                        pointerInputChange7 = (PointerInputChange) waitForUpOrCancellation$default2;
                                                        Job job5 = launch$default2;
                                                        if (pointerInputChange7 != null) {
                                                            pointerInputChange7.consume();
                                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                            this.$onDoubleTap.invoke(Offset.m4332boximpl(pointerInputChange7.getPosition()));
                                                        } else {
                                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                            Function1<Offset, Unit> function13 = this.$onTap;
                                                            if (function13 != null) {
                                                                function13.invoke(Offset.m4332boximpl(pointerInputChange6.getPosition()));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    this.L$0 = awaitPointerEventScope3;
                                                    this.L$1 = launch$default2;
                                                    this.L$2 = pointerInputChange3;
                                                    this.L$3 = pointerInputChange4;
                                                    this.label = 7;
                                                    waitForLongPress$default2 = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope3, null, this, 1, null);
                                                    if (waitForLongPress$default2 != coroutine_suspended) {
                                                        pointerInputChange5 = pointerInputChange3;
                                                        longPressResult = (LongPressResult) waitForLongPress$default2;
                                                        if (Intrinsics.areEqual(longPressResult, LongPressResult.Success.INSTANCE)) {
                                                            if (longPressResult instanceof LongPressResult.Released) {
                                                                pointerInputChange7 = ((LongPressResult.Released) longPressResult).getFinalUpChange();
                                                                pointerInputChange6 = pointerInputChange5;
                                                            } else {
                                                                if (!(longPressResult instanceof LongPressResult.Canceled)) {
                                                                    throw new NoWhenBranchMatchedException();
                                                                }
                                                                pointerInputChange6 = pointerInputChange5;
                                                                pointerInputChange7 = null;
                                                            }
                                                            Job job52 = launch$default2;
                                                            if (pointerInputChange7 != null) {
                                                            }
                                                        } else {
                                                            this.$onLongPress.invoke(Offset.m4332boximpl(pointerInputChange4.getPosition()));
                                                            this.L$0 = launch$default2;
                                                            this.L$1 = null;
                                                            this.L$2 = null;
                                                            this.L$3 = null;
                                                            this.label = 8;
                                                            consumeUntilUp2 = TapGestureDetectorKt.consumeUntilUp(awaitPointerEventScope3, this);
                                                            if (consumeUntilUp2 != coroutine_suspended) {
                                                                job3 = launch$default2;
                                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job3, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                                                return Unit.INSTANCE;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    }
                    return coroutine_suspended;
                case 1:
                    AwaitPointerEventScope awaitPointerEventScope6 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    awaitPointerEventScope = awaitPointerEventScope6;
                    awaitFirstDown$default = obj;
                    pointerInputChange = (PointerInputChange) awaitFirstDown$default;
                    pointerInputChange.consume();
                    CoroutineScope coroutineScope3 = this.$$this$coroutineScope;
                    coroutineStartForCurrentDispatchBehavior = TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior();
                    launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, coroutineStartForCurrentDispatchBehavior, new TapGestureDetectorKt$detectTapGestures$2$1$resetJob$1(this.$pressScope, null), 1, null);
                    function3 = this.$onPress;
                    function32 = TapGestureDetectorKt.NoPressGesture;
                    if (function3 != function32) {
                    }
                    if (this.$onLongPress == null) {
                    }
                    return coroutine_suspended;
                case 2:
                    Job job6 = (Job) this.L$1;
                    awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    launch$default = job6;
                    waitForUpOrCancellation$default = obj;
                    pointerInputChange2 = (PointerInputChange) waitForUpOrCancellation$default;
                    Job job42 = launch$default;
                    if (pointerInputChange2 == null) {
                    }
                    if (pointerInputChange2 != null) {
                    }
                    return Unit.INSTANCE;
                case 3:
                    Job job7 = (Job) this.L$2;
                    pointerInputChange = (PointerInputChange) this.L$1;
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    launch$default = job7;
                    waitForLongPress$default = obj;
                    LongPressResult longPressResult2 = (LongPressResult) waitForLongPress$default;
                    if (!Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                        if (longPressResult2 instanceof LongPressResult.Released) {
                            pointerInputChange2 = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                        } else {
                            if (!(longPressResult2 instanceof LongPressResult.Canceled)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            pointerInputChange2 = null;
                        }
                        awaitPointerEventScope2 = awaitPointerEventScope;
                        Job job422 = launch$default;
                        if (pointerInputChange2 == null) {
                        }
                        if (pointerInputChange2 != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    this.$onLongPress.invoke(Offset.m4332boximpl(pointerInputChange.getPosition()));
                    this.L$0 = launch$default;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.label = 4;
                    consumeUntilUp = TapGestureDetectorKt.consumeUntilUp(awaitPointerEventScope, this);
                    if (consumeUntilUp != coroutine_suspended) {
                        job2 = launch$default;
                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass2(this.$pressScope, null), 2, null);
                        return Unit.INSTANCE;
                    }
                    return coroutine_suspended;
                case 4:
                    Job job8 = (Job) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    job2 = job8;
                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass2(this.$pressScope, null), 2, null);
                    return Unit.INSTANCE;
                case 5:
                    job = (Job) this.L$2;
                    pointerInputChange3 = (PointerInputChange) this.L$1;
                    awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    awaitSecondDown = obj;
                    pointerInputChange4 = (PointerInputChange) awaitSecondDown;
                    if (pointerInputChange4 != null) {
                    }
                    break;
                case 6:
                    pointerInputChange6 = (PointerInputChange) this.L$1;
                    Job job9 = (Job) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    launch$default2 = job9;
                    waitForUpOrCancellation$default2 = obj;
                    pointerInputChange7 = (PointerInputChange) waitForUpOrCancellation$default2;
                    Job job522 = launch$default2;
                    if (pointerInputChange7 != null) {
                    }
                    return Unit.INSTANCE;
                case 7:
                    PointerInputChange pointerInputChange8 = (PointerInputChange) this.L$3;
                    pointerInputChange5 = (PointerInputChange) this.L$2;
                    Job job10 = (Job) this.L$1;
                    awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    pointerInputChange4 = pointerInputChange8;
                    launch$default2 = job10;
                    waitForLongPress$default2 = obj;
                    longPressResult = (LongPressResult) waitForLongPress$default2;
                    if (Intrinsics.areEqual(longPressResult, LongPressResult.Success.INSTANCE)) {
                    }
                    break;
                case 8:
                    Job job11 = (Job) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    job3 = job11;
                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job3, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$1", f = "TapGestureDetector.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputChange $down;
            final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00071(Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, Continuation<? super C00071> continuation) {
                super(2, continuation);
                this.$onPress = function3;
                this.$pressScope = pressGestureScopeImpl;
                this.$down = pointerInputChange;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00071(this.$onPress, this.$pressScope, this.$down, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3 = this.$onPress;
                    PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                    Offset m4332boximpl = Offset.m4332boximpl(this.$down.getPosition());
                    this.label = 1;
                    if (function3.invoke(pressGestureScopeImpl, m4332boximpl, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$2", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$pressScope, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$pressScope.release();
                return Unit.INSTANCE;
            }
        }

        /* compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$3", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$3, reason: invalid class name */
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.$pressScope, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$pressScope.cancel();
                return Unit.INSTANCE;
            }
        }

        /* compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$4", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$4, reason: invalid class name */
        static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass4(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass4> continuation) {
                super(2, continuation);
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass4(this.$pressScope, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$pressScope.release();
                return Unit.INSTANCE;
            }
        }

        /* compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$5", f = "TapGestureDetector.kt", i = {}, l = {157, 158}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$5, reason: invalid class name */
        static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Job $cancelOrReleaseJob;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass5(Job job, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass5> continuation) {
                super(2, continuation);
                this.$cancelOrReleaseJob = job;
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass5(this.$cancelOrReleaseJob, this.$pressScope, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:13:0x003a, code lost:
            
                if (r4.$pressScope.reset(r4) == r0) goto L15;
             */
            /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
            
                return r0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
            
                if (r4.$cancelOrReleaseJob.join(r4) == r0) goto L15;
             */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                this.label = 2;
            }
        }

        /* compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$6", f = "TapGestureDetector.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$6, reason: invalid class name */
        static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            final /* synthetic */ PointerInputChange $secondDown;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass6(Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, Continuation<? super AnonymousClass6> continuation) {
                super(2, continuation);
                this.$onPress = function3;
                this.$pressScope = pressGestureScopeImpl;
                this.$secondDown = pointerInputChange;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass6(this.$onPress, this.$pressScope, this.$secondDown, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3 = this.$onPress;
                    PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                    Offset m4332boximpl = Offset.m4332boximpl(this.$secondDown.getPosition());
                    this.label = 1;
                    if (function3.invoke(pressGestureScopeImpl, m4332boximpl, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$7", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$7, reason: invalid class name */
        static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass7(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass7> continuation) {
                super(2, continuation);
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass7(this.$pressScope, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$pressScope.release();
                return Unit.INSTANCE;
            }
        }

        /* compiled from: TapGestureDetector.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$8", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$8, reason: invalid class name */
        static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass8(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass8> continuation) {
                super(2, continuation);
                this.$pressScope = pressGestureScopeImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass8(this.$pressScope, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$pressScope.cancel();
                return Unit.INSTANCE;
            }
        }
    }
}
