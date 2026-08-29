package androidx.compose.foundation.layout;

import android.graphics.Insets;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* compiled from: WindowInsetsConnection.android.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bc\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0003H&J\u0017\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007H&¢\u0006\u0004\b\u0017\u0010\u0018ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001aÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/layout/SideCalculator;", "", "valueOf", "", "insets", "Landroid/graphics/Insets;", "motionOf", "", "x", "y", "showMotion", "hideMotion", "adjustInsets", "oldInsets", "newValue", "consumedOffsets", "Landroidx/compose/ui/geometry/Offset;", "available", "consumedOffsets-MK-Hz9U", "(J)J", "consumedVelocity", "Landroidx/compose/ui/unit/Velocity;", "remaining", "consumedVelocity-QWom1Mo", "(JF)J", "Companion", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
interface SideCalculator {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    Insets adjustInsets(Insets oldInsets, int newValue);

    /* renamed from: consumedOffsets-MK-Hz9U, reason: not valid java name */
    long mo911consumedOffsetsMKHz9U(long available);

    /* renamed from: consumedVelocity-QWom1Mo, reason: not valid java name */
    long mo912consumedVelocityQWom1Mo(long available, float remaining);

    float hideMotion(float x, float y);

    float motionOf(float x, float y);

    float showMotion(float x, float y);

    int valueOf(Insets insets);

