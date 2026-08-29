package com.tm;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;

/* renamed from: com.tm.h2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0102h2 extends SuspendLambda implements Function2 {
    public C0116j2 a;
    public ReceiveChannel b;
    public ChannelIterator c;
    public int d;
    public final /* synthetic */ Channel e;
    public final /* synthetic */ C0116j2 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0102h2(Channel channel, C0116j2 c0116j2, Continuation continuation) {
        super(2, continuation);
        this.e = channel;
        this.f = c0116j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0102h2(this.e, this.f, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0102h2(this.e, this.f, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0037 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0040 A[Catch: all -> 0x0051, TRY_LEAVE, TryCatch #0 {all -> 0x0051, blocks: (B:6:0x0011, B:7:0x0038, B:9:0x0040, B:10:0x0029, B:20:0x0024), top: B:2:0x0007 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0035 -> B:7:0x0038). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ReceiveChannel receiveChannel;
        ChannelIterator it;
        C0116j2 c0116j2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.d;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                receiveChannel = this.e;
                C0116j2 c0116j22 = this.f;
                it = receiveChannel.iterator();
                c0116j2 = c0116j22;
                this.a = c0116j2;
                this.b = receiveChannel;
                this.c = it;
                this.d = 1;
                obj = it.hasNext(this);
                if (obj == coroutine_suspended) {
                }
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = this.c;
                receiveChannel = this.b;
                c0116j2 = this.a;
                ResultKt.throwOnFailure(obj);
                if (((Boolean) obj).booleanValue()) {
                    C0116j2.a(c0116j2, (Z0) it.next());
                    this.a = c0116j2;
                    this.b = receiveChannel;
                    this.c = it;
                    this.d = 1;
                    obj = it.hasNext(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return Unit.INSTANCE;
                    }
                }
            }
        } finally {
        }
    }
}
