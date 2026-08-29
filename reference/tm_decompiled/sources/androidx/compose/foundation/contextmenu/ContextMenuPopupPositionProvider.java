package androidx.compose.foundation.contextmenu;

import androidx.autofill.HintConstants;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ContextMenuPopupPositionProvider.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001BQ\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012:\b\u0002\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006¢\u0006\u0004\b\r\u0010\u000eBM\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012:\b\u0002\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006¢\u0006\u0004\b\r\u0010\u0010J/\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/contextmenu/ContextMenuPopupPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "anchorPositionBlock", "Lkotlin/Function0;", "Landroidx/compose/ui/unit/IntOffset;", "onPositionCalculated", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "position", "Landroidx/compose/ui/unit/IntRect;", "menuBounds", "", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)V", "anchorPosition", "(JLkotlin/jvm/functions/Function2;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "calculatePosition", "anchorBounds", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "foundation_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextMenuPopupPositionProvider implements PopupPositionProvider {
    public static final int $stable = 0;
    private final Function0<IntOffset> anchorPositionBlock;
    private final Function2<IntOffset, IntRect, Unit> onPositionCalculated;

    public /* synthetic */ ContextMenuPopupPositionProvider(long j, Function2 function2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (Function2<? super IntOffset, ? super IntRect, Unit>) function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContextMenuPopupPositionProvider(Function0<IntOffset> function0, Function2<? super IntOffset, ? super IntRect, Unit> function2) {
        this.anchorPositionBlock = function0;
        this.onPositionCalculated = function2;
    }

    public /* synthetic */ ContextMenuPopupPositionProvider(Function0 function0, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((Function0<IntOffset>) function0, (Function2<? super IntOffset, ? super IntRect, Unit>) ((i & 2) != 0 ? null : function2));
    }

    public /* synthetic */ ContextMenuPopupPositionProvider(long j, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? null : function2, null);
    }

    private ContextMenuPopupPositionProvider(final long j, Function2<? super IntOffset, ? super IntRect, Unit> function2) {
        this((Function0<IntOffset>) new Function0() { // from class: androidx.compose.foundation.contextmenu.ContextMenuPopupPositionProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                IntOffset m7522boximpl;
                m7522boximpl = IntOffset.m7522boximpl(j);
                return m7522boximpl;
            }
        }, function2);
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* renamed from: calculatePosition-llwVHH4, reason: not valid java name */
    public long mo423calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        long m7540unboximpl = this.anchorPositionBlock.invoke().m7540unboximpl();
        long m7525constructorimpl = IntOffset.m7525constructorimpl((ContextMenuPopupPositionProviderKt.alignPopupAxis(anchorBounds.getLeft() + IntOffset.m7531getXimpl(m7540unboximpl), (int) (popupContentSize >> 32), (int) (windowSize >> 32), layoutDirection == LayoutDirection.Ltr) << 32) | (4294967295L & ContextMenuPopupPositionProviderKt.alignPopupAxis$default(anchorBounds.getTop() + IntOffset.m7532getYimpl(m7540unboximpl), (int) (popupContentSize & 4294967295L), (int) (windowSize & 4294967295L), false, 8, null)));
        Function2<IntOffset, IntRect, Unit> function2 = this.onPositionCalculated;
        if (function2 != null) {
            function2.invoke(IntOffset.m7522boximpl(m7540unboximpl), IntRectKt.m7564IntRectVbeCjmY(m7525constructorimpl, popupContentSize));
        }
        return m7525constructorimpl;
    }
}
