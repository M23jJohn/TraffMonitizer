package androidx.compose.ui.tooling.preview.datasource;

import androidx.compose.ui.tooling.preview.PreviewParameterProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: LoremIpsum.android.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\t\b\u0016¢\u0006\u0004\b\u0005\u0010\u0007J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/compose/ui/tooling/preview/datasource/LoremIpsum;", "Landroidx/compose/ui/tooling/preview/PreviewParameterProvider;", "", "words", "", "<init>", "(I)V", "()V", "values", "Lkotlin/sequences/Sequence;", "getValues", "()Lkotlin/sequences/Sequence;", "generateLoremIpsum", "ui-tooling-preview"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public class LoremIpsum implements PreviewParameterProvider<String> {
    public static final int $stable = 0;
    private final int words;

    @Override // androidx.compose.ui.tooling.preview.PreviewParameterProvider
    public /* synthetic */ int getCount() {
        return PreviewParameterProvider.CC.$default$getCount(this);
    }

    public LoremIpsum(int i) {
        this.words = i;
    }

    public LoremIpsum() {
        this(500);
    }

    @Override // androidx.compose.ui.tooling.preview.PreviewParameterProvider
    public Sequence<String> getValues() {
        return SequencesKt.sequenceOf((Object[]) new String[]{generateLoremIpsum(this.words)});
    }

    private final String generateLoremIpsum(int words) {
        List list;
        final Ref.IntRef intRef = new Ref.IntRef();
        list = LoremIpsum_androidKt.LOREM_IPSUM_SOURCE;
        final int size = list.size();
        return SequencesKt.joinToString$default(SequencesKt.take(SequencesKt.generateSequence(new Function0() { // from class: androidx.compose.ui.tooling.preview.datasource.LoremIpsum$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String generateLoremIpsum$lambda$0;
                generateLoremIpsum$lambda$0 = LoremIpsum.generateLoremIpsum$lambda$0(Ref.IntRef.this, size);
                return generateLoremIpsum$lambda$0;
            }
        }), words), " ", null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String generateLoremIpsum$lambda$0(Ref.IntRef intRef, int i) {
        List list;
        list = LoremIpsum_androidKt.LOREM_IPSUM_SOURCE;
        int i2 = intRef.element;
        intRef.element = i2 + 1;
        return (String) list.get(i2 % i);
    }
}
