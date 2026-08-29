package androidx.core.os;

import java.lang.Throwable;

/* loaded from: classes3.dex */
public interface OutcomeReceiverCompat<R, E extends Throwable> {

    /* renamed from: androidx.core.os.OutcomeReceiverCompat$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onError(OutcomeReceiverCompat _this, Throwable th) {
        }
    }

    void onError(E e);

    void onResult(R r);
}
