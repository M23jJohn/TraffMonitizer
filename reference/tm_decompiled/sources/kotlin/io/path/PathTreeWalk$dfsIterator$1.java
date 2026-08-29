package kotlin.io.path;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;

/* compiled from: PathTreeWalk.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/nio/file/Path;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlin.io.path.PathTreeWalk$dfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {191, 197, 210, 216}, m = "invokeSuspend", n = {"$this$iterator", "stack", "entriesReader", "startNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded", "$this$iterator", "stack", "entriesReader", "startNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded", "$this$iterator", "stack", "entriesReader", "startNode", "topNode", "topIterator", "pathNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded", "$this$iterator", "stack", "entriesReader", "startNode", "topNode", "topIterator", "pathNode", "this_$iv", "$this$yieldIfNeeded$iv", "node$iv", "entriesReader$iv", "path$iv", "$i$f$yieldIfNeeded"}, nl = {193, 199, 212, 218}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0"}, v = 2)
/* loaded from: classes3.dex */
final class PathTreeWalk$dfsIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ PathTreeWalk this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PathTreeWalk$dfsIterator$1(PathTreeWalk pathTreeWalk, Continuation<? super PathTreeWalk$dfsIterator$1> continuation) {
        super(2, continuation);
        this.this$0 = pathTreeWalk;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$1 = new PathTreeWalk$dfsIterator$1(this.this$0, continuation);
        pathTreeWalk$dfsIterator$1.L$0 = obj;
        return pathTreeWalk$dfsIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PathTreeWalk$dfsIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x01f5, code lost:
    
        r6 = r13;
        r4 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0325, code lost:
    
        if (r1.yield(r14, r17) == r2) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01ef, code lost:
    
