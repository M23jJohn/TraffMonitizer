package androidx.compose.ui.text;

import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.selection.WordBoundary_androidKt;
import androidx.compose.ui.text.android.selection.WordIterator;
import androidx.compose.ui.text.android.style.PlaceholderSpan;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.platform.style.ShaderBrushSpan;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: AndroidParagraph.android.kt */
@Metadata(d1 = {"\u0000\u0098\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bBk\b\u0016\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0014\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u00120\u0011\u0012\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u0011\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\n\u0010\u001aJ\u0010\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u00020-H\u0016J\u0017\u0010P\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020RH\u0016¢\u0006\u0004\bS\u0010TJ'\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020F2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[H\u0016¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020F2\u0006\u0010_\u001a\u00020\u0005H\u0016J)\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020V2\u0006\u0010c\u001a\u00020d2\b\b\u0001\u0010e\u001a\u00020\u0005H\u0016¢\u0006\u0004\bf\u0010gJ\u0018\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u00052\u0006\u0010k\u001a\u00020\u0005H\u0016J\u0010\u0010l\u001a\u00020F2\u0006\u0010_\u001a\u00020\u0005H\u0016J\u0017\u0010m\u001a\u00020V2\u0006\u0010_\u001a\u00020\u0005H\u0016¢\u0006\u0004\bn\u0010oJ\u0010\u0010p\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010r\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010s\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0015\u0010t\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0000¢\u0006\u0002\buJ\u0010\u0010v\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0015\u0010w\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0000¢\u0006\u0002\bxJ\u0010\u0010y\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010z\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010{\u001a\u00020-2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0010\u0010|\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0018\u0010}\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u00052\u0006\u0010~\u001a\u00020;H\u0016J\u0010\u0010\u007f\u001a\u00020;2\u0006\u0010q\u001a\u00020\u0005H\u0016J\u0017\u0010\u0080\u0001\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0000¢\u0006\u0003\b\u0081\u0001J\u0017\u0010\u0082\u0001\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0000¢\u0006\u0003\b\u0083\u0001J\u0011\u0010\u0084\u0001\u001a\u00020\u00052\u0006\u0010_\u001a\u00020\u0005H\u0016J\u001a\u0010\u0085\u0001\u001a\u00020-2\u0006\u0010_\u001a\u00020\u00052\u0007\u0010\u0086\u0001\u001a\u00020;H\u0016J\u0012\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0006\u0010_\u001a\u00020\u0005H\u0016J\u0012\u0010\u0089\u0001\u001a\u00030\u0088\u00012\u0006\u0010_\u001a\u00020\u0005H\u0016J\u001d\u0010\u008a\u0001\u001a\f\u0012\u0005\u0012\u00030\u008c\u0001\u0018\u00010\u008b\u0001*\u00020%H\u0002¢\u0006\u0003\u0010\u008d\u0001J\u001c\u0010\u008e\u0001\u001a\u00020;*\u00030\u008f\u00012\f\u0010\u0090\u0001\u001a\u0007\u0012\u0002\b\u00030\u0091\u0001H\u0002J>\u0010\u0092\u0001\u001a\u00020a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\n\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u00012\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u0001H\u0016¢\u0006\u0006\b\u009b\u0001\u0010\u009c\u0001JT\u0010\u0092\u0001\u001a\u00020a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\n\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u00012\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u00012\n\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u00012\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0016¢\u0006\u0006\b¡\u0001\u0010¢\u0001J]\u0010\u0092\u0001\u001a\u00020a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\b\u0010£\u0001\u001a\u00030¤\u00012\u0007\u0010¥\u0001\u001a\u00020-2\n\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0098\u00012\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u00012\n\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u00012\b\u0010\u009f\u0001\u001a\u00030 \u0001H\u0016¢\u0006\u0006\b¦\u0001\u0010§\u0001J\u0013\u0010\u0092\u0001\u001a\u00020a2\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0002J]\u0010¨\u0001\u001a\u00020%2\u0007\u0010©\u0001\u001a\u00020\u00052\u0007\u0010ª\u0001\u001a\u00020\u00052\n\u0010«\u0001\u001a\u0005\u0018\u00010¬\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0007\u0010\u00ad\u0001\u001a\u00020\u00052\u0007\u0010®\u0001\u001a\u00020\u00052\u0007\u0010¯\u0001\u001a\u00020\u00052\u0007\u0010°\u0001\u001a\u00020\u00052\b\b\u0002\u0010&\u001a\u00020'H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001f\u0010\u001eR\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010&\u001a\u00020'8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010/R\u0014\u00102\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010/R\u0014\u00104\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010/R\u0014\u00106\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u0010/R\u0014\u00108\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010/R\u0014\u0010:\u001a\u00020;8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020?8@X\u0081\u0004¢\u0006\f\u0012\u0004\b@\u0010)\u001a\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010\u001eR\u001c\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010F0\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u001a\u0010I\u001a\u00020J8@X\u0081\u0004¢\u0006\f\u0012\u0004\bK\u0010)\u001a\u0004\bL\u0010M¨\u0006±\u0001"}, d2 = {"Landroidx/compose/ui/text/AndroidParagraph;", "Landroidx/compose/ui/text/Paragraph;", "paragraphIntrinsics", "Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;", "maxLines", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "<init>", "(Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;IIJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "annotations", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "placeholders", "Landroidx/compose/ui/text/Placeholder;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "density", "Landroidx/compose/ui/unit/Density;", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;Ljava/util/List;IIJLandroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/ui/unit/Density;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getParagraphIntrinsics", "()Landroidx/compose/ui/text/platform/AndroidParagraphIntrinsics;", "getMaxLines", "()I", "getOverflow-gIe3tQ8", "I", "getConstraints-msEJaDk", "()J", "J", "layout", "Landroidx/compose/ui/text/android/TextLayout;", "charSequence", "", "getCharSequence$ui_text$annotations", "()V", "getCharSequence$ui_text", "()Ljava/lang/CharSequence;", "width", "", "getWidth", "()F", "height", "getHeight", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "minIntrinsicWidth", "getMinIntrinsicWidth", "firstBaseline", "getFirstBaseline", "lastBaseline", "getLastBaseline", "didExceedMaxLines", "", "getDidExceedMaxLines", "()Z", "textLocale", "Ljava/util/Locale;", "getTextLocale$ui_text$annotations", "getTextLocale$ui_text", "()Ljava/util/Locale;", "lineCount", "getLineCount", "placeholderRects", "Landroidx/compose/ui/geometry/Rect;", "getPlaceholderRects", "()Ljava/util/List;", "textPaint", "Landroidx/compose/ui/text/platform/AndroidTextPaint;", "getTextPaint$ui_text$annotations", "getTextPaint$ui_text", "()Landroidx/compose/ui/text/platform/AndroidTextPaint;", "getLineForVerticalPosition", "vertical", "getOffsetForPosition", "position", "Landroidx/compose/ui/geometry/Offset;", "getOffsetForPosition-k-4lQ0M", "(J)I", "getRangeForRect", "Landroidx/compose/ui/text/TextRange;", "rect", "granularity", "Landroidx/compose/ui/text/TextGranularity;", "inclusionStrategy", "Landroidx/compose/ui/text/TextInclusionStrategy;", "getRangeForRect-8-6BmAI", "(Landroidx/compose/ui/geometry/Rect;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "getBoundingBox", "offset", "fillBoundingBoxes", "", "range", "array", "", "arrayStart", "fillBoundingBoxes-8ffj60Q", "(J[FI)V", "getPathForRange", "Landroidx/compose/ui/graphics/Path;", "start", "end", "getCursorRect", "getWordBoundary", "getWordBoundary--jx7JFs", "(I)J", "getLineLeft", "lineIndex", "getLineRight", "getLineTop", "getLineAscent", "getLineAscent$ui_text", "getLineBaseline", "getLineDescent", "getLineDescent$ui_text", "getLineBottom", "getLineHeight", "getLineWidth", "getLineStart", "getLineEnd", "visibleEnd", "isLineEllipsized", "getLineEllipsisOffset", "getLineEllipsisOffset$ui_text", "getLineEllipsisCount", "getLineEllipsisCount$ui_text", "getLineForOffset", "getHorizontalPosition", "usePrimaryDirection", "getParagraphDirection", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getBidiRunDirection", "getShaderBrushSpans", "", "Landroidx/compose/ui/text/platform/style/ShaderBrushSpan;", "(Landroidx/compose/ui/text/android/TextLayout;)[Landroidx/compose/ui/text/platform/style/ShaderBrushSpan;", "hasSpan", "Landroid/text/Spanned;", "clazz", "Ljava/lang/Class;", "paint", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "color", "Landroidx/compose/ui/graphics/Color;", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "paint-RPmYEkk", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;)V", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "paint-LG529CI", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "paint-hn5TExg", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "constructTextLayout", "alignment", "justificationMode", "ellipsize", "Landroid/text/TextUtils$TruncateAt;", "hyphens", "breakStrategy", "lineBreakStyle", "lineBreakWordStyle", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidParagraph implements Paragraph {
    public static final int $stable = 8;
    private final CharSequence charSequence;
    private final long constraints;
    private final TextLayout layout;
    private final int maxLines;
    private final int overflow;
    private final AndroidParagraphIntrinsics paragraphIntrinsics;
    private final List<Rect> placeholderRects;

    /* compiled from: AndroidParagraph.android.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ResolvedTextDirection.values().length];
            try {
                iArr[ResolvedTextDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ResolvedTextDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ AndroidParagraph(AndroidParagraphIntrinsics androidParagraphIntrinsics, int i, int i2, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(androidParagraphIntrinsics, i, i2, j);
    }

    public /* synthetic */ AndroidParagraph(String str, TextStyle textStyle, List list, List list2, int i, int i2, long j, FontFamily.Resolver resolver, Density density, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, list, list2, i, i2, j, resolver, density);
    }

    public static /* synthetic */ void getCharSequence$ui_text$annotations() {
    }

    public static /* synthetic */ void getTextLocale$ui_text$annotations() {
    }

    public static /* synthetic */ void getTextPaint$ui_text$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0205  */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private AndroidParagraph(AndroidParagraphIntrinsics androidParagraphIntrinsics, int i, int i2, long j) {
        boolean shouldAttachIndentationFixSpan;
        CharSequence charSequence;
        int m6654toLayoutAlignaXe7zB0;
        int m6656toLayoutHyphenationFrequency3fSNIE;
        int m6655toLayoutBreakStrategyxImikfE;
        int m6657toLayoutLineBreakStylehpcqdu8;
        int m6658toLayoutLineBreakWordStylewPN0Rpw;
        TextStyle textStyle;
        int i3;
        int i4;
        int i5;
        TextUtils.TruncateAt truncateAt;
        TextLayout constructTextLayout$default;
        int i6;
        ?? r15;
        int i7;
        AndroidParagraph androidParagraph;
        ShaderBrushSpan[] shaderBrushSpans;
        CharSequence charSequence2;
        ArrayList arrayList;
        Rect rect;
        float horizontalPosition;
        float lineBaseline;
        int heightPx;
        float lineTop;
        float f;
        float lineBaseline2;
        int numberOfLinesThatFitMaxHeight;
        AndroidParagraph androidParagraph2;
        this.paragraphIntrinsics = androidParagraphIntrinsics;
        this.maxLines = i;
        this.overflow = i2;
        this.constraints = j;
        if (!(Constraints.m7343getMinHeightimpl(j) == 0 && Constraints.m7344getMinWidthimpl(j) == 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        if (!(i >= 1)) {
            InlineClassHelperKt.throwIllegalArgumentException("maxLines should be greater than 0");
        }
        TextStyle style = androidParagraphIntrinsics.getStyle();
        shouldAttachIndentationFixSpan = AndroidParagraph_androidKt.shouldAttachIndentationFixSpan(style, TextOverflow.m7315equalsimpl0(i2, TextOverflow.INSTANCE.m7325getEllipsisgIe3tQ8()));
        if (shouldAttachIndentationFixSpan) {
            charSequence = AndroidParagraph_androidKt.attachIndentationFixSpan(androidParagraphIntrinsics.getCharSequence());
        } else {
            charSequence = androidParagraphIntrinsics.getCharSequence();
        }
        this.charSequence = charSequence;
        m6654toLayoutAlignaXe7zB0 = AndroidParagraph_androidKt.m6654toLayoutAlignaXe7zB0(style.m6889getTextAligne0LSkKk());
        boolean m7269equalsimpl0 = TextAlign.m7269equalsimpl0(style.m6889getTextAligne0LSkKk(), TextAlign.INSTANCE.m7275getJustifye0LSkKk());
        m6656toLayoutHyphenationFrequency3fSNIE = AndroidParagraph_androidKt.m6656toLayoutHyphenationFrequency3fSNIE(style.getParagraphStyle().getHyphens());
        m6655toLayoutBreakStrategyxImikfE = AndroidParagraph_androidKt.m6655toLayoutBreakStrategyxImikfE(LineBreak.m7181getStrategyfcGXIks(style.m6886getLineBreakrAG3T2k()));
        m6657toLayoutLineBreakStylehpcqdu8 = AndroidParagraph_androidKt.m6657toLayoutLineBreakStylehpcqdu8(LineBreak.m7182getStrictnessusljTpc(style.m6886getLineBreakrAG3T2k()));
        m6658toLayoutLineBreakWordStylewPN0Rpw = AndroidParagraph_androidKt.m6658toLayoutLineBreakWordStylewPN0Rpw(LineBreak.m7183getWordBreakjp8hJ3c(style.m6886getLineBreakrAG3T2k()));
        if (TextOverflow.m7315equalsimpl0(i2, TextOverflow.INSTANCE.m7325getEllipsisgIe3tQ8())) {
            truncateAt = TextUtils.TruncateAt.END;
        } else if (TextOverflow.m7315equalsimpl0(i2, TextOverflow.INSTANCE.m7326getMiddleEllipsisgIe3tQ8())) {
            truncateAt = TextUtils.TruncateAt.MIDDLE;
        } else {
            if (!TextOverflow.m7315equalsimpl0(i2, TextOverflow.INSTANCE.m7327getStartEllipsisgIe3tQ8())) {
                textStyle = style;
                i3 = m7269equalsimpl0 ? 1 : 0;
                i4 = m6655toLayoutBreakStrategyxImikfE;
                i5 = m6658toLayoutLineBreakWordStylewPN0Rpw;
                truncateAt = null;
                TextUtils.TruncateAt truncateAt2 = truncateAt;
                CharSequence charSequence3 = charSequence;
                constructTextLayout$default = constructTextLayout$default(this, m6654toLayoutAlignaXe7zB0, i3, truncateAt2, i, m6656toLayoutHyphenationFrequency3fSNIE, i4, m6657toLayoutLineBreakStylehpcqdu8, i5, null, 256, null);
                if (Build.VERSION.SDK_INT < 35 || getTextPaint$ui_text().getLetterSpacing() == 0.0f || (!(TextOverflow.m7315equalsimpl0(i2, TextOverflow.INSTANCE.m7327getStartEllipsisgIe3tQ8()) || TextOverflow.m7315equalsimpl0(i2, TextOverflow.INSTANCE.m7326getMiddleEllipsisgIe3tQ8())) || constructTextLayout$default.getLineEllipsisCount(0) <= 0)) {
                    i6 = i;
                    r15 = 1;
                } else {
                    int lineEllipsisOffset = constructTextLayout$default.getLineEllipsisOffset(0);
                    r15 = 1;
                    i6 = i;
                    constructTextLayout$default = constructTextLayout(m6654toLayoutAlignaXe7zB0, i3, truncateAt2, i6, m6656toLayoutHyphenationFrequency3fSNIE, i4, m6657toLayoutLineBreakStylehpcqdu8, i5, TextUtils.concat(charSequence3.subSequence(0, lineEllipsisOffset), "…", charSequence3.subSequence(constructTextLayout$default.getLineEllipsisCount(0) + lineEllipsisOffset, charSequence3.length())));
                }
                if (!TextOverflow.m7315equalsimpl0(i2, TextOverflow.INSTANCE.m7325getEllipsisgIe3tQ8()) && constructTextLayout$default.getHeight() > Constraints.m7341getMaxHeightimpl(j) && i6 > r15) {
                    numberOfLinesThatFitMaxHeight = AndroidParagraph_androidKt.numberOfLinesThatFitMaxHeight(constructTextLayout$default, Constraints.m7341getMaxHeightimpl(j));
                    if (numberOfLinesThatFitMaxHeight < 0 || numberOfLinesThatFitMaxHeight == i6) {
                        androidParagraph2 = this;
                        i7 = 2;
                    } else {
                        int coerceAtLeast = RangesKt.coerceAtLeast(numberOfLinesThatFitMaxHeight, (int) r15);
                        i7 = 2;
                        AndroidParagraph androidParagraph3 = this;
                        constructTextLayout$default = constructTextLayout$default(androidParagraph3, m6654toLayoutAlignaXe7zB0, i3, truncateAt2, coerceAtLeast, m6656toLayoutHyphenationFrequency3fSNIE, i4, m6657toLayoutLineBreakStylehpcqdu8, i5, null, 256, null);
                        androidParagraph2 = androidParagraph3;
                    }
                    androidParagraph2.layout = constructTextLayout$default;
                    androidParagraph = androidParagraph2;
                } else {
                    AndroidParagraph androidParagraph4 = this;
                    i7 = 2;
                    androidParagraph4.layout = constructTextLayout$default;
                    androidParagraph = androidParagraph4;
                }
                androidParagraph.getTextPaint$ui_text().m7121setBrush12SF9DM(textStyle.getBrush(), Size.m4403constructorimpl((Float.floatToRawIntBits(androidParagraph.getHeight()) & 4294967295L) | (Float.floatToRawIntBits(androidParagraph.getWidth()) << 32)), textStyle.getAlpha());
                shaderBrushSpans = androidParagraph.getShaderBrushSpans(androidParagraph.layout);
                if (shaderBrushSpans != null) {
                    Iterator it = ArrayIteratorKt.iterator(shaderBrushSpans);
                    while (it.hasNext()) {
                        ((ShaderBrushSpan) it.next()).m7144setSizeuvyYCjk(Size.m4403constructorimpl((Float.floatToRawIntBits(androidParagraph.getHeight()) & 4294967295L) | (Float.floatToRawIntBits(androidParagraph.getWidth()) << 32)));
                    }
                }
                charSequence2 = androidParagraph.charSequence;
                if (charSequence2 instanceof Spanned) {
                    arrayList = CollectionsKt.emptyList();
                } else {
                    Spanned spanned = (Spanned) charSequence2;
                    Object[] spans = spanned.getSpans(0, charSequence2.length(), PlaceholderSpan.class);
                    ArrayList arrayList2 = new ArrayList(spans.length);
                    for (Object obj : spans) {
                        PlaceholderSpan placeholderSpan = (PlaceholderSpan) obj;
                        int spanStart = spanned.getSpanStart(placeholderSpan);
                        int spanEnd = spanned.getSpanEnd(placeholderSpan);
                        int lineForOffset = androidParagraph.layout.getLineForOffset(spanStart);
                        boolean z = lineForOffset >= androidParagraph.maxLines ? r15 : false;
                        boolean z2 = (androidParagraph.layout.getLineEllipsisCount(lineForOffset) <= 0 || spanEnd <= androidParagraph.layout.getLineEllipsisOffset(lineForOffset)) ? false : r15;
                        boolean z3 = spanEnd > androidParagraph.layout.getLineEnd(lineForOffset) ? r15 : false;
                        if (z2 || z3 || z) {
                            rect = null;
                        } else {
                            int i8 = WhenMappings.$EnumSwitchMapping$0[androidParagraph.getBidiRunDirection(spanStart).ordinal()];
                            if (i8 == r15) {
                                horizontalPosition = androidParagraph.getHorizontalPosition(spanStart, r15);
                            } else {
                                if (i8 != i7) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                horizontalPosition = androidParagraph.getHorizontalPosition(spanStart, r15) - placeholderSpan.getWidthPx();
                            }
                            float widthPx = placeholderSpan.getWidthPx() + horizontalPosition;
                            TextLayout textLayout = androidParagraph.layout;
                            switch (placeholderSpan.getVerticalAlign()) {
                                case 0:
                                    lineBaseline = textLayout.getLineBaseline(lineForOffset);
                                    heightPx = placeholderSpan.getHeightPx();
                                    lineTop = lineBaseline - heightPx;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 1:
                                    lineTop = textLayout.getLineTop(lineForOffset);
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 2:
                                    lineBaseline = textLayout.getLineBottom(lineForOffset);
                                    heightPx = placeholderSpan.getHeightPx();
                                    lineTop = lineBaseline - heightPx;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 3:
                                    lineTop = ((textLayout.getLineTop(lineForOffset) + textLayout.getLineBottom(lineForOffset)) - placeholderSpan.getHeightPx()) / i7;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 4:
                                    f = placeholderSpan.getFontMetrics().ascent;
                                    lineBaseline2 = textLayout.getLineBaseline(lineForOffset);
                                    lineTop = f + lineBaseline2;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 5:
                                    lineTop = (placeholderSpan.getFontMetrics().descent + textLayout.getLineBaseline(lineForOffset)) - placeholderSpan.getHeightPx();
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                case 6:
                                    Paint.FontMetricsInt fontMetrics = placeholderSpan.getFontMetrics();
                                    f = ((fontMetrics.ascent + fontMetrics.descent) - placeholderSpan.getHeightPx()) / i7;
                                    lineBaseline2 = textLayout.getLineBaseline(lineForOffset);
                                    lineTop = f + lineBaseline2;
                                    rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                                    break;
                                default:
                                    throw new IllegalStateException("unexpected verticalAlignment");
                            }
                        }
                        arrayList2.add(rect);
                    }
                    arrayList = arrayList2;
                }
                androidParagraph.placeholderRects = arrayList;
            }
            truncateAt = TextUtils.TruncateAt.START;
        }
        textStyle = style;
        i3 = m7269equalsimpl0 ? 1 : 0;
        i4 = m6655toLayoutBreakStrategyxImikfE;
        i5 = m6658toLayoutLineBreakWordStylewPN0Rpw;
        TextUtils.TruncateAt truncateAt22 = truncateAt;
        CharSequence charSequence32 = charSequence;
        constructTextLayout$default = constructTextLayout$default(this, m6654toLayoutAlignaXe7zB0, i3, truncateAt22, i, m6656toLayoutHyphenationFrequency3fSNIE, i4, m6657toLayoutLineBreakStylehpcqdu8, i5, null, 256, null);
        if (Build.VERSION.SDK_INT < 35) {
        }
        i6 = i;
        r15 = 1;
        if (!TextOverflow.m7315equalsimpl0(i2, TextOverflow.INSTANCE.m7325getEllipsisgIe3tQ8())) {
        }
        AndroidParagraph androidParagraph42 = this;
        i7 = 2;
        androidParagraph42.layout = constructTextLayout$default;
        androidParagraph = androidParagraph42;
        androidParagraph.getTextPaint$ui_text().m7121setBrush12SF9DM(textStyle.getBrush(), Size.m4403constructorimpl((Float.floatToRawIntBits(androidParagraph.getHeight()) & 4294967295L) | (Float.floatToRawIntBits(androidParagraph.getWidth()) << 32)), textStyle.getAlpha());
        shaderBrushSpans = androidParagraph.getShaderBrushSpans(androidParagraph.layout);
        if (shaderBrushSpans != null) {
        }
        charSequence2 = androidParagraph.charSequence;
        if (charSequence2 instanceof Spanned) {
        }
        androidParagraph.placeholderRects = arrayList;
    }

    public final AndroidParagraphIntrinsics getParagraphIntrinsics() {
        return this.paragraphIntrinsics;
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    /* renamed from: getOverflow-gIe3tQ8, reason: not valid java name and from getter */
    public final int getOverflow() {
        return this.overflow;
    }

    /* renamed from: getConstraints-msEJaDk, reason: not valid java name and from getter */
    public final long getConstraints() {
        return this.constraints;
    }

    private AndroidParagraph(String str, TextStyle textStyle, List<? extends AnnotatedString.Range<? extends AnnotatedString.Annotation>> list, List<AnnotatedString.Range<Placeholder>> list2, int i, int i2, long j, FontFamily.Resolver resolver, Density density) {
        this(new AndroidParagraphIntrinsics(str, textStyle, list, list2, resolver, density), i, i2, j, null);
    }

    /* renamed from: getCharSequence$ui_text, reason: from getter */
    public final CharSequence getCharSequence() {
        return this.charSequence;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getWidth() {
        return Constraints.m7342getMaxWidthimpl(this.constraints);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getHeight() {
        return this.layout.getHeight();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getMaxIntrinsicWidth() {
        return this.paragraphIntrinsics.getMaxIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getMinIntrinsicWidth() {
        return this.paragraphIntrinsics.getMinIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getFirstBaseline() {
        return getLineBaseline(0);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLastBaseline() {
        return getLineBaseline(getLineCount() - 1);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public boolean getDidExceedMaxLines() {
        return this.layout.getDidExceedMaxLines();
    }

    public final Locale getTextLocale$ui_text() {
        return this.paragraphIntrinsics.getTextPaint().getTextLocale();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineCount() {
        return this.layout.getLineCount();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public List<Rect> getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final AndroidTextPaint getTextPaint$ui_text() {
        return this.paragraphIntrinsics.getTextPaint();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineForVerticalPosition(float vertical) {
        return this.layout.getLineForVertical((int) vertical);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public int mo6641getOffsetForPositionk4lQ0M(long position) {
        return this.layout.getOffsetForHorizontal(this.layout.getLineForVertical((int) Float.intBitsToFloat((int) (4294967295L & position))), Float.intBitsToFloat((int) (position >> 32)));
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* renamed from: getRangeForRect-8-6BmAI, reason: not valid java name */
    public long mo6643getRangeForRect86BmAI(Rect rect, int granularity, final TextInclusionStrategy inclusionStrategy) {
        int m6659toLayoutTextGranularityduNsdkg;
        TextLayout textLayout = this.layout;
        RectF androidRectF = RectHelper_androidKt.toAndroidRectF(rect);
        m6659toLayoutTextGranularityduNsdkg = AndroidParagraph_androidKt.m6659toLayoutTextGranularityduNsdkg(granularity);
        int[] rangeForRect = textLayout.getRangeForRect(androidRectF, m6659toLayoutTextGranularityduNsdkg, new Function2() { // from class: androidx.compose.ui.text.AndroidParagraph$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                boolean rangeForRect_8_6BmAI$lambda$6;
                rangeForRect_8_6BmAI$lambda$6 = AndroidParagraph.getRangeForRect_8_6BmAI$lambda$6(TextInclusionStrategy.this, (RectF) obj, (RectF) obj2);
                return Boolean.valueOf(rangeForRect_8_6BmAI$lambda$6);
            }
        });
        if (rangeForRect == null) {
            return TextRange.INSTANCE.m6853getZerod9O1mEE();
        }
        return TextRangeKt.TextRange(rangeForRect[0], rangeForRect[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getRangeForRect_8_6BmAI$lambda$6(TextInclusionStrategy textInclusionStrategy, RectF rectF, RectF rectF2) {
        return textInclusionStrategy.isIncluded(RectHelper_androidKt.toComposeRect(rectF), RectHelper_androidKt.toComposeRect(rectF2));
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Rect getBoundingBox(int offset) {
        boolean z = false;
        if (offset >= 0 && offset < this.charSequence.length()) {
            z = true;
        }
        if (!z) {
            InlineClassHelperKt.throwIllegalArgumentException("offset(" + offset + ") is out of bounds [0," + this.charSequence.length() + ')');
        }
        RectF boundingBox = this.layout.getBoundingBox(offset);
        return new Rect(boundingBox.left, boundingBox.top, boundingBox.right, boundingBox.bottom);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* renamed from: fillBoundingBoxes-8ffj60Q, reason: not valid java name */
    public void mo6639fillBoundingBoxes8ffj60Q(long range, float[] array, int arrayStart) {
        this.layout.fillBoundingBoxes(TextRange.m6846getMinimpl(range), TextRange.m6845getMaximpl(range), array, arrayStart);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Path getPathForRange(int start, int end) {
        if (!(start >= 0 && start <= end && end <= this.charSequence.length())) {
            InlineClassHelperKt.throwIllegalArgumentException("start(" + start + ") or end(" + end + ") is out of range [0.." + this.charSequence.length() + "], or start > end!");
        }
        android.graphics.Path path = new android.graphics.Path();
        this.layout.getSelectionPath(start, end, path);
        return AndroidPath_androidKt.asComposePath(path);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Rect getCursorRect(int offset) {
        if (!(offset >= 0 && offset <= this.charSequence.length())) {
            InlineClassHelperKt.throwIllegalArgumentException("offset(" + offset + ") is out of bounds [0," + this.charSequence.length() + ']');
        }
        float primaryHorizontal$default = TextLayout.getPrimaryHorizontal$default(this.layout, offset, false, 2, null);
        int lineForOffset = this.layout.getLineForOffset(offset);
        return new Rect(primaryHorizontal$default, this.layout.getLineTop(lineForOffset), primaryHorizontal$default, this.layout.getLineBottom(lineForOffset));
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public long mo6644getWordBoundaryjx7JFs(int offset) {
        WordIterator wordIterator = this.layout.getWordIterator();
        return TextRangeKt.TextRange(WordBoundary_androidKt.getWordStart(wordIterator, offset), WordBoundary_androidKt.getWordEnd(wordIterator, offset));
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineLeft(int lineIndex) {
        return this.layout.getLineLeft(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineRight(int lineIndex) {
        return this.layout.getLineRight(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineTop(int lineIndex) {
        return this.layout.getLineTop(lineIndex);
    }

    public final float getLineAscent$ui_text(int lineIndex) {
        return this.layout.getLineAscent(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineBaseline(int lineIndex) {
        return this.layout.getLineBaseline(lineIndex);
    }

    public final float getLineDescent$ui_text(int lineIndex) {
        return this.layout.getLineDescent(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineBottom(int lineIndex) {
        return this.layout.getLineBottom(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineHeight(int lineIndex) {
        return this.layout.getLineHeight(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineWidth(int lineIndex) {
        return this.layout.getLineWidth(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineStart(int lineIndex) {
        return this.layout.getLineStart(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineEnd(int lineIndex, boolean visibleEnd) {
        if (visibleEnd) {
            return this.layout.getLineVisibleEnd(lineIndex);
        }
        return this.layout.getLineEnd(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public boolean isLineEllipsized(int lineIndex) {
        return this.layout.isLineEllipsized(lineIndex);
    }

    public final int getLineEllipsisOffset$ui_text(int lineIndex) {
        return this.layout.getLineEllipsisOffset(lineIndex);
    }

    public final int getLineEllipsisCount$ui_text(int lineIndex) {
        return this.layout.getLineEllipsisCount(lineIndex);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineForOffset(int offset) {
        return this.layout.getLineForOffset(offset);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getHorizontalPosition(int offset, boolean usePrimaryDirection) {
        if (usePrimaryDirection) {
            return TextLayout.getPrimaryHorizontal$default(this.layout, offset, false, 2, null);
        }
        return TextLayout.getSecondaryHorizontal$default(this.layout, offset, false, 2, null);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public ResolvedTextDirection getParagraphDirection(int offset) {
        return this.layout.getParagraphDirection(this.layout.getLineForOffset(offset)) == 1 ? ResolvedTextDirection.Ltr : ResolvedTextDirection.Rtl;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public ResolvedTextDirection getBidiRunDirection(int offset) {
        return this.layout.isRtlCharAt(offset) ? ResolvedTextDirection.Rtl : ResolvedTextDirection.Ltr;
    }

    private final ShaderBrushSpan[] getShaderBrushSpans(TextLayout textLayout) {
        if (!(textLayout.getText() instanceof Spanned)) {
            return null;
        }
        CharSequence text = textLayout.getText();
        Intrinsics.checkNotNull(text, "null cannot be cast to non-null type android.text.Spanned");
        if (!hasSpan((Spanned) text, ShaderBrushSpan.class)) {
            return null;
        }
        CharSequence text2 = textLayout.getText();
        Intrinsics.checkNotNull(text2, "null cannot be cast to non-null type android.text.Spanned");
        return (ShaderBrushSpan[]) ((Spanned) text2).getSpans(0, textLayout.getText().length(), ShaderBrushSpan.class);
    }

    private final boolean hasSpan(Spanned spanned, Class<?> cls) {
        return spanned.nextSpanTransition(-1, spanned.length(), cls) != spanned.length();
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* renamed from: paint-RPmYEkk, reason: not valid java name */
    public void mo6646paintRPmYEkk(Canvas canvas, long color, Shadow shadow, TextDecoration textDecoration) {
        AndroidTextPaint textPaint$ui_text = getTextPaint$ui_text();
        textPaint$ui_text.m7123setColor8_81llA(color);
        textPaint$ui_text.setShadow(shadow);
        textPaint$ui_text.setTextDecoration(textDecoration);
        paint(canvas);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* renamed from: paint-LG529CI, reason: not valid java name */
    public void mo6645paintLG529CI(Canvas canvas, long color, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode) {
        int backingBlendMode = getTextPaint$ui_text().getBackingBlendMode();
        AndroidTextPaint textPaint$ui_text = getTextPaint$ui_text();
        textPaint$ui_text.m7123setColor8_81llA(color);
        textPaint$ui_text.setShadow(shadow);
        textPaint$ui_text.setTextDecoration(textDecoration);
        textPaint$ui_text.setDrawStyle(drawStyle);
        textPaint$ui_text.m7120setBlendModes9anfk8(blendMode);
        paint(canvas);
        getTextPaint$ui_text().m7120setBlendModes9anfk8(backingBlendMode);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* renamed from: paint-hn5TExg, reason: not valid java name */
    public void mo6647painthn5TExg(Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int blendMode) {
        int backingBlendMode = getTextPaint$ui_text().getBackingBlendMode();
        AndroidTextPaint textPaint$ui_text = getTextPaint$ui_text();
        float width = getWidth();
        float height = getHeight();
        textPaint$ui_text.m7121setBrush12SF9DM(brush, Size.m4403constructorimpl((Float.floatToRawIntBits(height) & 4294967295L) | (Float.floatToRawIntBits(width) << 32)), alpha);
        textPaint$ui_text.setShadow(shadow);
        textPaint$ui_text.setTextDecoration(textDecoration);
        textPaint$ui_text.setDrawStyle(drawStyle);
        textPaint$ui_text.m7120setBlendModes9anfk8(blendMode);
        paint(canvas);
        getTextPaint$ui_text().m7120setBlendModes9anfk8(backingBlendMode);
    }

    private final void paint(Canvas canvas) {
        android.graphics.Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (getDidExceedMaxLines()) {
            nativeCanvas.save();
            nativeCanvas.clipRect(0.0f, 0.0f, getWidth(), getHeight());
        }
        this.layout.paint(nativeCanvas);
        if (getDidExceedMaxLines()) {
            nativeCanvas.restore();
        }
    }

    static /* synthetic */ TextLayout constructTextLayout$default(AndroidParagraph androidParagraph, int i, int i2, TextUtils.TruncateAt truncateAt, int i3, int i4, int i5, int i6, int i7, CharSequence charSequence, int i8, Object obj) {
        return androidParagraph.constructTextLayout(i, i2, truncateAt, i3, i4, i5, i6, i7, (i8 & 256) != 0 ? androidParagraph.charSequence : charSequence);
    }

    private final TextLayout constructTextLayout(int alignment, int justificationMode, TextUtils.TruncateAt ellipsize, int maxLines, int hyphens, int breakStrategy, int lineBreakStyle, int lineBreakWordStyle, CharSequence charSequence) {
        float width = getWidth();
        AndroidTextPaint textPaint$ui_text = getTextPaint$ui_text();
        return new TextLayout(charSequence, width, textPaint$ui_text, alignment, ellipsize, this.paragraphIntrinsics.getTextDirectionHeuristic(), 1.0f, 0.0f, AndroidParagraphHelper_androidKt.isIncludeFontPaddingEnabled(this.paragraphIntrinsics.getStyle()), true, maxLines, breakStrategy, lineBreakStyle, lineBreakWordStyle, hyphens, justificationMode, null, null, this.paragraphIntrinsics.getLayoutIntrinsics(), 196736, null);
    }
}
