package androidx.compose.foundation.lazy;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;

/* compiled from: LazyDsl.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a©\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042%\b\n\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00062%\b\n\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000623\b\u0004\u0010\f\u001a-\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0011\u001a\u0082\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042%\b\n\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u000623\b\u0004\u0010\f\u001a-\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0002\u0010\u0012\u001aè\u0001\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042:\b\n\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\r2:\b\u0006\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\r2H\b\u0004\u0010\f\u001aB\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u0017\u001a¬\u0001\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042:\b\n\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\r2H\b\u0004\u0010\f\u001aB\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0002\u0010\u0018\u001a©\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00192%\b\n\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00062%\b\n\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000623\b\u0004\u0010\f\u001a-\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u001a\u001a\u0082\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00192%\b\n\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u000623\b\u0004\u0010\f\u001a-\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0002\u0010\u001b\u001aè\u0001\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00192:\b\n\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\r2:\b\u0006\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\r2H\b\u0004\u0010\f\u001aB\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0086\b¢\u0006\u0002\u0010\u001c\u001a¬\u0001\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00192:\b\n\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\r2H\b\u0004\u0010\f\u001aB\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0002\u0010\u001d\u001a\u0082\u0001\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020&2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u00101\u001a\u0082\u0001\u00102\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020&2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u00107\u001av\u00102\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020&2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u00108\u001al\u00102\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010+\u001a\u00020,2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u00109\u001av\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020&2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010:\u001al\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010;¨\u0006<"}, d2 = {"items", "", "T", "Landroidx/compose/foundation/lazy/LazyListScope;", "", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "item", "", "contentType", "itemContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "", "index", "Lkotlin/Function3;", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "LazyRow", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LazyColumn", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyDslKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LazyColumn$lambda$1(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyColumn(modifier, lazyListState, paddingValues, z, vertical, horizontal, flingBehavior, z2, overscrollEffect, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LazyColumn$lambda$2(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z2, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyColumn(modifier, lazyListState, paddingValues, z, vertical, horizontal, flingBehavior, z2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LazyColumn$lambda$3(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyColumn(modifier, lazyListState, paddingValues, z, vertical, horizontal, flingBehavior, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LazyRow$lambda$0(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyRow(modifier, lazyListState, paddingValues, z, horizontal, vertical, flingBehavior, z2, overscrollEffect, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LazyRow$lambda$4(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z2, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyRow(modifier, lazyListState, paddingValues, z, horizontal, vertical, flingBehavior, z2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LazyRow$lambda$5(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, Function1 function1, int i, int i2, Composer composer, int i3) {
        LazyRow(modifier, lazyListState, paddingValues, z, horizontal, vertical, flingBehavior, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, List list, Function1 function1, Function1 function12, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            function12 = LazyDslKt$items$1.INSTANCE;
        }
        lazyListScope.items(list.size(), function1 != null ? new LazyDslKt$items$2(function1, list) : null, new LazyDslKt$items$3(function12, list), ComposableLambdaKt.composableLambdaInstance(802480018, true, new LazyDslKt$items$4(function4, list)));
    }

    public static final <T> void items(LazyListScope lazyListScope, List<? extends T> list, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> function12, Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        lazyListScope.items(list.size(), function1 != null ? new LazyDslKt$items$2(function1, list) : null, new LazyDslKt$items$3(function12, list), ComposableLambdaKt.composableLambdaInstance(802480018, true, new LazyDslKt$items$4(function4, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, List list, Function2 function2, Function2 function22, Function5 function5, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function22 = new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$1
                public final Void invoke(int i2, T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).intValue(), (int) obj3);
                }
            };
        }
        lazyListScope.items(list.size(), function2 != null ? new LazyDslKt$itemsIndexed$2(function2, list) : null, new LazyDslKt$itemsIndexed$3(function22, list), ComposableLambdaKt.composableLambdaInstance(2039820996, true, new LazyDslKt$itemsIndexed$4(function5, list)));
    }

    public static final <T> void itemsIndexed(LazyListScope lazyListScope, List<? extends T> list, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> function22, Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5) {
        lazyListScope.items(list.size(), function2 != null ? new LazyDslKt$itemsIndexed$2(function2, list) : null, new LazyDslKt$itemsIndexed$3(function22, list), ComposableLambdaKt.composableLambdaInstance(2039820996, true, new LazyDslKt$itemsIndexed$4(function5, list)));
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, Object[] objArr, Function1 function1, Function1 function12, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            function12 = LazyDslKt$items$5.INSTANCE;
        }
        lazyListScope.items(objArr.length, function1 != null ? new LazyDslKt$items$6(function1, objArr) : null, new LazyDslKt$items$7(function12, objArr), ComposableLambdaKt.composableLambdaInstance(-1781742563, true, new LazyDslKt$items$8(function4, objArr)));
    }

    public static final <T> void items(LazyListScope lazyListScope, T[] tArr, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> function12, Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        lazyListScope.items(tArr.length, function1 != null ? new LazyDslKt$items$6(function1, tArr) : null, new LazyDslKt$items$7(function12, tArr), ComposableLambdaKt.composableLambdaInstance(-1781742563, true, new LazyDslKt$items$8(function4, tArr)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, Object[] objArr, Function2 function2, Function2 function22, Function5 function5, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function22 = new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$5
                public final Void invoke(int i2, T t) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    return invoke(((Number) obj2).intValue(), (int) obj3);
                }
            };
        }
        lazyListScope.items(objArr.length, function2 != null ? new LazyDslKt$itemsIndexed$6(function2, objArr) : null, new LazyDslKt$itemsIndexed$7(function22, objArr), ComposableLambdaKt.composableLambdaInstance(1763000017, true, new LazyDslKt$itemsIndexed$8(function5, objArr)));
    }

    public static final <T> void itemsIndexed(LazyListScope lazyListScope, T[] tArr, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> function22, Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5) {
        lazyListScope.items(tArr.length, function2 != null ? new LazyDslKt$itemsIndexed$6(function2, tArr) : null, new LazyDslKt$itemsIndexed$7(function22, tArr), ComposableLambdaKt.composableLambdaInstance(1763000017, true, new LazyDslKt$itemsIndexed$8(function5, tArr)));
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void LazyRow(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, final Function1<? super LazyListScope, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListState2;
        PaddingValues paddingValues2;
        int i4;
        boolean z3;
        Arrangement.Horizontal horizontal2;
        int i5;
        Alignment.Vertical vertical2;
        FlingBehavior flingBehavior2;
        int i6;
        int i7;
        Composer composer2;
        final Modifier modifier2;
        final boolean z4;
        final LazyListState lazyListState3;
        final PaddingValues paddingValues3;
        final boolean z5;
        final Arrangement.Horizontal horizontal3;
        final Alignment.Vertical vertical3;
        final FlingBehavior flingBehavior3;
        final OverscrollEffect overscrollEffect2;
        ScopeUpdateScope endRestartGroup;
        Arrangement.Horizontal horizontal4;
        FlingBehavior flingBehavior4;
        OverscrollEffect overscrollEffect3;
        Modifier modifier3;
        LazyListState lazyListState4;
        PaddingValues paddingValues4;
        Arrangement.Horizontal horizontal5;
        Alignment.Vertical vertical4;
        boolean z6;
        boolean z7;
        int i8;
        FlingBehavior flingBehavior5;
        int i9;
        int i10;
        int i11;
        int i12;
        Composer startRestartGroup = composer.startRestartGroup(-1884325601);
        ComposerKt.sourceInformation(startRestartGroup, "C(LazyRow)N(modifier,state,contentPadding,reverseLayout,horizontalArrangement,verticalAlignment,flingBehavior,userScrollEnabled,overscrollEffect,content)340@17489L435:LazyDsl.kt#428nma");
        int i13 = i2 & 1;
        if (i13 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListState2 = lazyListState;
                if (startRestartGroup.changed(lazyListState2)) {
                    i12 = 32;
                    i3 |= i12;
                }
            } else {
                lazyListState2 = lazyListState;
            }
            i12 = 16;
            i3 |= i12;
        } else {
            lazyListState2 = lazyListState;
        }
        int i14 = i2 & 4;
        if (i14 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            paddingValues2 = paddingValues;
            i3 |= startRestartGroup.changed(paddingValues2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z3 = z;
                i3 |= startRestartGroup.changed(z3) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        horizontal2 = horizontal;
                        if (startRestartGroup.changed(horizontal2)) {
                            i11 = 16384;
                            i3 |= i11;
                        }
                    } else {
                        horizontal2 = horizontal;
                    }
                    i11 = 8192;
                    i3 |= i11;
                } else {
                    horizontal2 = horizontal;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    vertical2 = vertical;
                    i3 |= startRestartGroup.changed(vertical2) ? 131072 : 65536;
                    if ((1572864 & i) != 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            if (startRestartGroup.changed(flingBehavior2)) {
                                i10 = 1048576;
                                i3 |= i10;
                            }
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i10 = 524288;
                        i3 |= i10;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i6 = i2 & 128;
                    if (i6 == 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        i7 = i6;
                        i3 |= startRestartGroup.changed(z2) ? 8388608 : 4194304;
                        if ((i & 100663296) == 0) {
                            if ((i2 & 256) == 0 && startRestartGroup.changed(overscrollEffect)) {
                                i9 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                i3 |= i9;
                            }
                            i9 = 33554432;
                            i3 |= i9;
                        }
                        if ((i2 & 512) != 0) {
                            i3 |= 805306368;
                        } else if ((i & 805306368) == 0) {
                            i3 |= startRestartGroup.changedInstance(function1) ? 536870912 : 268435456;
                            if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                                startRestartGroup.startDefaults();
                                ComposerKt.sourceInformation(startRestartGroup, "329@16965L23,335@17316L15,337@17414L26");
                                if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i2 & 2) != 0) {
                                        i3 &= -113;
                                    }
                                    if ((i2 & 16) != 0) {
                                        i3 &= -57345;
                                    }
                                    if ((i2 & 64) != 0) {
                                        i3 &= -3670017;
                                    }
                                    if ((i2 & 256) != 0) {
                                        i3 &= -234881025;
                                    }
                                    modifier3 = modifier;
                                    z7 = z2;
                                    overscrollEffect3 = overscrollEffect;
                                    z6 = z3;
                                    horizontal5 = horizontal2;
                                    vertical4 = vertical2;
                                    flingBehavior5 = flingBehavior2;
                                    i8 = -1884325601;
                                    lazyListState4 = lazyListState2;
                                    paddingValues4 = paddingValues2;
                                } else {
                                    Modifier.Companion companion = i13 != 0 ? Modifier.INSTANCE : modifier;
                                    if ((i2 & 2) != 0) {
                                        lazyListState2 = LazyListStateKt.rememberLazyListState(0, 0, startRestartGroup, 0, 3);
                                        i3 &= -113;
                                    }
                                    PaddingValues m871PaddingValues0680j_4 = i14 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues2;
                                    if (i4 != 0) {
                                        z3 = false;
                                    }
                                    if ((i2 & 16) != 0) {
                                        Arrangement arrangement = Arrangement.INSTANCE;
                                        horizontal4 = !z3 ? arrangement.getStart() : arrangement.getEnd();
                                        i3 &= -57345;
                                    } else {
                                        horizontal4 = horizontal2;
                                    }
                                    Alignment.Vertical top = i5 != 0 ? Alignment.INSTANCE.getTop() : vertical2;
                                    if ((i2 & 64) != 0) {
                                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(startRestartGroup, 6);
                                        i3 &= -3670017;
                                    } else {
                                        flingBehavior4 = flingBehavior2;
                                    }
                                    boolean z8 = i7 == 0 ? z2 : true;
                                    if ((i2 & 256) != 0) {
                                        i3 &= -234881025;
                                        modifier3 = companion;
                                        overscrollEffect3 = OverscrollKt.rememberOverscrollEffect(startRestartGroup, 0);
                                    } else {
                                        overscrollEffect3 = overscrollEffect;
                                        modifier3 = companion;
                                    }
                                    lazyListState4 = lazyListState2;
                                    paddingValues4 = m871PaddingValues0680j_4;
                                    horizontal5 = horizontal4;
                                    vertical4 = top;
                                    z6 = z3;
                                    z7 = z8;
                                    i8 = -1884325601;
                                    flingBehavior5 = flingBehavior4;
                                }
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i8, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:339)");
                                }
                                int i15 = i3 >> 3;
                                composer2 = startRestartGroup;
                                LazyListKt.LazyList(modifier3, lazyListState4, paddingValues4, z6, false, flingBehavior5, z7, overscrollEffect3, 0, null, null, vertical4, horizontal5, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i15) | (3670016 & i15) | (i15 & 29360128), ((i3 >> 12) & 112) | ((i3 >> 6) & 896) | ((i3 >> 18) & 7168), 1792);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier2 = modifier3;
                                lazyListState3 = lazyListState4;
                                paddingValues3 = paddingValues4;
                                z5 = z6;
                                flingBehavior3 = flingBehavior5;
                                z4 = z7;
                                overscrollEffect2 = overscrollEffect3;
                                vertical3 = vertical4;
                                horizontal3 = horizontal5;
                            } else {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                modifier2 = modifier;
                                z4 = z2;
                                lazyListState3 = lazyListState2;
                                paddingValues3 = paddingValues2;
                                z5 = z3;
                                horizontal3 = horizontal2;
                                vertical3 = vertical2;
                                flingBehavior3 = flingBehavior2;
                                overscrollEffect2 = overscrollEffect;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit LazyRow$lambda$0;
                                        LazyRow$lambda$0 = LazyDslKt.LazyRow$lambda$0(Modifier.this, lazyListState3, paddingValues3, z5, horizontal3, vertical3, flingBehavior3, z4, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                        return LazyRow$lambda$0;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i7 = i6;
                    if ((i & 100663296) == 0) {
                    }
                    if ((i2 & 512) != 0) {
                    }
                    if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                vertical2 = vertical;
                if ((1572864 & i) != 0) {
                }
                i6 = i2 & 128;
                if (i6 == 0) {
                }
                i7 = i6;
                if ((i & 100663296) == 0) {
                }
                if ((i2 & 512) != 0) {
                }
                if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z3 = z;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            vertical2 = vertical;
            if ((1572864 & i) != 0) {
            }
            i6 = i2 & 128;
            if (i6 == 0) {
            }
            i7 = i6;
            if ((i & 100663296) == 0) {
            }
            if ((i2 & 512) != 0) {
            }
            if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z3 = z;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        vertical2 = vertical;
        if ((1572864 & i) != 0) {
        }
        i6 = i2 & 128;
        if (i6 == 0) {
        }
        i7 = i6;
        if ((i & 100663296) == 0) {
        }
        if ((i2 & 512) != 0) {
        }
        if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void LazyColumn(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, final Function1<? super LazyListScope, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListState2;
        PaddingValues paddingValues2;
        int i4;
        boolean z3;
        Arrangement.Vertical vertical2;
        int i5;
        Alignment.Horizontal horizontal2;
        FlingBehavior flingBehavior2;
        int i6;
        int i7;
        Composer composer2;
        final Modifier modifier2;
        final boolean z4;
        final LazyListState lazyListState3;
        final PaddingValues paddingValues3;
        final boolean z5;
        final Arrangement.Vertical vertical3;
        final Alignment.Horizontal horizontal3;
        final FlingBehavior flingBehavior3;
        final OverscrollEffect overscrollEffect2;
        ScopeUpdateScope endRestartGroup;
        Arrangement.Vertical vertical4;
        FlingBehavior flingBehavior4;
        OverscrollEffect overscrollEffect3;
        Modifier modifier3;
        LazyListState lazyListState4;
        PaddingValues paddingValues4;
        Arrangement.Vertical vertical5;
        Alignment.Horizontal horizontal4;
        boolean z6;
        boolean z7;
        int i8;
        FlingBehavior flingBehavior5;
        int i9;
        int i10;
        int i11;
        int i12;
        Composer startRestartGroup = composer.startRestartGroup(53695811);
        ComposerKt.sourceInformation(startRestartGroup, "C(LazyColumn)N(modifier,state,contentPadding,reverseLayout,verticalArrangement,horizontalAlignment,flingBehavior,userScrollEnabled,overscrollEffect,content)400@20806L434:LazyDsl.kt#428nma");
        int i13 = i2 & 1;
        if (i13 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListState2 = lazyListState;
                if (startRestartGroup.changed(lazyListState2)) {
                    i12 = 32;
                    i3 |= i12;
                }
            } else {
                lazyListState2 = lazyListState;
            }
            i12 = 16;
            i3 |= i12;
        } else {
            lazyListState2 = lazyListState;
        }
        int i14 = i2 & 4;
        if (i14 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            paddingValues2 = paddingValues;
            i3 |= startRestartGroup.changed(paddingValues2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z3 = z;
                i3 |= startRestartGroup.changed(z3) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        vertical2 = vertical;
                        if (startRestartGroup.changed(vertical2)) {
                            i11 = 16384;
                            i3 |= i11;
                        }
                    } else {
                        vertical2 = vertical;
                    }
                    i11 = 8192;
                    i3 |= i11;
                } else {
                    vertical2 = vertical;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    horizontal2 = horizontal;
                    i3 |= startRestartGroup.changed(horizontal2) ? 131072 : 65536;
                    if ((1572864 & i) != 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            if (startRestartGroup.changed(flingBehavior2)) {
                                i10 = 1048576;
                                i3 |= i10;
                            }
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i10 = 524288;
                        i3 |= i10;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i6 = i2 & 128;
                    if (i6 == 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        i7 = i6;
                        i3 |= startRestartGroup.changed(z2) ? 8388608 : 4194304;
                        if ((i & 100663296) == 0) {
                            if ((i2 & 256) == 0 && startRestartGroup.changed(overscrollEffect)) {
                                i9 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                i3 |= i9;
                            }
                            i9 = 33554432;
                            i3 |= i9;
                        }
                        if ((i2 & 512) != 0) {
                            i3 |= 805306368;
                        } else if ((i & 805306368) == 0) {
                            i3 |= startRestartGroup.changedInstance(function1) ? 536870912 : 268435456;
                            if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                                startRestartGroup.startDefaults();
                                ComposerKt.sourceInformation(startRestartGroup, "389@20279L23,395@20633L15,397@20731L26");
                                if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i2 & 2) != 0) {
                                        i3 &= -113;
                                    }
                                    if ((i2 & 16) != 0) {
                                        i3 &= -57345;
                                    }
                                    if ((i2 & 64) != 0) {
                                        i3 &= -3670017;
                                    }
                                    if ((i2 & 256) != 0) {
                                        i3 &= -234881025;
                                    }
                                    modifier3 = modifier;
                                    z7 = z2;
                                    overscrollEffect3 = overscrollEffect;
                                    z6 = z3;
                                    vertical5 = vertical2;
                                    horizontal4 = horizontal2;
                                    flingBehavior5 = flingBehavior2;
                                    i8 = 53695811;
                                    lazyListState4 = lazyListState2;
                                    paddingValues4 = paddingValues2;
                                } else {
                                    Modifier.Companion companion = i13 != 0 ? Modifier.INSTANCE : modifier;
                                    if ((i2 & 2) != 0) {
                                        lazyListState2 = LazyListStateKt.rememberLazyListState(0, 0, startRestartGroup, 0, 3);
                                        i3 &= -113;
                                    }
                                    PaddingValues m871PaddingValues0680j_4 = i14 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues2;
                                    if (i4 != 0) {
                                        z3 = false;
                                    }
                                    if ((i2 & 16) != 0) {
                                        Arrangement arrangement = Arrangement.INSTANCE;
                                        vertical4 = !z3 ? arrangement.getTop() : arrangement.getBottom();
                                        i3 &= -57345;
                                    } else {
                                        vertical4 = vertical2;
                                    }
                                    Alignment.Horizontal start = i5 != 0 ? Alignment.INSTANCE.getStart() : horizontal2;
                                    if ((i2 & 64) != 0) {
                                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(startRestartGroup, 6);
                                        i3 &= -3670017;
                                    } else {
                                        flingBehavior4 = flingBehavior2;
                                    }
                                    boolean z8 = i7 == 0 ? z2 : true;
                                    if ((i2 & 256) != 0) {
                                        i3 &= -234881025;
                                        modifier3 = companion;
                                        overscrollEffect3 = OverscrollKt.rememberOverscrollEffect(startRestartGroup, 0);
                                    } else {
                                        overscrollEffect3 = overscrollEffect;
                                        modifier3 = companion;
                                    }
                                    lazyListState4 = lazyListState2;
                                    paddingValues4 = m871PaddingValues0680j_4;
                                    vertical5 = vertical4;
                                    horizontal4 = start;
                                    z6 = z3;
                                    z7 = z8;
                                    i8 = 53695811;
                                    flingBehavior5 = flingBehavior4;
                                }
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i8, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:399)");
                                }
                                int i15 = i3 >> 3;
                                composer2 = startRestartGroup;
                                LazyListKt.LazyList(modifier3, lazyListState4, paddingValues4, z6, true, flingBehavior5, z7, overscrollEffect3, 0, horizontal4, vertical5, null, null, function1, composer2, (i3 & 14) | 24576 | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (458752 & i15) | (3670016 & i15) | (i15 & 29360128) | ((i3 << 12) & 1879048192), ((i3 >> 12) & 14) | ((i3 >> 18) & 7168), 6400);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier2 = modifier3;
                                lazyListState3 = lazyListState4;
                                paddingValues3 = paddingValues4;
                                z5 = z6;
                                flingBehavior3 = flingBehavior5;
                                z4 = z7;
                                overscrollEffect2 = overscrollEffect3;
                                horizontal3 = horizontal4;
                                vertical3 = vertical5;
                            } else {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                modifier2 = modifier;
                                z4 = z2;
                                lazyListState3 = lazyListState2;
                                paddingValues3 = paddingValues2;
                                z5 = z3;
                                vertical3 = vertical2;
                                horizontal3 = horizontal2;
                                flingBehavior3 = flingBehavior2;
                                overscrollEffect2 = overscrollEffect;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit LazyColumn$lambda$1;
                                        LazyColumn$lambda$1 = LazyDslKt.LazyColumn$lambda$1(Modifier.this, lazyListState3, paddingValues3, z5, vertical3, horizontal3, flingBehavior3, z4, overscrollEffect2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                        return LazyColumn$lambda$1;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i7 = i6;
                    if ((i & 100663296) == 0) {
                    }
                    if ((i2 & 512) != 0) {
                    }
                    if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                horizontal2 = horizontal;
                if ((1572864 & i) != 0) {
                }
                i6 = i2 & 128;
                if (i6 == 0) {
                }
                i7 = i6;
                if ((i & 100663296) == 0) {
                }
                if ((i2 & 512) != 0) {
                }
                if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z3 = z;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            horizontal2 = horizontal;
            if ((1572864 & i) != 0) {
            }
            i6 = i2 & 128;
            if (i6 == 0) {
            }
            i7 = i6;
            if ((i & 100663296) == 0) {
            }
            if ((i2 & 512) != 0) {
            }
            if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z3 = z;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        horizontal2 = horizontal;
        if ((1572864 & i) != 0) {
        }
        i6 = i2 & 128;
        if (i6 == 0) {
        }
        i7 = i6;
        if ((i & 100663296) == 0) {
        }
        if ((i2 & 512) != 0) {
        }
        if (!startRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void LazyColumn(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, boolean z2, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListState2;
        PaddingValues paddingValues2;
        int i4;
        boolean z3;
        Arrangement.Vertical vertical2;
        int i5;
        Alignment.Horizontal horizontal2;
        FlingBehavior flingBehavior2;
        int i6;
        int i7;
        Composer composer2;
        final Modifier modifier2;
        final boolean z4;
        final LazyListState lazyListState3;
        final PaddingValues paddingValues3;
        final boolean z5;
        final Arrangement.Vertical vertical3;
        final Alignment.Horizontal horizontal3;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope endRestartGroup;
        Arrangement.Vertical vertical4;
        FlingBehavior flingBehavior4;
        boolean z6;
        LazyListState lazyListState4;
        PaddingValues paddingValues4;
        Arrangement.Vertical vertical5;
        Alignment.Horizontal horizontal4;
        boolean z7;
        FlingBehavior flingBehavior5;
        Modifier modifier3;
        int i8;
        int i9;
        int i10;
        int i11;
        Composer startRestartGroup = composer.startRestartGroup(-740714857);
        ComposerKt.sourceInformation(startRestartGroup, "C(LazyColumn)N(modifier,state,contentPadding,reverseLayout,verticalArrangement,horizontalAlignment,flingBehavior,userScrollEnabled,content)438@22230L26,429@21871L419:LazyDsl.kt#428nma");
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListState2 = lazyListState;
                if (startRestartGroup.changed(lazyListState2)) {
                    i11 = 32;
                    i3 |= i11;
                }
            } else {
                lazyListState2 = lazyListState;
            }
            i11 = 16;
            i3 |= i11;
        } else {
            lazyListState2 = lazyListState;
        }
        int i13 = i2 & 4;
        if (i13 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            paddingValues2 = paddingValues;
            i3 |= startRestartGroup.changed(paddingValues2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z3 = z;
                i3 |= startRestartGroup.changed(z3) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        vertical2 = vertical;
                        if (startRestartGroup.changed(vertical2)) {
                            i10 = 16384;
                            i3 |= i10;
                        }
                    } else {
                        vertical2 = vertical;
                    }
                    i10 = 8192;
                    i3 |= i10;
                } else {
                    vertical2 = vertical;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    horizontal2 = horizontal;
                    i3 |= startRestartGroup.changed(horizontal2) ? 131072 : 65536;
                    if ((1572864 & i) != 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            if (startRestartGroup.changed(flingBehavior2)) {
                                i9 = 1048576;
                                i3 |= i9;
                            }
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i9 = 524288;
                        i3 |= i9;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i6 = i2 & 128;
                    if (i6 == 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        i7 = i6;
                        i3 |= startRestartGroup.changed(z2) ? 8388608 : 4194304;
                        if ((i2 & 256) != 0) {
                            i3 |= 100663296;
                        } else if ((i & 100663296) == 0) {
                            i3 |= startRestartGroup.changedInstance(function1) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
                                startRestartGroup.startDefaults();
                                ComposerKt.sourceInformation(startRestartGroup, "419@21414L23,425@21768L15");
                                if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i2 & 2) != 0) {
                                        i3 &= -113;
                                    }
                                    if ((i2 & 16) != 0) {
                                        i3 &= -57345;
                                    }
                                    if ((i2 & 64) != 0) {
                                        i3 &= -3670017;
                                    }
                                    z6 = z2;
                                    lazyListState4 = lazyListState2;
                                    vertical5 = vertical2;
                                    horizontal4 = horizontal2;
                                    flingBehavior5 = flingBehavior2;
                                    i8 = -740714857;
                                    modifier3 = modifier;
                                    paddingValues4 = paddingValues2;
                                    z7 = z3;
                                } else {
                                    Modifier.Companion companion = i12 != 0 ? Modifier.INSTANCE : modifier;
                                    if ((i2 & 2) != 0) {
                                        lazyListState2 = LazyListStateKt.rememberLazyListState(0, 0, startRestartGroup, 0, 3);
                                        i3 &= -113;
                                    }
                                    PaddingValues m871PaddingValues0680j_4 = i13 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues2;
                                    if (i4 != 0) {
                                        z3 = false;
                                    }
                                    if ((i2 & 16) != 0) {
                                        Arrangement arrangement = Arrangement.INSTANCE;
                                        vertical4 = !z3 ? arrangement.getTop() : arrangement.getBottom();
                                        i3 &= -57345;
                                    } else {
                                        vertical4 = vertical2;
                                    }
                                    Alignment.Horizontal start = i5 != 0 ? Alignment.INSTANCE.getStart() : horizontal2;
                                    if ((i2 & 64) != 0) {
                                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(startRestartGroup, 6);
                                        i3 &= -3670017;
                                    } else {
                                        flingBehavior4 = flingBehavior2;
                                    }
                                    if (i7 != 0) {
                                        lazyListState4 = lazyListState2;
                                        paddingValues4 = m871PaddingValues0680j_4;
                                        vertical5 = vertical4;
                                        horizontal4 = start;
                                        z7 = z3;
                                        z6 = true;
                                    } else {
                                        z6 = z2;
                                        lazyListState4 = lazyListState2;
                                        paddingValues4 = m871PaddingValues0680j_4;
                                        vertical5 = vertical4;
                                        horizontal4 = start;
                                        z7 = z3;
                                    }
                                    flingBehavior5 = flingBehavior4;
                                    modifier3 = companion;
                                    i8 = -740714857;
                                }
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i8, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:428)");
                                }
                                composer2 = startRestartGroup;
                                LazyColumn(modifier3, lazyListState4, paddingValues4, z7, vertical5, horizontal4, flingBehavior5, z6, OverscrollKt.rememberOverscrollEffect(startRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier2 = modifier3;
                                lazyListState3 = lazyListState4;
                                paddingValues3 = paddingValues4;
                                z5 = z7;
                                vertical3 = vertical5;
                                horizontal3 = horizontal4;
                                flingBehavior3 = flingBehavior5;
                                z4 = z6;
                            } else {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                modifier2 = modifier;
                                z4 = z2;
                                lazyListState3 = lazyListState2;
                                paddingValues3 = paddingValues2;
                                z5 = z3;
                                vertical3 = vertical2;
                                horizontal3 = horizontal2;
                                flingBehavior3 = flingBehavior2;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$$ExternalSyntheticLambda3
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit LazyColumn$lambda$2;
                                        LazyColumn$lambda$2 = LazyDslKt.LazyColumn$lambda$2(Modifier.this, lazyListState3, paddingValues3, z5, vertical3, horizontal3, flingBehavior3, z4, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                        return LazyColumn$lambda$2;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i7 = i6;
                    if ((i2 & 256) != 0) {
                    }
                    if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                horizontal2 = horizontal;
                if ((1572864 & i) != 0) {
                }
                i6 = i2 & 128;
                if (i6 == 0) {
                }
                i7 = i6;
                if ((i2 & 256) != 0) {
                }
                if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z3 = z;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            horizontal2 = horizontal;
            if ((1572864 & i) != 0) {
            }
            i6 = i2 & 128;
            if (i6 == 0) {
            }
            i7 = i6;
            if ((i2 & 256) != 0) {
            }
            if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z3 = z;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        horizontal2 = horizontal;
        if ((1572864 & i) != 0) {
        }
        i6 = i2 & 128;
        if (i6 == 0) {
        }
        i7 = i6;
        if ((i2 & 256) != 0) {
        }
        if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void LazyColumn(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, FlingBehavior flingBehavior, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListState2;
        PaddingValues paddingValues2;
        int i4;
        boolean z2;
        Arrangement.Vertical vertical2;
        int i5;
        Alignment.Horizontal horizontal2;
        FlingBehavior flingBehavior2;
        Composer composer2;
        final Modifier modifier2;
        final LazyListState lazyListState3;
        final PaddingValues paddingValues3;
        final boolean z3;
        final Arrangement.Vertical vertical3;
        final Alignment.Horizontal horizontal3;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope endRestartGroup;
        PaddingValues paddingValues4;
        Alignment.Horizontal horizontal4;
        int i6;
        boolean z4;
        Modifier modifier3;
        int i7;
        FlingBehavior flingBehavior4;
        Arrangement.Vertical vertical4;
        LazyListState lazyListState4;
        int i8;
        int i9;
        int i10;
        Composer startRestartGroup = composer.startRestartGroup(-563353797);
        ComposerKt.sourceInformation(startRestartGroup, "C(LazyColumn)N(modifier,state,contentPadding,reverseLayout,verticalArrangement,horizontalAlignment,flingBehavior,content)456@22882L351:LazyDsl.kt#428nma");
        int i11 = i2 & 1;
        if (i11 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListState2 = lazyListState;
                if (startRestartGroup.changed(lazyListState2)) {
                    i10 = 32;
                    i3 |= i10;
                }
            } else {
                lazyListState2 = lazyListState;
            }
            i10 = 16;
            i3 |= i10;
        } else {
            lazyListState2 = lazyListState;
        }
        int i12 = i2 & 4;
        if (i12 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            paddingValues2 = paddingValues;
            i3 |= startRestartGroup.changed(paddingValues2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        vertical2 = vertical;
                        if (startRestartGroup.changed(vertical2)) {
                            i9 = 16384;
                            i3 |= i9;
                        }
                    } else {
                        vertical2 = vertical;
                    }
                    i9 = 8192;
                    i3 |= i9;
                } else {
                    vertical2 = vertical;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    horizontal2 = horizontal;
                    i3 |= startRestartGroup.changed(horizontal2) ? 131072 : 65536;
                    if ((1572864 & i) != 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            if (startRestartGroup.changed(flingBehavior2)) {
                                i8 = 1048576;
                                i3 |= i8;
                            }
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i8 = 524288;
                        i3 |= i8;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    if ((i2 & 128) == 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        i3 |= startRestartGroup.changedInstance(function1) ? 8388608 : 4194304;
                        if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
                            startRestartGroup.startDefaults();
                            ComposerKt.sourceInformation(startRestartGroup, "447@22464L23,453@22818L15");
                            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                startRestartGroup.skipToGroupEnd();
                                if ((i2 & 2) != 0) {
                                    i3 &= -113;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                paddingValues4 = paddingValues2;
                                horizontal4 = horizontal2;
                                i7 = 12582912;
                                i6 = -563353797;
                                z4 = z2;
                                flingBehavior4 = flingBehavior2;
                                modifier3 = modifier;
                            } else {
                                Modifier.Companion companion = i11 != 0 ? Modifier.INSTANCE : modifier;
                                if ((i2 & 2) != 0) {
                                    lazyListState2 = LazyListStateKt.rememberLazyListState(0, 0, startRestartGroup, 0, 3);
                                    i3 &= -113;
                                }
                                PaddingValues m871PaddingValues0680j_4 = i12 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues2;
                                if (i4 != 0) {
                                    z2 = false;
                                }
                                if ((i2 & 16) != 0) {
                                    Arrangement arrangement = Arrangement.INSTANCE;
                                    i3 &= -57345;
                                    vertical2 = !z2 ? arrangement.getTop() : arrangement.getBottom();
                                }
                                if (i5 != 0) {
                                    horizontal2 = Alignment.INSTANCE.getStart();
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                    paddingValues4 = m871PaddingValues0680j_4;
                                    vertical4 = vertical2;
                                    horizontal4 = horizontal2;
                                    lazyListState4 = lazyListState2;
                                    z4 = z2;
                                    modifier3 = companion;
                                    i7 = 12582912;
                                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(startRestartGroup, 6);
                                    i6 = -563353797;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(i6, i3, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:455)");
                                    }
                                    composer2 = startRestartGroup;
                                    LazyColumn(modifier3, lazyListState4, paddingValues4, z4, vertical4, horizontal4, flingBehavior4, true, null, function1, composer2, i7 | (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier2 = modifier3;
                                    lazyListState3 = lazyListState4;
                                    paddingValues3 = paddingValues4;
                                    z3 = z4;
                                    vertical3 = vertical4;
                                    horizontal3 = horizontal4;
                                    flingBehavior3 = flingBehavior4;
                                } else {
                                    paddingValues4 = m871PaddingValues0680j_4;
                                    horizontal4 = horizontal2;
                                    i6 = -563353797;
                                    z4 = z2;
                                    modifier3 = companion;
                                    i7 = 12582912;
                                    flingBehavior4 = flingBehavior2;
                                }
                            }
                            vertical4 = vertical2;
                            lazyListState4 = lazyListState2;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer2 = startRestartGroup;
                            LazyColumn(modifier3, lazyListState4, paddingValues4, z4, vertical4, horizontal4, flingBehavior4, true, null, function1, composer2, i7 | (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            modifier2 = modifier3;
                            lazyListState3 = lazyListState4;
                            paddingValues3 = paddingValues4;
                            z3 = z4;
                            vertical3 = vertical4;
                            horizontal3 = horizontal4;
                            flingBehavior3 = flingBehavior4;
                        } else {
                            composer2 = startRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            lazyListState3 = lazyListState2;
                            paddingValues3 = paddingValues2;
                            z3 = z2;
                            vertical3 = vertical2;
                            horizontal3 = horizontal2;
                            flingBehavior3 = flingBehavior2;
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj, Object obj2) {
                                    Unit LazyColumn$lambda$3;
                                    LazyColumn$lambda$3 = LazyDslKt.LazyColumn$lambda$3(Modifier.this, lazyListState3, paddingValues3, z3, vertical3, horizontal3, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    return LazyColumn$lambda$3;
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                horizontal2 = horizontal;
                if ((1572864 & i) != 0) {
                }
                if ((i2 & 128) == 0) {
                }
                if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z2 = z;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            horizontal2 = horizontal;
            if ((1572864 & i) != 0) {
            }
            if ((i2 & 128) == 0) {
            }
            if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        horizontal2 = horizontal;
        if ((1572864 & i) != 0) {
        }
        if ((i2 & 128) == 0) {
        }
        if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void LazyRow(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, boolean z2, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListState2;
        PaddingValues paddingValues2;
        int i4;
        boolean z3;
        Arrangement.Horizontal horizontal2;
        int i5;
        Alignment.Vertical vertical2;
        FlingBehavior flingBehavior2;
        int i6;
        int i7;
        Composer composer2;
        final Modifier modifier2;
        final boolean z4;
        final LazyListState lazyListState3;
        final PaddingValues paddingValues3;
        final boolean z5;
        final Arrangement.Horizontal horizontal3;
        final Alignment.Vertical vertical3;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope endRestartGroup;
        Arrangement.Horizontal horizontal4;
        FlingBehavior flingBehavior4;
        boolean z6;
        LazyListState lazyListState4;
        PaddingValues paddingValues4;
        Arrangement.Horizontal horizontal5;
        Alignment.Vertical vertical4;
        boolean z7;
        FlingBehavior flingBehavior5;
        Modifier modifier3;
        int i8;
        int i9;
        int i10;
        int i11;
        Composer startRestartGroup = composer.startRestartGroup(-1724297413);
        ComposerKt.sourceInformation(startRestartGroup, "C(LazyRow)N(modifier,state,contentPadding,reverseLayout,horizontalArrangement,verticalAlignment,flingBehavior,userScrollEnabled,content)492@24214L26,483@23858L416:LazyDsl.kt#428nma");
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListState2 = lazyListState;
                if (startRestartGroup.changed(lazyListState2)) {
                    i11 = 32;
                    i3 |= i11;
                }
            } else {
                lazyListState2 = lazyListState;
            }
            i11 = 16;
            i3 |= i11;
        } else {
            lazyListState2 = lazyListState;
        }
        int i13 = i2 & 4;
        if (i13 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            paddingValues2 = paddingValues;
            i3 |= startRestartGroup.changed(paddingValues2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z3 = z;
                i3 |= startRestartGroup.changed(z3) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        horizontal2 = horizontal;
                        if (startRestartGroup.changed(horizontal2)) {
                            i10 = 16384;
                            i3 |= i10;
                        }
                    } else {
                        horizontal2 = horizontal;
                    }
                    i10 = 8192;
                    i3 |= i10;
                } else {
                    horizontal2 = horizontal;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    vertical2 = vertical;
                    i3 |= startRestartGroup.changed(vertical2) ? 131072 : 65536;
                    if ((1572864 & i) != 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            if (startRestartGroup.changed(flingBehavior2)) {
                                i9 = 1048576;
                                i3 |= i9;
                            }
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i9 = 524288;
                        i3 |= i9;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    i6 = i2 & 128;
                    if (i6 == 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        i7 = i6;
                        i3 |= startRestartGroup.changed(z2) ? 8388608 : 4194304;
                        if ((i2 & 256) != 0) {
                            i3 |= 100663296;
                        } else if ((i & 100663296) == 0) {
                            i3 |= startRestartGroup.changedInstance(function1) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
                                startRestartGroup.startDefaults();
                                ComposerKt.sourceInformation(startRestartGroup, "473@23404L23,479@23755L15");
                                if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i2 & 2) != 0) {
                                        i3 &= -113;
                                    }
                                    if ((i2 & 16) != 0) {
                                        i3 &= -57345;
                                    }
                                    if ((i2 & 64) != 0) {
                                        i3 &= -3670017;
                                    }
                                    z6 = z2;
                                    lazyListState4 = lazyListState2;
                                    horizontal5 = horizontal2;
                                    vertical4 = vertical2;
                                    flingBehavior5 = flingBehavior2;
                                    i8 = -1724297413;
                                    modifier3 = modifier;
                                    paddingValues4 = paddingValues2;
                                    z7 = z3;
                                } else {
                                    Modifier.Companion companion = i12 != 0 ? Modifier.INSTANCE : modifier;
                                    if ((i2 & 2) != 0) {
                                        lazyListState2 = LazyListStateKt.rememberLazyListState(0, 0, startRestartGroup, 0, 3);
                                        i3 &= -113;
                                    }
                                    PaddingValues m871PaddingValues0680j_4 = i13 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues2;
                                    if (i4 != 0) {
                                        z3 = false;
                                    }
                                    if ((i2 & 16) != 0) {
                                        Arrangement arrangement = Arrangement.INSTANCE;
                                        horizontal4 = !z3 ? arrangement.getStart() : arrangement.getEnd();
                                        i3 &= -57345;
                                    } else {
                                        horizontal4 = horizontal2;
                                    }
                                    Alignment.Vertical top = i5 != 0 ? Alignment.INSTANCE.getTop() : vertical2;
                                    if ((i2 & 64) != 0) {
                                        flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(startRestartGroup, 6);
                                        i3 &= -3670017;
                                    } else {
                                        flingBehavior4 = flingBehavior2;
                                    }
                                    if (i7 != 0) {
                                        lazyListState4 = lazyListState2;
                                        paddingValues4 = m871PaddingValues0680j_4;
                                        horizontal5 = horizontal4;
                                        vertical4 = top;
                                        z7 = z3;
                                        z6 = true;
                                    } else {
                                        z6 = z2;
                                        lazyListState4 = lazyListState2;
                                        paddingValues4 = m871PaddingValues0680j_4;
                                        horizontal5 = horizontal4;
                                        vertical4 = top;
                                        z7 = z3;
                                    }
                                    flingBehavior5 = flingBehavior4;
                                    modifier3 = companion;
                                    i8 = -1724297413;
                                }
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i8, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:482)");
                                }
                                composer2 = startRestartGroup;
                                LazyRow(modifier3, lazyListState4, paddingValues4, z7, horizontal5, vertical4, flingBehavior5, z6, OverscrollKt.rememberOverscrollEffect(startRestartGroup, 0), function1, composer2, (33554430 & i3) | ((i3 << 3) & 1879048192), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier2 = modifier3;
                                lazyListState3 = lazyListState4;
                                paddingValues3 = paddingValues4;
                                z5 = z7;
                                horizontal3 = horizontal5;
                                vertical3 = vertical4;
                                flingBehavior3 = flingBehavior5;
                                z4 = z6;
                            } else {
                                composer2 = startRestartGroup;
                                composer2.skipToGroupEnd();
                                modifier2 = modifier;
                                z4 = z2;
                                lazyListState3 = lazyListState2;
                                paddingValues3 = paddingValues2;
                                z5 = z3;
                                horizontal3 = horizontal2;
                                vertical3 = vertical2;
                                flingBehavior3 = flingBehavior2;
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$$ExternalSyntheticLambda4
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        Unit LazyRow$lambda$4;
                                        LazyRow$lambda$4 = LazyDslKt.LazyRow$lambda$4(Modifier.this, lazyListState3, paddingValues3, z5, horizontal3, vertical3, flingBehavior3, z4, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                        return LazyRow$lambda$4;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    i7 = i6;
                    if ((i2 & 256) != 0) {
                    }
                    if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                vertical2 = vertical;
                if ((1572864 & i) != 0) {
                }
                i6 = i2 & 128;
                if (i6 == 0) {
                }
                i7 = i6;
                if ((i2 & 256) != 0) {
                }
                if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            z3 = z;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            vertical2 = vertical;
            if ((1572864 & i) != 0) {
            }
            i6 = i2 & 128;
            if (i6 == 0) {
            }
            i7 = i6;
            if ((i2 & 256) != 0) {
            }
            if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z3 = z;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        vertical2 = vertical;
        if ((1572864 & i) != 0) {
        }
        i6 = i2 & 128;
        if (i6 == 0) {
        }
        i7 = i6;
        if ((i2 & 256) != 0) {
        }
        if (!startRestartGroup.shouldExecute((i3 & 38347923) == 38347922, i3 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ void LazyRow(Modifier modifier, LazyListState lazyListState, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, FlingBehavior flingBehavior, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        LazyListState lazyListState2;
        PaddingValues paddingValues2;
        int i4;
        boolean z2;
        Arrangement.Horizontal horizontal2;
        int i5;
        Alignment.Vertical vertical2;
        FlingBehavior flingBehavior2;
        Composer composer2;
        final Modifier modifier2;
        final LazyListState lazyListState3;
        final PaddingValues paddingValues3;
        final boolean z3;
        final Arrangement.Horizontal horizontal3;
        final Alignment.Vertical vertical3;
        final FlingBehavior flingBehavior3;
        ScopeUpdateScope endRestartGroup;
        PaddingValues paddingValues4;
        Alignment.Vertical vertical4;
        int i6;
        boolean z4;
        Modifier modifier3;
        int i7;
        FlingBehavior flingBehavior4;
        Arrangement.Horizontal horizontal4;
        LazyListState lazyListState4;
        int i8;
        int i9;
        int i10;
        Composer startRestartGroup = composer.startRestartGroup(407929823);
        ComposerKt.sourceInformation(startRestartGroup, "C(LazyRow)N(modifier,state,contentPadding,reverseLayout,horizontalArrangement,verticalAlignment,flingBehavior,content)510@24860L348:LazyDsl.kt#428nma");
        int i11 = i2 & 1;
        if (i11 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (startRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                lazyListState2 = lazyListState;
                if (startRestartGroup.changed(lazyListState2)) {
                    i10 = 32;
                    i3 |= i10;
                }
            } else {
                lazyListState2 = lazyListState;
            }
            i10 = 16;
            i3 |= i10;
        } else {
            lazyListState2 = lazyListState;
        }
        int i12 = i2 & 4;
        if (i12 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            paddingValues2 = paddingValues;
            i3 |= startRestartGroup.changed(paddingValues2) ? 256 : 128;
            i4 = i2 & 8;
            if (i4 == 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                z2 = z;
                i3 |= startRestartGroup.changed(z2) ? 2048 : 1024;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        horizontal2 = horizontal;
                        if (startRestartGroup.changed(horizontal2)) {
                            i9 = 16384;
                            i3 |= i9;
                        }
                    } else {
                        horizontal2 = horizontal;
                    }
                    i9 = 8192;
                    i3 |= i9;
                } else {
                    horizontal2 = horizontal;
                }
                i5 = i2 & 32;
                if (i5 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((196608 & i) == 0) {
                    vertical2 = vertical;
                    i3 |= startRestartGroup.changed(vertical2) ? 131072 : 65536;
                    if ((1572864 & i) != 0) {
                        if ((i2 & 64) == 0) {
                            flingBehavior2 = flingBehavior;
                            if (startRestartGroup.changed(flingBehavior2)) {
                                i8 = 1048576;
                                i3 |= i8;
                            }
                        } else {
                            flingBehavior2 = flingBehavior;
                        }
                        i8 = 524288;
                        i3 |= i8;
                    } else {
                        flingBehavior2 = flingBehavior;
                    }
                    if ((i2 & 128) == 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        i3 |= startRestartGroup.changedInstance(function1) ? 8388608 : 4194304;
                        if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
                            startRestartGroup.startDefaults();
                            ComposerKt.sourceInformation(startRestartGroup, "501@24445L23,507@24796L15");
                            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                                startRestartGroup.skipToGroupEnd();
                                if ((i2 & 2) != 0) {
                                    i3 &= -113;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -57345;
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                }
                                paddingValues4 = paddingValues2;
                                vertical4 = vertical2;
                                i7 = 12582912;
                                i6 = 407929823;
                                z4 = z2;
                                flingBehavior4 = flingBehavior2;
                                modifier3 = modifier;
                            } else {
                                Modifier.Companion companion = i11 != 0 ? Modifier.INSTANCE : modifier;
                                if ((i2 & 2) != 0) {
                                    lazyListState2 = LazyListStateKt.rememberLazyListState(0, 0, startRestartGroup, 0, 3);
                                    i3 &= -113;
                                }
                                PaddingValues m871PaddingValues0680j_4 = i12 != 0 ? PaddingKt.m871PaddingValues0680j_4(Dp.m7399constructorimpl(0)) : paddingValues2;
                                if (i4 != 0) {
                                    z2 = false;
                                }
                                if ((i2 & 16) != 0) {
                                    Arrangement arrangement = Arrangement.INSTANCE;
                                    i3 &= -57345;
                                    horizontal2 = !z2 ? arrangement.getStart() : arrangement.getEnd();
                                }
                                if (i5 != 0) {
                                    vertical2 = Alignment.INSTANCE.getTop();
                                }
                                if ((i2 & 64) != 0) {
                                    i3 &= -3670017;
                                    paddingValues4 = m871PaddingValues0680j_4;
                                    horizontal4 = horizontal2;
                                    vertical4 = vertical2;
                                    lazyListState4 = lazyListState2;
                                    z4 = z2;
                                    modifier3 = companion;
                                    i7 = 12582912;
                                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior(startRestartGroup, 6);
                                    i6 = 407929823;
                                    startRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(i6, i3, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:509)");
                                    }
                                    composer2 = startRestartGroup;
                                    LazyRow(modifier3, lazyListState4, paddingValues4, z4, horizontal4, vertical4, flingBehavior4, true, null, function1, composer2, i7 | (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier2 = modifier3;
                                    lazyListState3 = lazyListState4;
                                    paddingValues3 = paddingValues4;
                                    z3 = z4;
                                    horizontal3 = horizontal4;
                                    vertical3 = vertical4;
                                    flingBehavior3 = flingBehavior4;
                                } else {
                                    paddingValues4 = m871PaddingValues0680j_4;
                                    vertical4 = vertical2;
                                    i6 = 407929823;
                                    z4 = z2;
                                    modifier3 = companion;
                                    i7 = 12582912;
                                    flingBehavior4 = flingBehavior2;
                                }
                            }
                            horizontal4 = horizontal2;
                            lazyListState4 = lazyListState2;
                            startRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            composer2 = startRestartGroup;
                            LazyRow(modifier3, lazyListState4, paddingValues4, z4, horizontal4, vertical4, flingBehavior4, true, null, function1, composer2, i7 | (i3 & 14) | (i3 & 112) | (i3 & 896) | (i3 & 7168) | (57344 & i3) | (458752 & i3) | (3670016 & i3) | ((i3 << 6) & 1879048192), 256);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            modifier2 = modifier3;
                            lazyListState3 = lazyListState4;
                            paddingValues3 = paddingValues4;
                            z3 = z4;
                            horizontal3 = horizontal4;
                            vertical3 = vertical4;
                            flingBehavior3 = flingBehavior4;
                        } else {
                            composer2 = startRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            lazyListState3 = lazyListState2;
                            paddingValues3 = paddingValues2;
                            z3 = z2;
                            horizontal3 = horizontal2;
                            vertical3 = vertical2;
                            flingBehavior3 = flingBehavior2;
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup != null) {
                            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj, Object obj2) {
                                    Unit LazyRow$lambda$5;
                                    LazyRow$lambda$5 = LazyDslKt.LazyRow$lambda$5(Modifier.this, lazyListState3, paddingValues3, z3, horizontal3, vertical3, flingBehavior3, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    return LazyRow$lambda$5;
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup != null) {
                    }
                }
                vertical2 = vertical;
                if ((1572864 & i) != 0) {
                }
                if ((i2 & 128) == 0) {
                }
                if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup != null) {
                }
            }
            z2 = z;
            if ((i & 24576) == 0) {
            }
            i5 = i2 & 32;
            if (i5 != 0) {
            }
            vertical2 = vertical;
            if ((1572864 & i) != 0) {
            }
            if ((i2 & 128) == 0) {
            }
            if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup != null) {
            }
        }
        paddingValues2 = paddingValues;
        i4 = i2 & 8;
        if (i4 == 0) {
        }
        z2 = z;
        if ((i & 24576) == 0) {
        }
        i5 = i2 & 32;
        if (i5 != 0) {
        }
        vertical2 = vertical;
        if ((1572864 & i) != 0) {
        }
        if ((i2 & 128) == 0) {
        }
        if (startRestartGroup.shouldExecute((i3 & 4793491) != 4793490, i3 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
        }
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, List list, Function1 function1, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        lazyListScope.items(list.size(), function1 != null ? new LazyDslKt$items$2(function1, list) : null, new LazyDslKt$items$3(LazyDslKt$items$1.INSTANCE, list), ComposableLambdaKt.composableLambdaInstance(802480018, true, new LazyDslKt$items$4(function4, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, List list, Function2 function2, Function5 function5, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        lazyListScope.items(list.size(), function2 != null ? new LazyDslKt$itemsIndexed$2(function2, list) : null, new LazyDslKt$itemsIndexed$$inlined$itemsIndexed$default$1(list), ComposableLambdaKt.composableLambdaInstance(2039820996, true, new LazyDslKt$itemsIndexed$4(function5, list)));
    }

    public static /* synthetic */ void items$default(LazyListScope lazyListScope, Object[] objArr, Function1 function1, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        lazyListScope.items(objArr.length, function1 != null ? new LazyDslKt$items$6(function1, objArr) : null, new LazyDslKt$items$7(LazyDslKt$items$5.INSTANCE, objArr), ComposableLambdaKt.composableLambdaInstance(-1781742563, true, new LazyDslKt$items$8(function4, objArr)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope lazyListScope, Object[] objArr, Function2 function2, Function5 function5, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        lazyListScope.items(objArr.length, function2 != null ? new LazyDslKt$itemsIndexed$6(function2, objArr) : null, new LazyDslKt$itemsIndexed$$inlined$itemsIndexed$default$2(objArr), ComposableLambdaKt.composableLambdaInstance(1763000017, true, new LazyDslKt$itemsIndexed$8(function5, objArr)));
    }
}
