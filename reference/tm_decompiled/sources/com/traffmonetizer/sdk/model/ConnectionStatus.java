package com.traffmonetizer.sdk.model;

import androidx.autofill.HintConstants;
import com.tm.C0086f0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \t2\u00020\u0001:\u0001\nB\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0006\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/traffmonetizer/sdk/model/ConnectionStatus;", "", "", HintConstants.AUTOFILL_HINT_NAME, "<init>", "(Ljava/lang/String;)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Companion", "com/tm/f0", "Lcom/traffmonetizer/sdk/model/ConnectionConnected;", "Lcom/traffmonetizer/sdk/model/ConnectionDisconnected;", "Lcom/traffmonetizer/sdk/model/LoadBalancerResolving;", "Lcom/traffmonetizer/sdk/model/SocketConnecting;", "Lcom/traffmonetizer/sdk/model/HelloConnecting;", "Lcom/traffmonetizer/sdk/model/ConnectionFailed;", "TraffmonetizerSDK-v1.2.11_internalRelease"}, k = 1, mv = {1, 6, 0})
/* loaded from: classes3.dex */
public abstract class ConnectionStatus {
    public static final C0086f0 Companion = new C0086f0();
    private final String name;

    public /* synthetic */ ConnectionStatus(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public final String getName() {
        return this.name;
    }

    private ConnectionStatus(String str) {
        this.name = str;
    }
}
