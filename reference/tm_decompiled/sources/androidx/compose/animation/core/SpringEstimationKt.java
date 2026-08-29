package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: SpringEstimation.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a0\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007\u001a0\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0007\u001a8\u0010\u0002\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0007\u001a0\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a(\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a0\u0010\u0013\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a@\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a9\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u001aH\u0082\b\u001a\r\u0010\u001c\u001a\u00020\u001d*\u00020\tH\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"MAX_LONG_MILLIS", "", "estimateAnimationDurationMillis", "stiffness", "", "dampingRatio", "initialVelocity", "initialDisplacement", "delta", "", "springConstant", "dampingCoefficient", "mass", "estimateUnderDamped", "firstRootReal", "firstRootImaginary", "p0", "v0", "estimateCriticallyDamped", "estimateOverDamped", "secondRootReal", "estimateDurationInternal", "initialPosition", "iterateNewtonsMethod", "x", "fn", "Lkotlin/Function1;", "fnPrime", "isNotFinite", "", "animation-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SpringEstimationKt {
    private static final long MAX_LONG_MILLIS = 9223372036854L;

    public static final long estimateAnimationDurationMillis(float f, float f2, float f3, float f4, float f5) {
        return f2 == 0.0f ? MAX_LONG_MILLIS : estimateAnimationDurationMillis(f, f2, f3, f4, f5);
    }

    public static final long estimateAnimationDurationMillis(double d, double d2, double d3, double d4, double d5) {
        double sqrt = 2.0d * d2 * Math.sqrt(d);
        double d6 = (sqrt * sqrt) - (4.0d * d);
        double sqrt2 = d6 < 0.0d ? 0.0d : Math.sqrt(d6);
        double d7 = -sqrt;
        return estimateDurationInternal((d7 + sqrt2) * 0.5d, (d6 < 0.0d ? Math.sqrt(Math.abs(d6)) : 0.0d) * 0.5d, (d7 - sqrt2) * 0.5d, d2, d3, d4, d5);
    }

    public static final long estimateAnimationDurationMillis(double d, double d2, double d3, double d4, double d5, double d6) {
        double sqrt = d2 / (Math.sqrt(d * d3) * 2.0d);
        double d7 = (d2 * d2) - ((4.0d * d3) * d);
        double d8 = 1.0d / (2.0d * d3);
        double sqrt2 = d7 < 0.0d ? 0.0d : Math.sqrt(d7);
        double d9 = -d2;
        return estimateDurationInternal((d9 + sqrt2) * d8, (d7 < 0.0d ? Math.sqrt(Math.abs(d7)) : 0.0d) * d8, (d9 - sqrt2) * d8, sqrt, d4, d5, d6);
    }

    private static final double estimateUnderDamped(double d, double d2, double d3, double d4, double d5) {
        double d6 = (d4 - (d * d3)) / d2;
        return Math.log(d5 / Math.sqrt((d3 * d3) + (d6 * d6))) / d;
    }

    private static final double estimateCriticallyDamped(double d, double d2, double d3, double d4) {
        double d5;
        double d6 = d4;
        double d7 = d * d2;
        double d8 = d3 - d7;
        double log = Math.log(Math.abs(d6 / d2)) / d;
        double log2 = Math.log(Math.abs(d6 / d8));
        int i = 0;
        double d9 = log2;
        for (int i2 = 0; i2 < 6; i2++) {
            d9 = log2 - Math.log(Math.abs(d9 / d));
        }
        double d10 = d9 / d;
        if (!((Double.doubleToRawLongBits(log) & Long.MAX_VALUE) < 9218868437227405312L)) {
            log = d10;
        } else if ((Double.doubleToRawLongBits(d10) & Long.MAX_VALUE) < 9218868437227405312L) {
            log = Math.max(log, d10);
        }
        double d11 = (-(d7 + d8)) / (d * d8);
        double d12 = d * d11;
        double exp = (Math.exp(d12) * d2) + (d8 * d11 * Math.exp(d12));
        if (!Double.isNaN(d11) && d11 > 0.0d) {
            if (d11 <= 0.0d || (-exp) >= d6) {
                log = (-(2.0d / d)) - (d2 / d8);
                d5 = Double.MAX_VALUE;
                while (d5 > 0.001d && i < 100) {
                    i++;
                    double d13 = d * log;
                    double d14 = d6;
                    double exp2 = log - ((((d2 + (d8 * log)) * Math.exp(d13)) + d6) / ((((1 + d13) * d8) + d7) * Math.exp(d13)));
                    d5 = Math.abs(log - exp2);
                    log = exp2;
                    d6 = d14;
                }
                return log;
            }
            if (d8 < 0.0d && d2 > 0.0d) {
                log = 0.0d;
            }
        }
        d6 = -d6;
        d5 = Double.MAX_VALUE;
        while (d5 > 0.001d) {
            i++;
            double d132 = d * log;
            double d142 = d6;
            double exp22 = log - ((((d2 + (d8 * log)) * Math.exp(d132)) + d6) / ((((1 + d132) * d8) + d7) * Math.exp(d132)));
            d5 = Math.abs(log - exp22);
            log = exp22;
            d6 = d142;
        }
        return log;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final double estimateOverDamped(double d, double d2, double d3, double d4, double d5) {
        double d6;
        double d7 = d5;
        double d8 = d - d2;
        double d9 = ((d * d3) - d4) / d8;
        double d10 = d3 - d9;
        double log = Math.log(Math.abs(d7 / d10)) / d;
        double log2 = Math.log(Math.abs(d7 / d9)) / d2;
        if ((Double.doubleToRawLongBits(log) & Long.MAX_VALUE) < 9218868437227405312L) {
            if ((Double.doubleToRawLongBits(log2) & Long.MAX_VALUE) < 9218868437227405312L) {
                log = Math.max(log, log2);
            }
        } else {
            log = log2;
        }
        double d11 = d10 * d;
        double log3 = Math.log(d11 / ((-d9) * d2)) / (d2 - d);
        if (!Double.isNaN(log3) && log3 > 0.0d) {
            if (log3 <= 0.0d || (-estimateOverDamped$xInflection(d10, d, log3, d9, d2)) >= d7) {
                log = Math.log((-((d9 * d2) * d2)) / (d11 * d)) / d8;
                d6 = d9 * d2;
                if (Math.abs((Math.exp(d * log) * d11) + (Math.exp(d2 * log) * d6)) >= 1.0E-4d) {
                    return log;
                }
                double d12 = Double.MAX_VALUE;
                int i = 0;
                while (d12 > 0.001d && i < 100) {
                    i++;
                    double d13 = d * log;
                    double d14 = d2 * log;
                    double exp = log - ((((Math.exp(d13) * d10) + (Math.exp(d14) * d9)) + d7) / ((Math.exp(d13) * d11) + (Math.exp(d14) * d6)));
                    d12 = Math.abs(log - exp);
                    log = exp;
                }
                return log;
            }
            if (d9 > 0.0d && d10 < 0.0d) {
                log = 0.0d;
            }
        }
        d7 = -d7;
        d6 = d9 * d2;
        if (Math.abs((Math.exp(d * log) * d11) + (Math.exp(d2 * log) * d6)) >= 1.0E-4d) {
        }
    }

    private static final double estimateOverDamped$xInflection(double d, double d2, double d3, double d4, double d5) {
        return (d * Math.exp(d2 * d3)) + (d4 * Math.exp(d5 * d3));
    }

    private static final long estimateDurationInternal(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        double estimateCriticallyDamped;
        double d8 = d5;
        if (d6 == 0.0d && d8 == 0.0d) {
            return 0L;
        }
        if (d6 < 0.0d) {
            d8 = -d8;
        }
        double d9 = d8;
        double abs = Math.abs(d6);
        if (d4 > 1.0d) {
            estimateCriticallyDamped = estimateOverDamped(d, d3, abs, d9, d7);
        } else if (d4 < 1.0d) {
            estimateCriticallyDamped = estimateUnderDamped(d, d2, abs, d9, d7);
        } else {
            estimateCriticallyDamped = estimateCriticallyDamped(d, abs, d9, d7);
        }
        return (long) (estimateCriticallyDamped * 1000.0d);
    }

    private static final double iterateNewtonsMethod(double d, Function1<? super Double, Double> function1, Function1<? super Double, Double> function12) {
        return d - (function1.invoke(Double.valueOf(d)).doubleValue() / function12.invoke(Double.valueOf(d)).doubleValue());
    }

    private static final boolean isNotFinite(double d) {
        return !((Double.doubleToRawLongBits(d) & Long.MAX_VALUE) < 9218868437227405312L);
    }
}
