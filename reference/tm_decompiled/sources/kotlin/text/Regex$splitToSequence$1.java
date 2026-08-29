package kotlin.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: Regex.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlin.text.Regex$splitToSequence$1", f = "Regex.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {280, 288, 292}, m = "invokeSuspend", n = {"$this$sequence", "matcher", "$this$sequence", "matcher", "nextStart", "splitCount", "$this$sequence", "matcher", "nextStart", "splitCount"}, nl = {281, 289, 293}, s = {"L$0", "L$1", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"}, v = 2)
/* loaded from: classes3.dex */
final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation<? super Regex$splitToSequence$1> continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00b6, code lost:
    
        if (r0.yield(r4.subSequence(r11, r4.length()).toString(), r10) != r1) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d7, code lost:
    
        if (r0.yield(r10.$input.toString(), r10) == r1) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0079  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0079 -> B:13:0x007a). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Pattern pattern;
        int i;
        Matcher matcher;
        int i2;
        SequenceScope sequenceScope = (SequenceScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            pattern = this.this$0.nativePattern;
            Matcher matcher2 = pattern.matcher(this.$input);
            if (this.$limit != 1 && matcher2.find()) {
                i = 0;
                matcher = matcher2;
                i2 = 0;
                this.L$0 = sequenceScope;
                this.L$1 = matcher;
                this.I$0 = i;
                this.I$1 = i2;
                this.label = 2;
                if (sequenceScope.yield(this.$input.subSequence(i, matcher.start()).toString(), this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(sequenceScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(matcher2);
            this.label = 1;
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            if (i3 != 2) {
                if (i3 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            int i4 = this.I$1;
            matcher = (Matcher) this.L$1;
            ResultKt.throwOnFailure(obj);
            int end = matcher.end();
            int i5 = i4 + 1;
            if (i5 == this.$limit - 1 && matcher.find()) {
                i = end;
                i2 = i5;
                this.L$0 = sequenceScope;
                this.L$1 = matcher;
                this.I$0 = i;
                this.I$1 = i2;
                this.label = 2;
                if (sequenceScope.yield(this.$input.subSequence(i, matcher.start()).toString(), this) != coroutine_suspended) {
                    i4 = i2;
                    int end2 = matcher.end();
                    int i52 = i4 + 1;
                    if (i52 == this.$limit - 1) {
                    }
                    CharSequence charSequence = this.$input;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(sequenceScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(matcher);
                    this.I$0 = end2;
                    this.I$1 = i52;
                    this.label = 3;
                }
                return coroutine_suspended;
            }
            CharSequence charSequence2 = this.$input;
            this.L$0 = SpillingKt.nullOutSpilledVariable(sequenceScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(matcher);
            this.I$0 = end2;
            this.I$1 = i52;
            this.label = 3;
        }
        return Unit.INSTANCE;
    }
}
