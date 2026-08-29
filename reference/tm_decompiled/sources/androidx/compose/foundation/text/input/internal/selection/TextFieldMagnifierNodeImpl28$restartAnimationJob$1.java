package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.selection.SelectionMagnifierKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: AndroidTextFieldMagnifier.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1", f = "AndroidTextFieldMagnifier.android.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class TextFieldMagnifierNodeImpl28$restartAnimationJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TextFieldMagnifierNodeImpl28 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldMagnifierNodeImpl28$restartAnimationJob$1(TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28, Continuation<? super TextFieldMagnifierNodeImpl28$restartAnimationJob$1> continuation) {
        super(2, continuation);
        this.this$0 = textFieldMagnifierNodeImpl28;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldMagnifierNodeImpl28$restartAnimationJob$1 textFieldMagnifierNodeImpl28$restartAnimationJob$1 = new TextFieldMagnifierNodeImpl28$restartAnimationJob$1(this.this$0, continuation);
        textFieldMagnifierNodeImpl28$restartAnimationJob$1.L$0 = obj;
        return textFieldMagnifierNodeImpl28$restartAnimationJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldMagnifierNodeImpl28$restartAnimationJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            final TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28 = this.this$0;
            Flow snapshotFlow = SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Offset invokeSuspend$lambda$0;
                    invokeSuspend$lambda$0 = TextFieldMagnifierNodeImpl28$restartAnimationJob$1.invokeSuspend$lambda$0(TextFieldMagnifierNodeImpl28.this);
                    return invokeSuspend$lambda$0;
                }
            });
            final TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl282 = this.this$0;
            this.label = 1;
            if (snapshotFlow.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1.2
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return m1669emit3MmeM6k(((Offset) obj2).m4353unboximpl(), continuation);
                }

                /* renamed from: emit-3MmeM6k, reason: not valid java name */
                public final Object m1669emit3MmeM6k(long j, Continuation<? super Unit> continuation) {
                    Animatable animatable;
                    Animatable animatable2;
                    Animatable animatable3;
                    animatable = TextFieldMagnifierNodeImpl28.this.animatable;
                    if ((((Offset) animatable.getValue()).m4353unboximpl() & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats && (j & 9223372034707292159L) != InlineClassHelperKt.UnspecifiedPackedFloats) {
                        animatable3 = TextFieldMagnifierNodeImpl28.this.animatable;
                        if (Float.intBitsToFloat((int) (((Offset) animatable3.getValue()).m4353unboximpl() & 4294967295L)) != Float.intBitsToFloat((int) (4294967295L & j))) {
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(TextFieldMagnifierNodeImpl28.this, j, null), 3, null);
                            return Unit.INSTANCE;
                        }
                    }
                    animatable2 = TextFieldMagnifierNodeImpl28.this.animatable;
                    Object snapTo = animatable2.snapTo(Offset.m4332boximpl(j), continuation);
                    return snapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? snapTo : Unit.INSTANCE;
                }

                /* compiled from: AndroidTextFieldMagnifier.android.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1$2$1", f = "AndroidTextFieldMagnifier.android.kt", i = {}, l = {160}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNodeImpl28$restartAnimationJob$1$2$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ long $targetValue;
                    int label;
                    final /* synthetic */ TextFieldMagnifierNodeImpl28 this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28, long j, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.this$0 = textFieldMagnifierNodeImpl28;
                        this.$targetValue = j;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.this$0, this.$targetValue, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Animatable animatable;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            animatable = this.this$0.animatable;
                            this.label = 1;
                            if (Animatable.animateTo$default(animatable, Offset.m4332boximpl(this.$targetValue), SelectionMagnifierKt.getMagnifierSpringSpec(), null, null, this, 12, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset invokeSuspend$lambda$0(TextFieldMagnifierNodeImpl28 textFieldMagnifierNodeImpl28) {
        boolean z;
        TransformedTextFieldState transformedTextFieldState;
        TextFieldSelectionState textFieldSelectionState;
        TextLayoutState textLayoutState;
        long m1667getMagnifierSizeYbymL2g;
        TextFieldSelectionState textFieldSelectionState2;
        z = textFieldMagnifierNodeImpl28.visible;
        if (!z) {
            textFieldSelectionState2 = textFieldMagnifierNodeImpl28.textFieldSelectionState;
            if (textFieldSelectionState2.getDirectDragGestureInitiator() != TextFieldSelectionState.InputType.Touch) {
                return Offset.m4332boximpl(Offset.INSTANCE.m4358getUnspecifiedF1C5BW0());
            }
        }
        transformedTextFieldState = textFieldMagnifierNodeImpl28.textFieldState;
        textFieldSelectionState = textFieldMagnifierNodeImpl28.textFieldSelectionState;
        textLayoutState = textFieldMagnifierNodeImpl28.textLayoutState;
        m1667getMagnifierSizeYbymL2g = textFieldMagnifierNodeImpl28.m1667getMagnifierSizeYbymL2g();
        return Offset.m4332boximpl(TextFieldMagnifierKt.m1665calculateSelectionMagnifierCenterAndroidhUlJWOE(transformedTextFieldState, textFieldSelectionState, textLayoutState, m1667getMagnifierSizeYbymL2g));
    }
}