    /* compiled from: WindowInsetsConnection.android.kt */
    /* renamed from: androidx.compose.foundation.layout.SideCalculator$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }

    /* compiled from: WindowInsetsConnection.android.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0004\r\u0010\u0013\u0016\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/layout/SideCalculator$Companion;", "", "<init>", "()V", "chooseCalculator", "Landroidx/compose/foundation/layout/SideCalculator;", "side", "Landroidx/compose/foundation/layout/WindowInsetsSides;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "chooseCalculator-ni1skBw", "(ILandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/foundation/layout/SideCalculator;", "LeftSideCalculator", "androidx/compose/foundation/layout/SideCalculator$Companion$LeftSideCalculator$1", "Landroidx/compose/foundation/layout/SideCalculator$Companion$LeftSideCalculator$1;", "TopSideCalculator", "androidx/compose/foundation/layout/SideCalculator$Companion$TopSideCalculator$1", "Landroidx/compose/foundation/layout/SideCalculator$Companion$TopSideCalculator$1;", "RightSideCalculator", "androidx/compose/foundation/layout/SideCalculator$Companion$RightSideCalculator$1", "Landroidx/compose/foundation/layout/SideCalculator$Companion$RightSideCalculator$1;", "BottomSideCalculator", "androidx/compose/foundation/layout/SideCalculator$Companion$BottomSideCalculator$1", "Landroidx/compose/foundation/layout/SideCalculator$Companion$BottomSideCalculator$1;", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final SideCalculator$Companion$LeftSideCalculator$1 LeftSideCalculator = new SideCalculator() { // from class: androidx.compose.foundation.layout.SideCalculator$Companion$LeftSideCalculator$1
            @Override // androidx.compose.foundation.layout.SideCalculator
            public /* synthetic */ float hideMotion(float f, float f2) {
                float coerceAtMost;
                coerceAtMost = RangesKt.coerceAtMost(motionOf(f, f2), 0.0f);
                return coerceAtMost;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public float motionOf(float x, float y) {
                return x;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public /* synthetic */ float showMotion(float f, float f2) {
                float coerceAtLeast;
                coerceAtLeast = RangesKt.coerceAtLeast(motionOf(f, f2), 0.0f);
                return coerceAtLeast;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public int valueOf(Insets insets) {
                int i;
                i = insets.left;
                return i;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public Insets adjustInsets(Insets oldInsets, int newValue) {
                int i;
                int i2;
                int i3;
                Insets of;
                i = oldInsets.top;
                i2 = oldInsets.right;
                i3 = oldInsets.bottom;
                of = Insets.of(newValue, i, i2, i3);
                return of;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            /* renamed from: consumedVelocity-QWom1Mo */
            public long mo912consumedVelocityQWom1Mo(long available, float remaining) {
                return VelocityKt.Velocity(Velocity.m7641getXimpl(available) - remaining, 0.0f);
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            /* renamed from: consumedOffsets-MK-Hz9U */
            public long mo911consumedOffsetsMKHz9U(long available) {
                return Offset.m4335constructorimpl((Float.floatToRawIntBits(Float.intBitsToFloat((int) (available >> 32))) << 32) | (Float.floatToRawIntBits(0.0f) & 4294967295L));
            }
        };
        private static final SideCalculator$Companion$TopSideCalculator$1 TopSideCalculator = new SideCalculator() { // from class: androidx.compose.foundation.layout.SideCalculator$Companion$TopSideCalculator$1
            @Override // androidx.compose.foundation.layout.SideCalculator
            public /* synthetic */ float hideMotion(float f, float f2) {
                float coerceAtMost;
                coerceAtMost = RangesKt.coerceAtMost(motionOf(f, f2), 0.0f);
                return coerceAtMost;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public float motionOf(float x, float y) {
                return y;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public /* synthetic */ float showMotion(float f, float f2) {
                float coerceAtLeast;
                coerceAtLeast = RangesKt.coerceAtLeast(motionOf(f, f2), 0.0f);
                return coerceAtLeast;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public int valueOf(Insets insets) {
                int i;
                i = insets.top;
                return i;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public Insets adjustInsets(Insets oldInsets, int newValue) {
                int i;
                int i2;
                int i3;
                Insets of;
                i = oldInsets.left;
                i2 = oldInsets.right;
                i3 = oldInsets.bottom;
                of = Insets.of(i, newValue, i2, i3);
                return of;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            /* renamed from: consumedVelocity-QWom1Mo */
            public long mo912consumedVelocityQWom1Mo(long available, float remaining) {
                return VelocityKt.Velocity(0.0f, Velocity.m7642getYimpl(available) - remaining);
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            /* renamed from: consumedOffsets-MK-Hz9U */
            public long mo911consumedOffsetsMKHz9U(long available) {
                float intBitsToFloat = Float.intBitsToFloat((int) (available & 4294967295L));
                return Offset.m4335constructorimpl((Float.floatToRawIntBits(intBitsToFloat) & 4294967295L) | (Float.floatToRawIntBits(0.0f) << 32));
            }
        };
        private static final SideCalculator$Companion$RightSideCalculator$1 RightSideCalculator = new SideCalculator() { // from class: androidx.compose.foundation.layout.SideCalculator$Companion$RightSideCalculator$1
            @Override // androidx.compose.foundation.layout.SideCalculator
            public /* synthetic */ float hideMotion(float f, float f2) {
                float coerceAtMost;
                coerceAtMost = RangesKt.coerceAtMost(motionOf(f, f2), 0.0f);
                return coerceAtMost;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public float motionOf(float x, float y) {
                return -x;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public /* synthetic */ float showMotion(float f, float f2) {
                float coerceAtLeast;
                coerceAtLeast = RangesKt.coerceAtLeast(motionOf(f, f2), 0.0f);
                return coerceAtLeast;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public int valueOf(Insets insets) {
                int i;
                i = insets.right;
                return i;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public Insets adjustInsets(Insets oldInsets, int newValue) {
                int i;
                int i2;
                int i3;
                Insets of;
                i = oldInsets.left;
                i2 = oldInsets.top;
                i3 = oldInsets.bottom;
                of = Insets.of(i, i2, newValue, i3);
                return of;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            /* renamed from: consumedVelocity-QWom1Mo */
            public long mo912consumedVelocityQWom1Mo(long available, float remaining) {
                return VelocityKt.Velocity(Velocity.m7641getXimpl(available) + remaining, 0.0f);
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            /* renamed from: consumedOffsets-MK-Hz9U */
            public long mo911consumedOffsetsMKHz9U(long available) {
                return Offset.m4335constructorimpl((Float.floatToRawIntBits(Float.intBitsToFloat((int) (available >> 32))) << 32) | (Float.floatToRawIntBits(0.0f) & 4294967295L));
            }
        };
        private static final SideCalculator$Companion$BottomSideCalculator$1 BottomSideCalculator = new SideCalculator() { // from class: androidx.compose.foundation.layout.SideCalculator$Companion$BottomSideCalculator$1
            @Override // androidx.compose.foundation.layout.SideCalculator
            public /* synthetic */ float hideMotion(float f, float f2) {
                float coerceAtMost;
                coerceAtMost = RangesKt.coerceAtMost(motionOf(f, f2), 0.0f);
                return coerceAtMost;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public float motionOf(float x, float y) {
                return -y;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public /* synthetic */ float showMotion(float f, float f2) {
                float coerceAtLeast;
                coerceAtLeast = RangesKt.coerceAtLeast(motionOf(f, f2), 0.0f);
                return coerceAtLeast;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public int valueOf(Insets insets) {
                int i;
                i = insets.bottom;
                return i;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            public Insets adjustInsets(Insets oldInsets, int newValue) {
                int i;
                int i2;
                int i3;
                Insets of;
                i = oldInsets.left;
                i2 = oldInsets.top;
                i3 = oldInsets.right;
                of = Insets.of(i, i2, i3, newValue);
                return of;
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            /* renamed from: consumedVelocity-QWom1Mo */
            public long mo912consumedVelocityQWom1Mo(long available, float remaining) {
                return VelocityKt.Velocity(0.0f, Velocity.m7642getYimpl(available) + remaining);
            }

            @Override // androidx.compose.foundation.layout.SideCalculator
            /* renamed from: consumedOffsets-MK-Hz9U */
            public long mo911consumedOffsetsMKHz9U(long available) {
                float intBitsToFloat = Float.intBitsToFloat((int) (available & 4294967295L));
                return Offset.m4335constructorimpl((Float.floatToRawIntBits(intBitsToFloat) & 4294967295L) | (Float.floatToRawIntBits(0.0f) << 32));
            }
        };

        private Companion() {
        }

        /* renamed from: chooseCalculator-ni1skBw, reason: not valid java name */
        public final SideCalculator m913chooseCalculatorni1skBw(int side, LayoutDirection layoutDirection) {
            if (WindowInsetsSides.m967equalsimpl0(side, WindowInsetsSides.INSTANCE.m981getLeftJoeWqyM())) {
                return LeftSideCalculator;
            }
            if (WindowInsetsSides.m967equalsimpl0(side, WindowInsetsSides.INSTANCE.m984getTopJoeWqyM())) {
                return TopSideCalculator;
            }
            if (WindowInsetsSides.m967equalsimpl0(side, WindowInsetsSides.INSTANCE.m982getRightJoeWqyM())) {
                return RightSideCalculator;
            }
            if (WindowInsetsSides.m967equalsimpl0(side, WindowInsetsSides.INSTANCE.m978getBottomJoeWqyM())) {
                return BottomSideCalculator;
            }
            if (WindowInsetsSides.m967equalsimpl0(side, WindowInsetsSides.INSTANCE.m983getStartJoeWqyM())) {
                if (layoutDirection == LayoutDirection.Ltr) {
                    return LeftSideCalculator;
                }
                return RightSideCalculator;
            }
            if (WindowInsetsSides.m967equalsimpl0(side, WindowInsetsSides.INSTANCE.m979getEndJoeWqyM())) {
                if (layoutDirection == LayoutDirection.Ltr) {
                    return RightSideCalculator;
                }
                return LeftSideCalculator;
            }
            throw new IllegalStateException("Only Left, Top, Right, Bottom, Start and End are allowed".toString());
        }
    }
}
