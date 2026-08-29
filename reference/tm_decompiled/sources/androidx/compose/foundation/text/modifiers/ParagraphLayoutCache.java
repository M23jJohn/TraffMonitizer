package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.foundation.text.modifiers.MinLinesConstrainer;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphIntrinsicsKt;
import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ParagraphLayoutCache.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010A\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020CH\u0002¢\u0006\u0004\bD\u00100J\u001d\u0010E\u001a\u00020\u000b2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206¢\u0006\u0004\bH\u0010IJ)\u0010J\u001a\u0002082\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u0002062\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0002¢\u0006\u0004\bK\u0010LJ\u0016\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\r2\u0006\u0010G\u001a\u000206JE\u0010O\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u0002042\u0006\u0010G\u001a\u000206H\u0002J\u001f\u0010S\u001a\u00020!2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206H\u0000¢\u0006\u0004\bT\u0010UJ\u001f\u0010V\u001a\u00020\u000b2\u0006\u0010F\u001a\u0002082\u0006\u0010G\u001a\u000206H\u0002¢\u0006\u0004\bW\u0010IJ\b\u0010X\u001a\u00020\u001dH\u0002J\u0010\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010[\u001a\u00020\r2\u0006\u0010G\u001a\u000206J\u000e\u0010\\\u001a\u00020\r2\u0006\u0010G\u001a\u000206J\b\u0010]\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R(\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u00020,X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u000208X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u00109\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010;\u001a\u00020<8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b=\u0010>\u001a\u0004\b?\u0010.\"\u0004\b@\u00100¨\u0006^"}, d2 = {"Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "<init>", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "lastDensity", "Landroidx/compose/foundation/text/modifiers/InlineDensity;", "J", "value", "Landroidx/compose/ui/unit/Density;", "density", "getDensity$foundation_release", "()Landroidx/compose/ui/unit/Density;", "setDensity$foundation_release", "(Landroidx/compose/ui/unit/Density;)V", "observeFontChanges", "", "getObserveFontChanges$foundation_release", "()Lkotlin/Unit;", "paragraph", "Landroidx/compose/ui/text/Paragraph;", "getParagraph$foundation_release", "()Landroidx/compose/ui/text/Paragraph;", "setParagraph$foundation_release", "(Landroidx/compose/ui/text/Paragraph;)V", "didOverflow", "getDidOverflow$foundation_release", "()Z", "setDidOverflow$foundation_release", "(Z)V", "layoutSize", "Landroidx/compose/ui/unit/IntSize;", "getLayoutSize-YbymL2g$foundation_release", "()J", "setLayoutSize-ozmzZPI$foundation_release", "(J)V", "mMinLinesConstrainer", "Landroidx/compose/foundation/text/modifiers/MinLinesConstrainer;", "paragraphIntrinsics", "Landroidx/compose/ui/text/ParagraphIntrinsics;", "intrinsicsLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "prevConstraints", "Landroidx/compose/ui/unit/Constraints;", "cachedIntrinsicHeightInputWidth", "cachedIntrinsicHeight", "historyFlag", "", "getHistoryFlag$foundation_release$annotations", "()V", "getHistoryFlag$foundation_release", "setHistoryFlag$foundation_release", "recordHistory", "op", "Landroidx/compose/foundation/text/modifiers/LayoutCacheOperation;", "recordHistory-4ETZmGE", "layoutWithConstraints", "constraints", "layoutDirection", "layoutWithConstraints-K40F9xA", "(JLandroidx/compose/ui/unit/LayoutDirection;)Z", "useMinLinesConstrainer", "useMinLinesConstrainer-euUD3Qg", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/TextStyle;)J", "intrinsicHeight", "width", "update", "update-L6sJoHM", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZII)V", "setLayoutDirection", "layoutText", "layoutText-K40F9xA$foundation_release", "(JLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/text/Paragraph;", "newLayoutWillBeDifferent", "newLayoutWillBeDifferent-K40F9xA", "markDirty", "slowCreateTextLayoutResultOrNull", "Landroidx/compose/ui/text/TextLayoutResult;", "minIntrinsicWidth", "maxIntrinsicWidth", "toString", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ParagraphLayoutCache {
    public static final int $stable = 8;
    private int cachedIntrinsicHeight;
    private int cachedIntrinsicHeightInputWidth;
    private Density density;
    private boolean didOverflow;
    private FontFamily.Resolver fontFamilyResolver;
    private long historyFlag;
    private LayoutDirection intrinsicsLayoutDirection;
    private long lastDensity;
    private long layoutSize;
    private MinLinesConstrainer mMinLinesConstrainer;
    private int maxLines;
    private int minLines;
    private int overflow;
    private Paragraph paragraph;
    private ParagraphIntrinsics paragraphIntrinsics;
    private long prevConstraints;
    private boolean softWrap;
    private TextStyle style;
    private String text;

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, i, z, i2, i3);
    }

    public static /* synthetic */ void getHistoryFlag$foundation_release$annotations() {
    }

    private ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.lastDensity = InlineDensity.INSTANCE.m1719getUnspecifiedL26CHvs();
        long j = 0;
        this.layoutSize = IntSize.m7569constructorimpl((j & 4294967295L) | (j << 32));
        this.prevConstraints = Constraints.INSTANCE.m7352fixedJhjzzOo(0, 0);
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
    }

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, (i4 & 8) != 0 ? TextOverflow.INSTANCE.m7324getClipgIe3tQ8() : i, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? Integer.MAX_VALUE : i2, (i4 & 64) != 0 ? 1 : i3, null);
    }

    /* renamed from: getDensity$foundation_release, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity$foundation_release(Density density) {
        Density density2 = this.density;
        long m1711constructorimpl = density != null ? InlineDensity.m1711constructorimpl(density) : InlineDensity.INSTANCE.m1719getUnspecifiedL26CHvs();
        if (density2 == null) {
            this.density = density;
            this.lastDensity = m1711constructorimpl;
        } else if (density == null || !InlineDensity.m1713equalsimpl0(this.lastDensity, m1711constructorimpl)) {
            this.density = density;
            this.lastDensity = m1711constructorimpl;
            m1751recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1728getMarkDirtyDensityDEKiAbY());
            markDirty();
        }
    }

    public final Unit getObserveFontChanges$foundation_release() {
        ParagraphIntrinsics paragraphIntrinsics = this.paragraphIntrinsics;
        if (paragraphIntrinsics != null) {
            paragraphIntrinsics.getHasStaleResolvedFonts();
        }
        return Unit.INSTANCE;
    }

    /* renamed from: getParagraph$foundation_release, reason: from getter */
    public final Paragraph getParagraph() {
        return this.paragraph;
    }

    public final void setParagraph$foundation_release(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    /* renamed from: getDidOverflow$foundation_release, reason: from getter */
    public final boolean getDidOverflow() {
        return this.didOverflow;
    }

    public final void setDidOverflow$foundation_release(boolean z) {
        this.didOverflow = z;
    }

    /* renamed from: getLayoutSize-YbymL2g$foundation_release, reason: not valid java name and from getter */
    public final long getLayoutSize() {
        return this.layoutSize;
    }

    /* renamed from: setLayoutSize-ozmzZPI$foundation_release, reason: not valid java name */
    public final void m1757setLayoutSizeozmzZPI$foundation_release(long j) {
        this.layoutSize = j;
    }

    /* renamed from: getHistoryFlag$foundation_release, reason: from getter */
    public final long getHistoryFlag() {
        return this.historyFlag;
    }

    public final void setHistoryFlag$foundation_release(long j) {
        this.historyFlag = j;
    }

    /* renamed from: recordHistory-4ETZmGE, reason: not valid java name */
    private final void m1751recordHistory4ETZmGE(long op) {
        this.historyFlag = op | (this.historyFlag << 2);
    }

    /* renamed from: layoutWithConstraints-K40F9xA, reason: not valid java name */
    public final boolean m1756layoutWithConstraintsK40F9xA(long constraints, LayoutDirection layoutDirection) {
        ParagraphLayoutCache paragraphLayoutCache;
        LayoutDirection layoutDirection2;
        m1751recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1727getLayoutWithConstraintsDEKiAbY());
        boolean z = true;
        if (this.minLines > 1) {
            paragraphLayoutCache = this;
            layoutDirection2 = layoutDirection;
            constraints = m1753useMinLinesConstrainereuUD3Qg$default(paragraphLayoutCache, constraints, layoutDirection2, null, 4, null);
        } else {
            paragraphLayoutCache = this;
            layoutDirection2 = layoutDirection;
        }
        boolean z2 = false;
        if (!m1750newLayoutWillBeDifferentK40F9xA(constraints, layoutDirection2)) {
            if (!Constraints.m7335equalsimpl0(constraints, paragraphLayoutCache.prevConstraints)) {
                Paragraph paragraph = paragraphLayoutCache.paragraph;
                Intrinsics.checkNotNull(paragraph);
                paragraphLayoutCache.layoutSize = ConstraintsKt.m7356constrain4WqzIAM(constraints, IntSize.m7569constructorimpl((TextDelegateKt.ceilToIntPx(Math.min(paragraph.getMaxIntrinsicWidth(), paragraph.getWidth())) << 32) | (TextDelegateKt.ceilToIntPx(paragraph.getHeight()) & 4294967295L)));
                if (TextOverflow.m7315equalsimpl0(paragraphLayoutCache.overflow, TextOverflow.INSTANCE.m7328getVisiblegIe3tQ8()) || (((int) (r7 >> 32)) >= paragraph.getWidth() && ((int) (4294967295L & r7)) >= paragraph.getHeight())) {
                    z = false;
                }
                paragraphLayoutCache.didOverflow = z;
                paragraphLayoutCache.prevConstraints = constraints;
            }
            return false;
        }
        Paragraph m1755layoutTextK40F9xA$foundation_release = m1755layoutTextK40F9xA$foundation_release(constraints, layoutDirection2);
        paragraphLayoutCache.prevConstraints = constraints;
        paragraphLayoutCache.layoutSize = ConstraintsKt.m7356constrain4WqzIAM(constraints, IntSize.m7569constructorimpl((TextDelegateKt.ceilToIntPx(m1755layoutTextK40F9xA$foundation_release.getWidth()) << 32) | (TextDelegateKt.ceilToIntPx(m1755layoutTextK40F9xA$foundation_release.getHeight()) & 4294967295L)));
        if (!TextOverflow.m7315equalsimpl0(paragraphLayoutCache.overflow, TextOverflow.INSTANCE.m7328getVisiblegIe3tQ8()) && (((int) (r13 >> 32)) < m1755layoutTextK40F9xA$foundation_release.getWidth() || ((int) (r13 & 4294967295L)) < m1755layoutTextK40F9xA$foundation_release.getHeight())) {
            z2 = true;
        }
        paragraphLayoutCache.didOverflow = z2;
        paragraphLayoutCache.paragraph = m1755layoutTextK40F9xA$foundation_release;
        return true;
    }

    /* renamed from: useMinLinesConstrainer-euUD3Qg$default, reason: not valid java name */
    static /* synthetic */ long m1753useMinLinesConstrainereuUD3Qg$default(ParagraphLayoutCache paragraphLayoutCache, long j, LayoutDirection layoutDirection, TextStyle textStyle, int i, Object obj) {
        if ((i & 4) != 0) {
            textStyle = paragraphLayoutCache.style;
        }
        return paragraphLayoutCache.m1752useMinLinesConstrainereuUD3Qg(j, layoutDirection, textStyle);
    }

    /* renamed from: useMinLinesConstrainer-euUD3Qg, reason: not valid java name */
    private final long m1752useMinLinesConstrainereuUD3Qg(long constraints, LayoutDirection layoutDirection, TextStyle style) {
        MinLinesConstrainer.Companion companion = MinLinesConstrainer.INSTANCE;
        MinLinesConstrainer minLinesConstrainer = this.mMinLinesConstrainer;
        Density density = this.density;
        Intrinsics.checkNotNull(density);
        MinLinesConstrainer from = companion.from(minLinesConstrainer, layoutDirection, style, density, this.fontFamilyResolver);
        this.mMinLinesConstrainer = from;
        return from.m1735coerceMinLinesOh53vG4$foundation_release(constraints, this.minLines);
    }

    public final int intrinsicHeight(int width, LayoutDirection layoutDirection) {
        ParagraphLayoutCache paragraphLayoutCache;
        LayoutDirection layoutDirection2;
        int i = this.cachedIntrinsicHeightInputWidth;
        int i2 = this.cachedIntrinsicHeight;
        if (width == i && i != -1) {
            return i2;
        }
        long Constraints = ConstraintsKt.Constraints(0, width, 0, Integer.MAX_VALUE);
        if (this.minLines > 1) {
            paragraphLayoutCache = this;
            layoutDirection2 = layoutDirection;
            Constraints = m1753useMinLinesConstrainereuUD3Qg$default(paragraphLayoutCache, Constraints, layoutDirection2, null, 4, null);
        } else {
            paragraphLayoutCache = this;
            layoutDirection2 = layoutDirection;
        }
        int coerceAtLeast = RangesKt.coerceAtLeast(TextDelegateKt.ceilToIntPx(m1755layoutTextK40F9xA$foundation_release(Constraints, layoutDirection2).getHeight()), Constraints.m7343getMinHeightimpl(Constraints));
        paragraphLayoutCache.cachedIntrinsicHeightInputWidth = width;
        paragraphLayoutCache.cachedIntrinsicHeight = coerceAtLeast;
        return coerceAtLeast;
    }

    /* renamed from: update-L6sJoHM, reason: not valid java name */
    public final void m1758updateL6sJoHM(String text, TextStyle style, FontFamily.Resolver fontFamilyResolver, int overflow, boolean softWrap, int maxLines, int minLines) {
        this.text = text;
        this.style = style;
        this.fontFamilyResolver = fontFamilyResolver;
        this.overflow = overflow;
        this.softWrap = softWrap;
        this.maxLines = maxLines;
        this.minLines = minLines;
        m1751recordHistory4ETZmGE(LayoutCacheOperation.INSTANCE.m1729getMarkDirtyNodeDEKiAbY());
        markDirty();
    }

    private final ParagraphIntrinsics setLayoutDirection(LayoutDirection layoutDirection) {
        ParagraphIntrinsics paragraphIntrinsics = this.paragraphIntrinsics;
        if (paragraphIntrinsics == null || layoutDirection != this.intrinsicsLayoutDirection || paragraphIntrinsics.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            String str = this.text;
            TextStyle resolveDefaults = TextStyleKt.resolveDefaults(this.style, layoutDirection);
            List emptyList = CollectionsKt.emptyList();
            Density density = this.density;
            Intrinsics.checkNotNull(density);
            paragraphIntrinsics = ParagraphIntrinsicsKt.ParagraphIntrinsics(str, resolveDefaults, (List<? extends AnnotatedString.Range<? extends AnnotatedString.Annotation>>) emptyList, density, this.fontFamilyResolver, (List<AnnotatedString.Range<Placeholder>>) CollectionsKt.emptyList());
        }
        this.paragraphIntrinsics = paragraphIntrinsics;
        return paragraphIntrinsics;
    }

    /* renamed from: layoutText-K40F9xA$foundation_release, reason: not valid java name */
    public final Paragraph m1755layoutTextK40F9xA$foundation_release(long constraints, LayoutDirection layoutDirection) {
        ParagraphIntrinsics layoutDirection2 = setLayoutDirection(layoutDirection);
        return ParagraphKt.m6712ParagraphczeNHc(layoutDirection2, LayoutUtilsKt.m1731finalConstraintstfFHcEY(constraints, this.softWrap, this.overflow, layoutDirection2.getMaxIntrinsicWidth()), LayoutUtilsKt.m1732finalMaxLinesxdlQI24(this.softWrap, this.overflow, this.maxLines), this.overflow);
    }

    /* renamed from: newLayoutWillBeDifferent-K40F9xA, reason: not valid java name */
    private final boolean m1750newLayoutWillBeDifferentK40F9xA(long constraints, LayoutDirection layoutDirection) {
        ParagraphIntrinsics paragraphIntrinsics;
        Paragraph paragraph = this.paragraph;
        if (paragraph == null || (paragraphIntrinsics = this.paragraphIntrinsics) == null || paragraphIntrinsics.getHasStaleResolvedFonts() || layoutDirection != this.intrinsicsLayoutDirection) {
            return true;
        }
        if (Constraints.m7335equalsimpl0(constraints, this.prevConstraints)) {
            return false;
        }
        return Constraints.m7342getMaxWidthimpl(constraints) != Constraints.m7342getMaxWidthimpl(this.prevConstraints) || Constraints.m7344getMinWidthimpl(constraints) != Constraints.m7344getMinWidthimpl(this.prevConstraints) || ((float) Constraints.m7341getMaxHeightimpl(constraints)) < paragraph.getHeight() || paragraph.getDidExceedMaxLines();
    }

    private final void markDirty() {
        this.paragraph = null;
        this.paragraphIntrinsics = null;
        this.intrinsicsLayoutDirection = null;
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
        this.prevConstraints = Constraints.INSTANCE.m7352fixedJhjzzOo(0, 0);
        long j = 0;
        this.layoutSize = IntSize.m7569constructorimpl((j & 4294967295L) | (j << 32));
        this.didOverflow = false;
    }

    public final TextLayoutResult slowCreateTextLayoutResultOrNull(TextStyle style) {
        Density density;
        LayoutDirection layoutDirection = this.intrinsicsLayoutDirection;
        if (layoutDirection == null || (density = this.density) == null) {
            return null;
        }
        AnnotatedString annotatedString = new AnnotatedString(this.text, null, 2, null);
        if (this.paragraph == null || this.paragraphIntrinsics == null) {
            return null;
        }
        long m7330constructorimpl = Constraints.m7330constructorimpl(this.prevConstraints & ConstraintsKt.MaxDimensionsAndFocusMask);
        return new TextLayoutResult(new TextLayoutInput(annotatedString, style, CollectionsKt.emptyList(), this.maxLines, this.softWrap, this.overflow, density, layoutDirection, this.fontFamilyResolver, m7330constructorimpl, (DefaultConstructorMarker) null), new MultiParagraph(new MultiParagraphIntrinsics(annotatedString, style, (List<AnnotatedString.Range<Placeholder>>) CollectionsKt.emptyList(), density, this.fontFamilyResolver), m7330constructorimpl, this.maxLines, this.overflow, (DefaultConstructorMarker) null), this.layoutSize, null);
    }

    public final int minIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMinIntrinsicWidth());
    }

    public final int maxIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMaxIntrinsicWidth());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ParagraphLayoutCache(paragraph=");
        sb.append(this.paragraph != null ? "<paragraph>" : "null");
        sb.append(", lastDensity=");
        sb.append((Object) InlineDensity.m1717toStringimpl(this.lastDensity));
        sb.append(", history=");
        sb.append(this.historyFlag);
        sb.append(", constraints=$)");
        return sb.toString();
    }
}
