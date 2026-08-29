package kotlin.io.path;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: PathTreeWalk.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/nio/file/Path;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlin.io.path.PathTreeWalk$bfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {191, 197}, m = "invokeSuspend", n = {"$this$iterator", "queue", "entriesReader", "pathNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded", "$this$iterator", "queue", "entriesReader", "pathNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded"}, nl = {193, 199}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0"}, v = 2)
/* loaded from: classes3.dex */
final class PathTreeWalk$bfsIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    final /* synthetic */ PathTreeWalk this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PathTreeWalk$bfsIterator$1(PathTreeWalk pathTreeWalk, Continuation<? super PathTreeWalk$bfsIterator$1> continuation) {
        super(2, continuation);
        this.this$0 = pathTreeWalk;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PathTreeWalk$bfsIterator$1 pathTreeWalk$bfsIterator$1 = new PathTreeWalk$bfsIterator$1(this.this$0, continuation);
        pathTreeWalk$bfsIterator$1.L$0 = obj;
        return pathTreeWalk$bfsIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PathTreeWalk$bfsIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0099, code lost:
    
        r2 = r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00a2  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        DirectoryEntriesReader directoryEntriesReader;
        boolean followLinks;
        Path path;
        Path path2;
        Object keyOf;
        ArrayDeque arrayDeque;
        Path path3;
        DirectoryEntriesReader directoryEntriesReader2;
        PathTreeWalk pathTreeWalk;
        PathNode pathNode;
        boolean isDirectory;
        ArrayDeque arrayDeque2;
        DirectoryEntriesReader directoryEntriesReader3;
        Path path4;
        boolean isDirectory2;
        boolean createsCycle;
        LinkOption linkOption;
        boolean exists;
        SequenceScope sequenceScope = (SequenceScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayDeque arrayDeque3 = new ArrayDeque();
            followLinks = this.this$0.getFollowLinks();
            directoryEntriesReader = new DirectoryEntriesReader(followLinks);
            path = this.this$0.start;
            path2 = this.this$0.start;
            keyOf = PathTreeWalkKt.keyOf(path2, this.this$0.getLinkOptions());
            arrayDeque3.addLast(new PathNode(path, keyOf, null));
            arrayDeque = arrayDeque3;
        } else if (i == 1) {
            path4 = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9314m(this.L$8);
            directoryEntriesReader3 = (DirectoryEntriesReader) this.L$7;
            pathNode = (PathNode) this.L$6;
            pathTreeWalk = (PathTreeWalk) this.L$4;
            directoryEntriesReader2 = (DirectoryEntriesReader) this.L$2;
            arrayDeque2 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(obj);
            path3 = path4;
            directoryEntriesReader = directoryEntriesReader3;
            arrayDeque = arrayDeque2;
            LinkOption[] linkOptions = pathTreeWalk.getLinkOptions();
            LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
            isDirectory = Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
            if (isDirectory) {
                arrayDeque.addAll(directoryEntriesReader.readEntries(pathNode));
            }
            directoryEntriesReader = directoryEntriesReader2;
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9314m(this.L$8);
            directoryEntriesReader = (DirectoryEntriesReader) this.L$2;
            arrayDeque = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        while (!arrayDeque.isEmpty()) {
            pathNode = (PathNode) arrayDeque.removeFirst();
            pathTreeWalk = this.this$0;
            path3 = pathNode.getPath();
            if (pathNode.getParent() != null) {
                PathsKt.checkFileName(path3);
            }
            LinkOption[] linkOptions2 = pathTreeWalk.getLinkOptions();
            LinkOption[] linkOptionArr2 = (LinkOption[]) Arrays.copyOf(linkOptions2, linkOptions2.length);
            isDirectory2 = Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length));
            if (isDirectory2) {
                createsCycle = PathTreeWalkKt.createsCycle(pathNode);
                if (!createsCycle) {
                    if (!pathTreeWalk.getIncludeDirectories()) {
                        directoryEntriesReader2 = directoryEntriesReader;
                        LinkOption[] linkOptions3 = pathTreeWalk.getLinkOptions();
                        LinkOption[] linkOptionArr3 = (LinkOption[]) Arrays.copyOf(linkOptions3, linkOptions3.length);
                        isDirectory = Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr3, linkOptionArr3.length));
                        if (isDirectory) {
                        }
                        directoryEntriesReader = directoryEntriesReader2;
                        while (!arrayDeque.isEmpty()) {
                        }
                    } else {
                        this.L$0 = sequenceScope;
                        this.L$1 = arrayDeque;
                        this.L$2 = directoryEntriesReader;
                        this.L$3 = SpillingKt.nullOutSpilledVariable(pathNode);
                        this.L$4 = pathTreeWalk;
                        this.L$5 = SpillingKt.nullOutSpilledVariable(sequenceScope);
                        this.L$6 = pathNode;
                        this.L$7 = directoryEntriesReader;
                        this.L$8 = path3;
                        this.I$0 = 0;
                        this.label = 1;
                        if (sequenceScope.yield(path3, this) != coroutine_suspended) {
                            directoryEntriesReader2 = directoryEntriesReader;
                            arrayDeque2 = arrayDeque;
                            path4 = path3;
                            directoryEntriesReader3 = directoryEntriesReader2;
                            path3 = path4;
                            directoryEntriesReader = directoryEntriesReader3;
                            arrayDeque = arrayDeque2;
                            LinkOption[] linkOptions32 = pathTreeWalk.getLinkOptions();
                            LinkOption[] linkOptionArr32 = (LinkOption[]) Arrays.copyOf(linkOptions32, linkOptions32.length);
                            isDirectory = Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr32, linkOptionArr32.length));
                            if (isDirectory) {
                            }
                            directoryEntriesReader = directoryEntriesReader2;
                            while (!arrayDeque.isEmpty()) {
                            }
                        }
                    }
                } else {
                    PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9338m$1();
                    throw PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9307m(path3.toString());
                }
            } else {
                linkOption = LinkOption.NOFOLLOW_LINKS;
                exists = Files.exists(path3, (LinkOption[]) Arrays.copyOf(new LinkOption[]{linkOption}, 1));
                if (exists) {
                    this.L$0 = sequenceScope;
                    this.L$1 = arrayDeque;
                    this.L$2 = directoryEntriesReader;
                    this.L$3 = SpillingKt.nullOutSpilledVariable(pathNode);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(pathTreeWalk);
                    this.L$5 = SpillingKt.nullOutSpilledVariable(sequenceScope);
                    this.L$6 = SpillingKt.nullOutSpilledVariable(pathNode);
                    this.L$7 = SpillingKt.nullOutSpilledVariable(directoryEntriesReader);
                    this.L$8 = SpillingKt.nullOutSpilledVariable(path3);
                    this.I$0 = 0;
                    this.label = 2;
                    if (sequenceScope.yield(path3, this) == coroutine_suspended) {
                    }
                }
            }
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
