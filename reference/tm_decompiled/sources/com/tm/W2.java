package com.tm;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class W2 extends Throwable {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W2(String id) {
        super("sessionId=" + id);
        Intrinsics.checkNotNullParameter(id, "id");
    }
}
