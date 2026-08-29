package androidx.compose.material3.carousel;

import androidx.compose.material3.carousel.KeylineListScope;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* compiled from: Keylines.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a0\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001aD\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00012\b\b\u0002\u0010\u0014\u001a\u00020\u0001H\u0000\u001a(\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0000¨\u0006\u0017"}, d2 = {"calculateMediumChildSize", "", "minimumMediumSize", "largeItemSize", "remainingSpace", "createLeftAlignedKeylineList", "Landroidx/compose/material3/carousel/KeylineList;", "carouselMainAxisSize", "itemSpacing", "leftAnchorSize", "rightAnchorSize", "arrangement", "Landroidx/compose/material3/carousel/Arrangement;", "multiBrowseKeylineList", "density", "Landroidx/compose/ui/unit/Density;", "preferredItemSize", "itemCount", "", "minSmallItemSize", "maxSmallItemSize", "uncontainedKeylineList", "itemSize", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KeylinesKt {
    public static /* synthetic */ KeylineList multiBrowseKeylineList$default(Density density, float f, float f2, float f3, int i, float f4, float f5, int i2, Object obj) {
        if ((i2 & 32) != 0) {
            f4 = density.mo459toPx0680j_4(CarouselDefaults.INSTANCE.m3037getMinSmallItemSizeD9Ej5fM$material3_release());
        }
        float f6 = f4;
        if ((i2 & 64) != 0) {
            f5 = density.mo459toPx0680j_4(CarouselDefaults.INSTANCE.m3036getMaxSmallItemSizeD9Ej5fM$material3_release());
        }
        return multiBrowseKeylineList(density, f, f2, f3, i, f6, f5);
    }

    public static final KeylineList multiBrowseKeylineList(Density density, float f, float f2, float f3, int i, float f4, float f5) {
        float f6;
        if (f == 0.0f || f2 == 0.0f) {
            return KeylineListKt.emptyKeylineList();
        }
        int[] iArr = {1};
        int[] iArr2 = {1, 0};
        float min = Math.min(f2, f);
        float coerceIn = RangesKt.coerceIn(min / 3.0f, f4, f5);
        float f7 = (min + coerceIn) / 2.0f;
        if (f < 2 * f4) {
            iArr = new int[]{0};
        }
        int[] iArr3 = iArr;
        int ceil = (int) Math.ceil(f / min);
        int max = (ceil - Math.max(1, (int) Math.floor(((f - (ArraysKt.maxOrThrow(iArr2) * f7)) - (ArraysKt.maxOrThrow(iArr3) * f5)) / min))) + 1;
        int[] iArr4 = new int[max];
        for (int i2 = 0; i2 < max; i2++) {
            iArr4[i2] = ceil - i2;
        }
        float mo459toPx0680j_4 = density.mo459toPx0680j_4(CarouselDefaults.INSTANCE.m3035getAnchorSizeD9Ej5fM$material3_release());
        Arrangement findLowestCostArrangement = Arrangement.INSTANCE.findLowestCostArrangement(f, f3, coerceIn, f4, f5, iArr3, f7, iArr2, min, iArr4);
        if (findLowestCostArrangement == null || findLowestCostArrangement.itemCount() <= i) {
            f6 = f;
        } else {
            int smallCount = findLowestCostArrangement.getSmallCount();
            int mediumCount = findLowestCostArrangement.getMediumCount();
            for (int itemCount = findLowestCostArrangement.itemCount() - i; itemCount > 0; itemCount--) {
                if (smallCount > 0) {
                    smallCount--;
                } else if (mediumCount > 1) {
                    mediumCount--;
                }
            }
            f6 = f;
            findLowestCostArrangement = Arrangement.INSTANCE.findLowestCostArrangement(f6, f3, coerceIn, f4, f5, new int[]{smallCount}, f7, new int[]{mediumCount}, min, iArr4);
        }
        if (findLowestCostArrangement == null) {
            return KeylineListKt.emptyKeylineList();
        }
        return createLeftAlignedKeylineList(f6, f3, mo459toPx0680j_4, mo459toPx0680j_4, findLowestCostArrangement);
    }

    public static final KeylineList createLeftAlignedKeylineList(float f, float f2, final float f3, final float f4, final Arrangement arrangement) {
        return KeylineListKt.m3043keylineListOfWNYm7Xg(f, f2, CarouselAlignment.INSTANCE.m3034getStartNUL3oTo(), new Function1<KeylineListScope, Unit>() { // from class: androidx.compose.material3.carousel.KeylinesKt$createLeftAlignedKeylineList$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KeylineListScope keylineListScope) {
                invoke2(keylineListScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KeylineListScope keylineListScope) {
                keylineListScope.add(f3, true);
                int largeCount = arrangement.getLargeCount();
                Arrangement arrangement2 = arrangement;
                for (int i = 0; i < largeCount; i++) {
                    KeylineListScope.CC.add$default(keylineListScope, arrangement2.getLargeSize(), false, 2, null);
                }
                int mediumCount = arrangement.getMediumCount();
                Arrangement arrangement3 = arrangement;
                for (int i2 = 0; i2 < mediumCount; i2++) {
                    KeylineListScope.CC.add$default(keylineListScope, arrangement3.getMediumSize(), false, 2, null);
                }
                int smallCount = arrangement.getSmallCount();
                Arrangement arrangement4 = arrangement;
                for (int i3 = 0; i3 < smallCount; i3++) {
                    KeylineListScope.CC.add$default(keylineListScope, arrangement4.getSmallSize(), false, 2, null);
                }
                keylineListScope.add(f4, true);
            }
        });
    }

    public static final KeylineList uncontainedKeylineList(Density density, float f, float f2, float f3) {
        if (f == 0.0f || f2 == 0.0f) {
            return KeylineListKt.emptyKeylineList();
        }
        float min = Math.min(f2 + f3, f);
        int max = Math.max(1, (int) Math.floor(f / min));
        float f4 = f - (max * min);
        int i = f4 <= 0.0f ? 0 : 1;
        float mo459toPx0680j_4 = density.mo459toPx0680j_4(CarouselDefaults.INSTANCE.m3035getAnchorSizeD9Ej5fM$material3_release());
        float calculateMediumChildSize = calculateMediumChildSize(mo459toPx0680j_4, min, f4);
        return createLeftAlignedKeylineList(f, f3, Math.max(Math.min(mo459toPx0680j_4, f2), calculateMediumChildSize * 0.5f), mo459toPx0680j_4, new Arrangement(0, 0.0f, 0, calculateMediumChildSize, i, min, max));
    }

    private static final float calculateMediumChildSize(float f, float f2, float f3) {
        float max = Math.max(1.5f * f3, f);
        float f4 = f2 * 0.85f;
        return max > f4 ? Math.max(f4, f3 * 1.2f) : max;
    }
}
