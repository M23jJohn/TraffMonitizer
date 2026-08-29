package com.traffmonetizer.client.ui.main.home;

import androidx.core.view.MotionEventCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: StatsViewModel.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.traffmonetizer.client.ui.main.home.StatsViewModel", f = "StatsViewModel.kt", i = {1}, l = {MotionEventCompat.AXIS_GENERIC_11, MotionEventCompat.AXIS_GENERIC_12}, m = "requestData", n = {"balance"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
final class StatsViewModel$requestData$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StatsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StatsViewModel$requestData$1(StatsViewModel statsViewModel, Continuation<? super StatsViewModel$requestData$1> continuation) {
        super(continuation);
        this.this$0 = statsViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.requestData(this);
    }
}
