package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import com.traffmonetizer.client.BuildConfig;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* compiled from: Clickable.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.AbstractClickableNode$handlePressInteraction$2$1", f = "Clickable.kt", i = {0, 1, 2}, l = {1725, 1727, 1734, 1735, 1745}, m = "invokeSuspend", n = {"delayJob", "success", BuildConfig.BUILD_TYPE}, s = {"L$0", "Z$0", "L$0"})
/* loaded from: classes.dex */
final class AbstractClickableNode$handlePressInteraction$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ long $offset;
    final /* synthetic */ PressGestureScope $this_handlePressInteraction;
    private /* synthetic */ Object L$0;
    boolean Z$0;
    int label;
    final /* synthetic */ AbstractClickableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AbstractClickableNode$handlePressInteraction$2$1(PressGestureScope pressGestureScope, long j, MutableInteractionSource mutableInteractionSource, AbstractClickableNode abstractClickableNode, Continuation<? super AbstractClickableNode$handlePressInteraction$2$1> continuation) {
        super(2, continuation);
        this.$this_handlePressInteraction = pressGestureScope;
        this.$offset = j;
        this.$interactionSource = mutableInteractionSource;
        this.this$0 = abstractClickableNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AbstractClickableNode$handlePressInteraction$2$1 abstractClickableNode$handlePressInteraction$2$1 = new AbstractClickableNode$handlePressInteraction$2$1(this.$this_handlePressInteraction, this.$offset, this.$interactionSource, this.this$0, continuation);
        abstractClickableNode$handlePressInteraction$2$1.L$0 = obj;
        return abstractClickableNode$handlePressInteraction$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AbstractClickableNode$handlePressInteraction$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x00be, code lost:
    
        if (r16.$interactionSource.emit(r2, r16) != r1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00e6, code lost:
    
        if (r4.emit(r5, r16) == r1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006d, code lost:
    
        if (r7 == r1) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0090  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Job launch$default;
        Object tryAwaitRelease;
        PressInteraction.Press press;
        PressInteraction.Cancel cancel;
        boolean z;
        PressInteraction.Release release;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            launch$default = BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, null, null, new AbstractClickableNode$handlePressInteraction$2$1$delayJob$1(this.this$0, this.$offset, this.$interactionSource, null), 3, null);
            this.L$0 = launch$default;
            this.label = 1;
            tryAwaitRelease = this.$this_handlePressInteraction.tryAwaitRelease(this);
        } else if (i == 1) {
            launch$default = (Job) this.L$0;
            ResultKt.throwOnFailure(obj);
            tryAwaitRelease = obj;
        } else {
            if (i == 2) {
                z = this.Z$0;
                ResultKt.throwOnFailure(obj);
                if (z) {
                    PressInteraction.Press press2 = new PressInteraction.Press(this.$offset, null);
                    PressInteraction.Release release2 = new PressInteraction.Release(press2);
                    this.L$0 = release2;
                    this.label = 3;
                    if (this.$interactionSource.emit(press2, this) != coroutine_suspended) {
                        release = release2;
                        this.L$0 = null;
                        this.label = 4;
                    }
                    return coroutine_suspended;
                }
                this.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            }
            if (i != 3) {
                if (i != 4 && i != 5) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            }
            release = (PressInteraction.Release) this.L$0;
            ResultKt.throwOnFailure(obj);
            this.L$0 = null;
            this.label = 4;
        }
        boolean booleanValue = ((Boolean) tryAwaitRelease).booleanValue();
        if (!launch$default.isActive()) {
            press = this.this$0.pressInteraction;
            if (press != null) {
                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                if (booleanValue) {
                    cancel = new PressInteraction.Release(press);
                } else {
                    cancel = new PressInteraction.Cancel(press);
                }
                this.L$0 = null;
                this.label = 5;
            }
            this.this$0.pressInteraction = null;
            return Unit.INSTANCE;
        }
        this.L$0 = null;
        this.Z$0 = booleanValue;
        this.label = 2;
        if (JobKt.cancelAndJoin(launch$default, this) != coroutine_suspended) {
            z = booleanValue;
            if (z) {
            }
            this.this$0.pressInteraction = null;
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }
}
