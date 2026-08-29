package androidx.compose.foundation.text.contextmenu.internal;

import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import kotlin.Metadata;

/* compiled from: DefaultTextContextMenuDropdownProvider.android.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J/\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\bH\u0016¢\u0006\u0004\b\"\u0010#R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006$"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/internal/MaintainWindowPositionPopupPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "popupPositionProvider", "<init>", "(Landroidx/compose/ui/window/PopupPositionProvider;)V", "getPopupPositionProvider", "()Landroidx/compose/ui/window/PopupPositionProvider;", "previousWindowSize", "Landroidx/compose/ui/unit/IntSize;", "getPreviousWindowSize-bOM6tXw", "()Landroidx/compose/ui/unit/IntSize;", "setPreviousWindowSize-fhxjrPA", "(Landroidx/compose/ui/unit/IntSize;)V", "previousLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getPreviousLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setPreviousLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "previousPopupContentSize", "getPreviousPopupContentSize-bOM6tXw", "setPreviousPopupContentSize-fhxjrPA", "previousPosition", "Landroidx/compose/ui/unit/IntOffset;", "getPreviousPosition-JyOPPKE", "()Landroidx/compose/ui/unit/IntOffset;", "setPreviousPosition-fg0MpWk", "(Landroidx/compose/ui/unit/IntOffset;)V", "calculatePosition", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "layoutDirection", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
final class MaintainWindowPositionPopupPositionProvider implements PopupPositionProvider {
    private final PopupPositionProvider popupPositionProvider;
    private LayoutDirection previousLayoutDirection;
    private IntSize previousPopupContentSize;
    private IntOffset previousPosition;
    private IntSize previousWindowSize;

    public MaintainWindowPositionPopupPositionProvider(PopupPositionProvider popupPositionProvider) {
        this.popupPositionProvider = popupPositionProvider;
    }

    public final PopupPositionProvider getPopupPositionProvider() {
        return this.popupPositionProvider;
    }

    /* renamed from: getPreviousWindowSize-bOM6tXw, reason: not valid java name and from getter */
    public final IntSize getPreviousWindowSize() {
        return this.previousWindowSize;
    }

    /* renamed from: setPreviousWindowSize-fhxjrPA, reason: not valid java name */
    public final void m1452setPreviousWindowSizefhxjrPA(IntSize intSize) {
        this.previousWindowSize = intSize;
    }

    public final LayoutDirection getPreviousLayoutDirection() {
        return this.previousLayoutDirection;
    }

    public final void setPreviousLayoutDirection(LayoutDirection layoutDirection) {
        this.previousLayoutDirection = layoutDirection;
    }

    /* renamed from: getPreviousPopupContentSize-bOM6tXw, reason: not valid java name and from getter */
    public final IntSize getPreviousPopupContentSize() {
        return this.previousPopupContentSize;
    }

    /* renamed from: setPreviousPopupContentSize-fhxjrPA, reason: not valid java name */
    public final void m1450setPreviousPopupContentSizefhxjrPA(IntSize intSize) {
        this.previousPopupContentSize = intSize;
    }

    /* renamed from: getPreviousPosition-JyOPPKE, reason: not valid java name and from getter */
    public final IntOffset getPreviousPosition() {
        return this.previousPosition;
    }

    /* renamed from: setPreviousPosition-fg0MpWk, reason: not valid java name */
    public final void m1451setPreviousPositionfg0MpWk(IntOffset intOffset) {
        this.previousPosition = intOffset;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* renamed from: calculatePosition-llwVHH4 */
    public long mo423calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        IntOffset intOffset = this.previousPosition;
        if (intOffset != null) {
            IntSize intSize = this.previousWindowSize;
            if ((intSize == null ? false : IntSize.m7572equalsimpl0(intSize.m7578unboximpl(), windowSize)) && this.previousLayoutDirection == layoutDirection) {
                IntSize intSize2 = this.previousPopupContentSize;
                if (intSize2 != null ? IntSize.m7572equalsimpl0(intSize2.m7578unboximpl(), popupContentSize) : false) {
                    return intOffset.m7540unboximpl();
                }
            }
        }
        long mo423calculatePositionllwVHH4 = this.popupPositionProvider.mo423calculatePositionllwVHH4(anchorBounds, windowSize, layoutDirection, popupContentSize);
        this.previousWindowSize = IntSize.m7566boximpl(windowSize);
        this.previousLayoutDirection = layoutDirection;
        this.previousPopupContentSize = IntSize.m7566boximpl(popupContentSize);
        this.previousPosition = IntOffset.m7522boximpl(mo423calculatePositionllwVHH4);
        return mo423calculatePositionllwVHH4;
    }
}
