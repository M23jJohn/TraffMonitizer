package androidx.compose.ui;

import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.ui.SessionMutex;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: SessionMutex.kt */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.SessionMutex$withSessionCancellingPrevious$2", f = "SessionMutex.kt", i = {0, 1}, l = {LockFreeTaskQueueCore.CLOSED_SHIFT, 63}, m = "invokeSuspend", n = {"newSession", "newSession"}, s = {"L$0", "L$0"})
/* loaded from: classes.dex */
final class SessionMutex$withSessionCancellingPrevious$2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
    final /* synthetic */ AtomicReference<SessionMutex.Session<T>> $arg0;
    final /* synthetic */ Function2<T, Continuation<? super R>, Object> $session;
    final /* synthetic */ Function1<CoroutineScope, T> $sessionInitializer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SessionMutex$withSessionCancellingPrevious$2(Function1<? super CoroutineScope, ? extends T> function1, AtomicReference<SessionMutex.Session<T>> atomicReference, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super SessionMutex$withSessionCancellingPrevious$2> continuation) {
        super(2, continuation);
        this.$sessionInitializer = function1;
        this.$arg0 = atomicReference;
        this.$session = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SessionMutex$withSessionCancellingPrevious$2 sessionMutex$withSessionCancellingPrevious$2 = new SessionMutex$withSessionCancellingPrevious$2(this.$sessionInitializer, this.$arg0, this.$session, continuation);
        sessionMutex$withSessionCancellingPrevious$2.L$0 = obj;
        return sessionMutex$withSessionCancellingPrevious$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
        return ((SessionMutex$withSessionCancellingPrevious$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x005e, code lost:
    
        if (kotlinx.coroutines.JobKt.cancelAndJoin(r8, r7) == r0) goto L23;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        SessionMutex.Session session;
        SessionMutex.Session session2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                session = new SessionMutex.Session(JobKt.getJob(coroutineScope.getCoroutineContext()), this.$sessionInitializer.invoke(coroutineScope));
                SessionMutex.Session session3 = (SessionMutex.Session) this.$arg0.getAndSet(session);
                if (session3 != null && (r8 = session3.getJob()) != null) {
                    this.L$0 = session;
                    this.label = 1;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    session2 = (SessionMutex.Session) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.$arg0, session2, null);
                        return obj;
                    } catch (Throwable th) {
                        th = th;
                        MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.$arg0, session2, null);
                        throw th;
                    }
                }
                session = (SessionMutex.Session) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            Function2<T, Continuation<? super R>, Object> function2 = this.$session;
            Object value = session.getValue();
            this.L$0 = session;
            this.label = 2;
            obj = function2.invoke(value, this);
            if (obj != coroutine_suspended) {
                session2 = session;
                MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.$arg0, session2, null);
                return obj;
            }
            return coroutine_suspended;
        } catch (Throwable th2) {
            th = th2;
            session2 = session;
            MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.$arg0, session2, null);
            throw th;
        }
    }
}
