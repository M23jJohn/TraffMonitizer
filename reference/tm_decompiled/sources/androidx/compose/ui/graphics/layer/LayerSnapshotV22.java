package androidx.compose.ui.graphics.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.media.Image;
import android.media.ImageReader;
import android.os.Looper;
import android.view.Surface;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.core.os.HandlerCompat;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: LayerSnapshot.android.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0096@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/graphics/layer/LayerSnapshotV22;", "Landroidx/compose/ui/graphics/layer/LayerSnapshotImpl;", "<init>", "()V", "toBitmap", "Landroid/graphics/Bitmap;", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LayerSnapshotV22 implements LayerSnapshotImpl {
    public static final LayerSnapshotV22 INSTANCE = new LayerSnapshotV22();

    private LayerSnapshotV22() {
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.compose.ui.graphics.layer.LayerSnapshotImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object toBitmap(GraphicsLayer graphicsLayer, Continuation<? super Bitmap> continuation) {
        LayerSnapshotV22$toBitmap$1 layerSnapshotV22$toBitmap$1;
        int i;
        AutoCloseable autoCloseable;
        Bitmap bitmap;
        if (continuation instanceof LayerSnapshotV22$toBitmap$1) {
            layerSnapshotV22$toBitmap$1 = (LayerSnapshotV22$toBitmap$1) continuation;
            if ((layerSnapshotV22$toBitmap$1.label & Integer.MIN_VALUE) != 0) {
                layerSnapshotV22$toBitmap$1.label -= Integer.MIN_VALUE;
                Object obj = layerSnapshotV22$toBitmap$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = layerSnapshotV22$toBitmap$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    long size = graphicsLayer.getSize();
                    Looper myLooper = Looper.myLooper();
                    if (myLooper == null) {
                        myLooper = Looper.getMainLooper();
                    }
                    ImageReader newInstance = ImageReader.newInstance((int) (size >> 32), (int) (size & 4294967295L), 1, 1);
                    try {
                        ImageReader imageReader = newInstance;
                        layerSnapshotV22$toBitmap$1.L$0 = graphicsLayer;
                        layerSnapshotV22$toBitmap$1.L$1 = myLooper;
                        layerSnapshotV22$toBitmap$1.L$2 = newInstance;
                        layerSnapshotV22$toBitmap$1.L$3 = imageReader;
                        layerSnapshotV22$toBitmap$1.L$4 = layerSnapshotV22$toBitmap$1;
                        layerSnapshotV22$toBitmap$1.label = 1;
                        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(layerSnapshotV22$toBitmap$1), 1);
                        cancellableContinuationImpl.initCancellability();
                        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
                        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: androidx.compose.ui.graphics.layer.LayerSnapshotV22$toBitmap$2$image$1$1
                            @Override // android.media.ImageReader.OnImageAvailableListener
                            public final void onImageAvailable(ImageReader imageReader2) {
                                CancellableContinuation<Image> cancellableContinuation = cancellableContinuationImpl2;
                                Result.Companion companion = Result.INSTANCE;
                                cancellableContinuation.resumeWith(Result.m8075constructorimpl(imageReader2.acquireLatestImage()));
                            }
                        }, HandlerCompat.createAsync(myLooper));
                        Surface surface = imageReader.getSurface();
                        Canvas lockCanvas = SurfaceUtils.INSTANCE.lockCanvas(surface);
                        try {
                            lockCanvas.drawColor(ColorKt.m4669toArgb8_81llA(Color.INSTANCE.m4641getBlack0d7_KjU()), PorterDuff.Mode.CLEAR);
                            graphicsLayer.draw$ui_graphics_release(AndroidCanvas_androidKt.Canvas(lockCanvas), null);
                            surface.unlockCanvasAndPost(lockCanvas);
                            obj = cancellableContinuationImpl.getResult();
                            if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                DebugProbesKt.probeCoroutineSuspended(layerSnapshotV22$toBitmap$1);
                            }
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            autoCloseable = newInstance;
                        } catch (Throwable th) {
                            surface.unlockCanvasAndPost(lockCanvas);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        autoCloseable = newInstance;
                        throw th;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    autoCloseable = (AutoCloseable) layerSnapshotV22$toBitmap$1.L$2;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            AutoCloseableKt.closeFinally(autoCloseable, th);
                            throw th4;
                        }
                    }
                }
                bitmap = LayerSnapshot_androidKt.toBitmap((Image) obj);
                AutoCloseableKt.closeFinally(autoCloseable, null);
                return bitmap;
            }
        }
        layerSnapshotV22$toBitmap$1 = new LayerSnapshotV22$toBitmap$1(this, continuation);
        Object obj2 = layerSnapshotV22$toBitmap$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = layerSnapshotV22$toBitmap$1.label;
        if (i != 0) {
        }
        bitmap = LayerSnapshot_androidKt.toBitmap((Image) obj2);
        AutoCloseableKt.closeFinally(autoCloseable, null);
        return bitmap;
    }
}