        if (r1.yield(r12, r17) == r2) goto L70;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0189  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0325 -> B:8:0x0328). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x032b -> B:8:0x0328). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x032d -> B:8:0x0328). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ArrayDeque arrayDeque;
        DirectoryEntriesReader directoryEntriesReader;
        boolean followLinks;
        PathNode pathNode;
        Path path;
        Path path2;
        Object keyOf;
        boolean isDirectory;
        LinkOption linkOption;
        boolean exists;
        DirectoryEntriesReader directoryEntriesReader2;
        PathNode pathNode2;
        boolean createsCycle;
        PathTreeWalk pathTreeWalk;
        Path path3;
        DirectoryEntriesReader directoryEntriesReader3;
        PathNode pathNode3;
        DirectoryEntriesReader directoryEntriesReader4;
        ArrayDeque arrayDeque2;
        PathNode pathNode4;
        PathTreeWalk pathTreeWalk2;
        Path path4;
        boolean isDirectory2;
        Path path5;
        DirectoryEntriesReader directoryEntriesReader5;
        PathNode pathNode5;
        PathNode pathNode6;
        PathTreeWalk pathTreeWalk3;
        boolean isDirectory3;
        ArrayDeque arrayDeque3;
        PathNode pathNode7;
        PathNode pathNode8;
        Path path6;
        char c;
        boolean isDirectory4;
        LinkOption linkOption2;
        boolean exists2;
        boolean createsCycle2;
        SequenceScope sequenceScope = (SequenceScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        char c2 = 4;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            arrayDeque = new ArrayDeque();
            followLinks = this.this$0.getFollowLinks();
            directoryEntriesReader = new DirectoryEntriesReader(followLinks);
            path = this.this$0.start;
            path2 = this.this$0.start;
            keyOf = PathTreeWalkKt.keyOf(path2, this.this$0.getLinkOptions());
            pathNode = new PathNode(path, keyOf, null);
            PathTreeWalk pathTreeWalk4 = this.this$0;
            Path path7 = pathNode.getPath();
            if (pathNode.getParent() != null) {
                PathsKt.checkFileName(path7);
            }
            LinkOption[] linkOptions = pathTreeWalk4.getLinkOptions();
            LinkOption[] linkOptionArr = (LinkOption[]) Arrays.copyOf(linkOptions, linkOptions.length);
            isDirectory = Files.isDirectory(path7, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
            if (isDirectory) {
                createsCycle = PathTreeWalkKt.createsCycle(pathNode);
                if (!createsCycle) {
                    if (!pathTreeWalk4.getIncludeDirectories()) {
                        pathTreeWalk = pathTreeWalk4;
                        path3 = path7;
                        directoryEntriesReader3 = directoryEntriesReader;
                        pathNode3 = pathNode;
                        LinkOption[] linkOptions2 = pathTreeWalk.getLinkOptions();
                        LinkOption[] linkOptionArr2 = (LinkOption[]) Arrays.copyOf(linkOptions2, linkOptions2.length);
                        isDirectory2 = Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr2, linkOptionArr2.length));
                        if (isDirectory2) {
                        }
                        pathNode2 = pathNode3;
                        directoryEntriesReader2 = directoryEntriesReader3;
                    } else {
                        this.L$0 = sequenceScope;
                        this.L$1 = arrayDeque;
                        this.L$2 = directoryEntriesReader;
                        this.L$3 = pathNode;
                        this.L$4 = pathTreeWalk4;
                        this.L$5 = SpillingKt.nullOutSpilledVariable(sequenceScope);
                        this.L$6 = pathNode;
                        this.L$7 = directoryEntriesReader;
                        this.L$8 = path7;
                        this.I$0 = 0;
                        this.label = 1;
                        if (sequenceScope.yield(path7, this) != coroutine_suspended) {
                            directoryEntriesReader4 = directoryEntriesReader;
                            arrayDeque2 = arrayDeque;
                            pathNode4 = pathNode;
                            pathTreeWalk2 = pathTreeWalk4;
                            path4 = path7;
                            directoryEntriesReader3 = directoryEntriesReader4;
                            pathNode3 = pathNode4;
                            ArrayDeque arrayDeque4 = arrayDeque2;
                            path3 = path4;
                            directoryEntriesReader = directoryEntriesReader4;
                            pathTreeWalk = pathTreeWalk2;
                            pathNode = pathNode4;
                            arrayDeque = arrayDeque4;
                            LinkOption[] linkOptions22 = pathTreeWalk.getLinkOptions();
                            LinkOption[] linkOptionArr22 = (LinkOption[]) Arrays.copyOf(linkOptions22, linkOptions22.length);
                            isDirectory2 = Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr22, linkOptionArr22.length));
                            if (isDirectory2) {
                            }
                            pathNode2 = pathNode3;
                            directoryEntriesReader2 = directoryEntriesReader3;
                        }
                    }
                } else {
                    PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9338m$1();
                    throw PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9307m(path7.toString());
                }
            } else {
                linkOption = LinkOption.NOFOLLOW_LINKS;
                exists = Files.exists(path7, (LinkOption[]) Arrays.copyOf(new LinkOption[]{linkOption}, 1));
                if (exists) {
                    this.L$0 = sequenceScope;
                    this.L$1 = arrayDeque;
                    this.L$2 = directoryEntriesReader;
                    this.L$3 = SpillingKt.nullOutSpilledVariable(pathNode);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(pathTreeWalk4);
                    this.L$5 = SpillingKt.nullOutSpilledVariable(sequenceScope);
                    this.L$6 = SpillingKt.nullOutSpilledVariable(pathNode);
                    this.L$7 = SpillingKt.nullOutSpilledVariable(directoryEntriesReader);
                    this.L$8 = SpillingKt.nullOutSpilledVariable(path7);
                    this.I$0 = 0;
                    this.label = 2;
                }
                directoryEntriesReader2 = directoryEntriesReader;
                pathNode2 = pathNode;
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            path4 = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9314m(this.L$8);
            directoryEntriesReader4 = (DirectoryEntriesReader) this.L$7;
            pathNode4 = (PathNode) this.L$6;
            pathTreeWalk2 = (PathTreeWalk) this.L$4;
            pathNode3 = (PathNode) this.L$3;
            directoryEntriesReader3 = (DirectoryEntriesReader) this.L$2;
            arrayDeque2 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(obj);
            ArrayDeque arrayDeque42 = arrayDeque2;
            path3 = path4;
            directoryEntriesReader = directoryEntriesReader4;
            pathTreeWalk = pathTreeWalk2;
            pathNode = pathNode4;
            arrayDeque = arrayDeque42;
            LinkOption[] linkOptions222 = pathTreeWalk.getLinkOptions();
            LinkOption[] linkOptionArr222 = (LinkOption[]) Arrays.copyOf(linkOptions222, linkOptions222.length);
            isDirectory2 = Files.isDirectory(path3, (LinkOption[]) Arrays.copyOf(linkOptionArr222, linkOptionArr222.length));
            if (isDirectory2) {
                pathNode3.setContentIterator(directoryEntriesReader.readEntries(pathNode).iterator());
                arrayDeque.addLast(pathNode3);
            }
            pathNode2 = pathNode3;
            directoryEntriesReader2 = directoryEntriesReader3;
        } else if (i == 2) {
            PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9314m(this.L$8);
            pathNode2 = (PathNode) this.L$3;
            directoryEntriesReader2 = (DirectoryEntriesReader) this.L$2;
            arrayDeque = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(obj);
        } else if (i == 3) {
            path6 = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9314m(this.L$11);
            directoryEntriesReader2 = (DirectoryEntriesReader) this.L$10;
            pathNode8 = (PathNode) this.L$9;
            pathTreeWalk3 = (PathTreeWalk) this.L$7;
            pathNode6 = (PathNode) this.L$6;
            pathNode7 = (PathNode) this.L$3;
            directoryEntriesReader5 = (DirectoryEntriesReader) this.L$2;
            arrayDeque3 = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(obj);
            ArrayDeque arrayDeque5 = arrayDeque3;
            path5 = path6;
            pathNode2 = pathNode7;
            pathNode5 = pathNode8;
            arrayDeque = arrayDeque5;
            LinkOption[] linkOptions3 = pathTreeWalk3.getLinkOptions();
            LinkOption[] linkOptionArr3 = (LinkOption[]) Arrays.copyOf(linkOptions3, linkOptions3.length);
            isDirectory3 = Files.isDirectory(path5, (LinkOption[]) Arrays.copyOf(linkOptionArr3, linkOptionArr3.length));
            if (isDirectory3) {
                pathNode6.setContentIterator(directoryEntriesReader2.readEntries(pathNode5).iterator());
                arrayDeque.addLast(pathNode6);
            }
            directoryEntriesReader2 = directoryEntriesReader5;
            c2 = 4;
        } else {
            if (i != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9314m(this.L$11);
            pathNode2 = (PathNode) this.L$3;
            directoryEntriesReader2 = (DirectoryEntriesReader) this.L$2;
            arrayDeque = (ArrayDeque) this.L$1;
            ResultKt.throwOnFailure(obj);
            c = 4;
            c2 = c;
        }
        if (arrayDeque.isEmpty()) {
            PathNode pathNode9 = (PathNode) arrayDeque.last();
            Iterator<PathNode> contentIterator = pathNode9.getContentIterator();
            Intrinsics.checkNotNull(contentIterator);
            if (contentIterator.hasNext()) {
                pathNode5 = contentIterator.next();
                PathTreeWalk pathTreeWalk5 = this.this$0;
                path5 = pathNode5.getPath();
                if (pathNode5.getParent() != null) {
                    PathsKt.checkFileName(path5);
                }
                LinkOption[] linkOptions4 = pathTreeWalk5.getLinkOptions();
                LinkOption[] linkOptionArr4 = (LinkOption[]) Arrays.copyOf(linkOptions4, linkOptions4.length);
                isDirectory4 = Files.isDirectory(path5, (LinkOption[]) Arrays.copyOf(linkOptionArr4, linkOptionArr4.length));
                if (isDirectory4) {
                    createsCycle2 = PathTreeWalkKt.createsCycle(pathNode5);
                    if (!createsCycle2) {
                        if (!pathTreeWalk5.getIncludeDirectories()) {
                            pathNode6 = pathNode5;
                            pathTreeWalk3 = pathTreeWalk5;
                            directoryEntriesReader5 = directoryEntriesReader2;
                            LinkOption[] linkOptions32 = pathTreeWalk3.getLinkOptions();
                            LinkOption[] linkOptionArr32 = (LinkOption[]) Arrays.copyOf(linkOptions32, linkOptions32.length);
                            isDirectory3 = Files.isDirectory(path5, (LinkOption[]) Arrays.copyOf(linkOptionArr32, linkOptionArr32.length));
                            if (isDirectory3) {
                            }
                            directoryEntriesReader2 = directoryEntriesReader5;
                            c2 = 4;
                            if (arrayDeque.isEmpty()) {
                            }
                        } else {
                            this.L$0 = sequenceScope;
                            this.L$1 = arrayDeque;
                            this.L$2 = directoryEntriesReader2;
                            this.L$3 = SpillingKt.nullOutSpilledVariable(pathNode2);
                            this.L$4 = SpillingKt.nullOutSpilledVariable(pathNode9);
                            this.L$5 = SpillingKt.nullOutSpilledVariable(contentIterator);
                            this.L$6 = pathNode5;
                            this.L$7 = pathTreeWalk5;
                            this.L$8 = SpillingKt.nullOutSpilledVariable(sequenceScope);
                            this.L$9 = pathNode5;
                            this.L$10 = directoryEntriesReader2;
                            this.L$11 = path5;
                            this.I$0 = 0;
                            this.label = 3;
                            if (sequenceScope.yield(path5, this) != coroutine_suspended) {
                                pathNode6 = pathNode5;
                                pathTreeWalk3 = pathTreeWalk5;
                                pathNode7 = pathNode2;
                                directoryEntriesReader5 = directoryEntriesReader2;
                                path6 = path5;
                                arrayDeque3 = arrayDeque;
                                pathNode8 = pathNode6;
                                ArrayDeque arrayDeque52 = arrayDeque3;
                                path5 = path6;
                                pathNode2 = pathNode7;
                                pathNode5 = pathNode8;
                                arrayDeque = arrayDeque52;
                                LinkOption[] linkOptions322 = pathTreeWalk3.getLinkOptions();
                                LinkOption[] linkOptionArr322 = (LinkOption[]) Arrays.copyOf(linkOptions322, linkOptions322.length);
                                isDirectory3 = Files.isDirectory(path5, (LinkOption[]) Arrays.copyOf(linkOptionArr322, linkOptionArr322.length));
                                if (isDirectory3) {
                                }
                                directoryEntriesReader2 = directoryEntriesReader5;
                                c2 = 4;
                                if (arrayDeque.isEmpty()) {
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    } else {
                        PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9338m$1();
                        throw PathTreeWalk$$ExternalSyntheticApiModelOutline0.m9307m(path5.toString());
                    }
                } else {
                    linkOption2 = LinkOption.NOFOLLOW_LINKS;
                    exists2 = Files.exists(path5, (LinkOption[]) Arrays.copyOf(new LinkOption[]{linkOption2}, 1));
                    if (exists2) {
                        this.L$0 = sequenceScope;
                        this.L$1 = arrayDeque;
                        this.L$2 = directoryEntriesReader2;
                        this.L$3 = SpillingKt.nullOutSpilledVariable(pathNode2);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(pathNode9);
                        this.L$5 = SpillingKt.nullOutSpilledVariable(contentIterator);
                        this.L$6 = SpillingKt.nullOutSpilledVariable(pathNode5);
                        this.L$7 = SpillingKt.nullOutSpilledVariable(pathTreeWalk5);
                        this.L$8 = SpillingKt.nullOutSpilledVariable(sequenceScope);
                        this.L$9 = SpillingKt.nullOutSpilledVariable(pathNode5);
                        this.L$10 = SpillingKt.nullOutSpilledVariable(directoryEntriesReader2);
                        this.L$11 = SpillingKt.nullOutSpilledVariable(path5);
                        this.I$0 = 0;
                        c = 4;
                        this.label = 4;
                    } else {
                        c = 4;
                    }
                }
                return coroutine_suspended;
            }
            c = c2;
            arrayDeque.removeLast();
            c2 = c;
            if (arrayDeque.isEmpty()) {
            }
        }
    }
}
