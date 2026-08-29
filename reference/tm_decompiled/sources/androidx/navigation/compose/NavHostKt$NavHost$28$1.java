package androidx.navigation.compose;

import androidx.compose.animation.core.SeekableTransitionState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.State;
import androidx.navigation.NavBackStackEntry;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: NavHost.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.navigation.compose.NavHostKt$NavHost$28$1", f = "NavHost.kt", i = {}, l = {636}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class NavHostKt$NavHost$28$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<List<NavBackStackEntry>> $currentBackStack$delegate;
    final /* synthetic */ MutableFloatState $progress$delegate;
    final /* synthetic */ SeekableTransitionState<NavBackStackEntry> $transitionState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    NavHostKt$NavHost$28$1(SeekableTransitionState<NavBackStackEntry> seekableTransitionState, State<? extends List<NavBackStackEntry>> state, MutableFloatState mutableFloatState, Continuation<? super NavHostKt$NavHost$28$1> continuation) {
        super(2, continuation);
        this.$transitionState = seekableTransitionState;
        this.$currentBackStack$delegate = state;
        this.$progress$delegate = mutableFloatState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NavHostKt$NavHost$28$1(this.$transitionState, this.$currentBackStack$delegate, this.$progress$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NavHostKt$NavHost$28$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List NavHost$lambda$38;
        List NavHost$lambda$382;
        List NavHost$lambda$383;
        float NavHost$lambda$40;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NavHost$lambda$38 = NavHostKt.NavHost$lambda$38(this.$currentBackStack$delegate);
            if (NavHost$lambda$38.size() > 1) {
                NavHost$lambda$382 = NavHostKt.NavHost$lambda$38(this.$currentBackStack$delegate);
                NavHost$lambda$383 = NavHostKt.NavHost$lambda$38(this.$currentBackStack$delegate);
                NavBackStackEntry navBackStackEntry = (NavBackStackEntry) NavHost$lambda$382.get(NavHost$lambda$383.size() - 2);
                SeekableTransitionState<NavBackStackEntry> seekableTransitionState = this.$transitionState;
                NavHost$lambda$40 = NavHostKt.NavHost$lambda$40(this.$progress$delegate);
                this.label = 1;
                if (seekableTransitionState.seekTo(NavHost$lambda$40, navBackStackEntry, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
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
