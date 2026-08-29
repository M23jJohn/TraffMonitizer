package kotlin.time.jdk8;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* compiled from: DurationConversions.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b¢\u0006\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0002*\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"toKotlinDuration", "Lkotlin/time/Duration;", "Ljava/time/Duration;", "(Ljava/time/Duration;)J", "toJavaDuration", "toJavaDuration-LRDsOJo", "(J)Ljava/time/Duration;", "kotlin-stdlib-jdk8"}, k = 2, mv = {2, 2, 0}, pn = "kotlin.time", xi = 48)
/* loaded from: classes3.dex */
public final class DurationConversionsJDK8Kt {
    private static final long toKotlinDuration(Duration duration) {
        long seconds;
        int nano;
        Intrinsics.checkNotNullParameter(duration, "<this>");
        seconds = duration.getSeconds();
        long duration2 = DurationKt.toDuration(seconds, DurationUnit.SECONDS);
        nano = duration.getNano();
        return kotlin.time.Duration.m9504plusLRDsOJo(duration2, DurationKt.toDuration(nano, DurationUnit.NANOSECONDS));
    }

    /* renamed from: toJavaDuration-LRDsOJo, reason: not valid java name */
    private static final Duration m9611toJavaDurationLRDsOJo(long j) {
        Duration ofSeconds;
        ofSeconds = Duration.ofSeconds(kotlin.time.Duration.m9489getInWholeSecondsimpl(j), kotlin.time.Duration.m9491getNanosecondsComponentimpl(j));
        Intrinsics.checkNotNullExpressionValue(ofSeconds, "toComponents-impl(...)");
        return ofSeconds;
    }
}
