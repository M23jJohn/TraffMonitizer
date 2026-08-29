package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.os.Build;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RenderEffect;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: RenderNodeApi29.android.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b5\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0015J\b\u0010q\u001a\u00020rH\u0002J\b\u0010s\u001a\u00020\u0012H\u0002J\b\u0010t\u001a\u00020\u0012H\u0002J\b\u0010u\u001a\u00020\u0010H\u0002J\u001b\u0010v\u001a\u00020r*\u00020\t2\u0006\u0010n\u001a\u00020\rH\u0002¢\u0006\u0004\bw\u0010xJ\u0012\u0010{\u001a\u00020r2\b\u0010|\u001a\u0004\u0018\u00010}H\u0016J(\u0010~\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001bH\u0016J\u0011\u0010\u007f\u001a\u00020r2\u0007\u0010\u0080\u0001\u001a\u00020\u001bH\u0016J\u0012\u0010\u0081\u0001\u001a\u00020r2\u0007\u0010\u0080\u0001\u001a\u00020\u001bH\u0016J6\u0010\u0082\u0001\u001a\u00020r2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\n\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u00012\u0015\u0010\u0087\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0089\u0001\u0012\u0004\u0012\u00020r0\u0088\u0001H\u0016J\u0013\u0010\u008a\u0001\u001a\u00020r2\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0016J\u0013\u0010\u008d\u0001\u001a\u00020r2\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0016J\u0013\u0010\u008e\u0001\u001a\u00020r2\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0016J\u0011\u0010\u0091\u0001\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\n\u0010\u0092\u0001\u001a\u00030\u0093\u0001H\u0016J\t\u0010\u0094\u0001\u001a\u00020rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001dR\u0014\u0010\"\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u001dR\u0014\u0010$\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u001dR\u0014\u0010&\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u001dR$\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u0010/\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b0\u0010,\"\u0004\b1\u0010.R$\u00102\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b3\u0010,\"\u0004\b4\u0010.R$\u00105\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b6\u0010,\"\u0004\b7\u0010.R$\u00108\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b9\u0010,\"\u0004\b:\u0010.R$\u0010;\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u001b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b<\u0010\u001d\"\u0004\b=\u0010>R$\u0010?\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u001b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b@\u0010\u001d\"\u0004\bA\u0010>R$\u0010B\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bC\u0010,\"\u0004\bD\u0010.R$\u0010E\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bF\u0010,\"\u0004\bG\u0010.R$\u0010H\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bI\u0010,\"\u0004\bJ\u0010.R$\u0010K\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bL\u0010,\"\u0004\bM\u0010.R$\u0010N\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bO\u0010,\"\u0004\bP\u0010.R$\u0010Q\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bR\u0010,\"\u0004\bS\u0010.R$\u0010T\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u00128V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR$\u0010Y\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u00128V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bZ\u0010V\"\u0004\b[\u0010XR$\u0010\\\u001a\u00020)2\u0006\u0010(\u001a\u00020)8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b]\u0010,\"\u0004\b^\u0010.R&\u0010`\u001a\u00020_2\u0006\u0010(\u001a\u00020_@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\ba\u0010\u001d\"\u0004\bb\u0010>R(\u0010d\u001a\u0004\u0018\u00010c2\b\u0010(\u001a\u0004\u0018\u00010c@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR(\u0010i\u001a\u0004\u0018\u00010\u000b2\b\u0010(\u001a\u0004\u0018\u00010\u000b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR$\u0010n\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bo\u0010\u001d\"\u0004\bp\u0010>R\u0014\u0010y\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bz\u0010V¨\u0006\u0095\u0001"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeApi29;", "Landroidx/compose/ui/platform/DeviceRenderNode;", "ownerView", "Landroidx/compose/ui/platform/AndroidComposeView;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "getOwnerView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "renderNode", "Landroid/graphics/RenderNode;", "internalRenderEffect", "Landroidx/compose/ui/graphics/RenderEffect;", "internalCompositingStrategy", "Landroidx/compose/ui/graphics/CompositingStrategy;", "I", "layerPaint", "Landroidx/compose/ui/graphics/Paint;", "isUsingCompositingLayer", "", "isUsingCompositingLayer$ui_release", "hasOverlappingRendering", "hasOverlappingRendering$ui_release", "uniqueId", "", "getUniqueId", "()J", "left", "", "getLeft", "()I", "top", "getTop", "right", "getRight", "bottom", "getBottom", "width", "getWidth", "height", "getHeight", "value", "", "scaleX", "getScaleX", "()F", "setScaleX", "(F)V", "scaleY", "getScaleY", "setScaleY", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "elevation", "getElevation", "setElevation", "ambientShadowColor", "getAmbientShadowColor", "setAmbientShadowColor", "(I)V", "spotShadowColor", "getSpotShadowColor", "setSpotShadowColor", "rotationZ", "getRotationZ", "setRotationZ", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "cameraDistance", "getCameraDistance", "setCameraDistance", "pivotX", "getPivotX", "setPivotX", "pivotY", "getPivotY", "setPivotY", "clipToOutline", "getClipToOutline", "()Z", "setClipToOutline", "(Z)V", "clipToBounds", "getClipToBounds", "setClipToBounds", "alpha", "getAlpha", "setAlpha", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "setBlendMode-s9anfk8", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "compositingStrategy", "getCompositingStrategy--NrFUSI", "setCompositingStrategy-aDBOjCE", "updateLayerProperties", "", "requiresCompositingLayer", "requiresLayerPaint", "obtainLayerPaint", "applyCompositingStrategy", "applyCompositingStrategy-Qu9p0E8", "(Landroid/graphics/RenderNode;I)V", "hasDisplayList", "getHasDisplayList", "setOutline", "outline", "Landroid/graphics/Outline;", "setPosition", "offsetLeftAndRight", "offset", "offsetTopAndBottom", "record", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "clipPath", "Landroidx/compose/ui/graphics/Path;", "drawBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Canvas;", "getMatrix", "matrix", "Landroid/graphics/Matrix;", "getInverseMatrix", "drawInto", "canvas", "Landroid/graphics/Canvas;", "setHasOverlappingRendering", "dumpRenderNodeData", "Landroidx/compose/ui/platform/DeviceRenderNodeData;", "discardDisplayList", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RenderNodeApi29 implements DeviceRenderNode {
    public static final int $stable = 8;
    private ColorFilter colorFilter;
    private RenderEffect internalRenderEffect;
    private Paint layerPaint;
    private final AndroidComposeView ownerView;
    private final RenderNode renderNode = ComponentDialog$$ExternalSyntheticApiModelOutline0.m("Compose");
    private int internalCompositingStrategy = CompositingStrategy.INSTANCE.m4705getAutoNrFUSI();
    private int blendMode = BlendMode.INSTANCE.m4556getSrcOver0nO6VwU();

    public RenderNodeApi29(AndroidComposeView androidComposeView) {
        this.ownerView = androidComposeView;
    }

    public final AndroidComposeView getOwnerView() {
        return this.ownerView;
    }

    public final boolean isUsingCompositingLayer$ui_release() {
        boolean useCompositingLayer;
        useCompositingLayer = this.renderNode.getUseCompositingLayer();
        return useCompositingLayer;
    }

    public final boolean hasOverlappingRendering$ui_release() {
        boolean hasOverlappingRendering;
        hasOverlappingRendering = this.renderNode.hasOverlappingRendering();
        return hasOverlappingRendering;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public long getUniqueId() {
        long uniqueId;
        uniqueId = this.renderNode.getUniqueId();
        return uniqueId;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public int getLeft() {
        int left;
        left = this.renderNode.getLeft();
        return left;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public int getTop() {
        int top;
        top = this.renderNode.getTop();
        return top;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public int getRight() {
        int right;
        right = this.renderNode.getRight();
        return right;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public int getBottom() {
        int bottom;
        bottom = this.renderNode.getBottom();
        return bottom;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public int getWidth() {
        int width;
        width = this.renderNode.getWidth();
        return width;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public int getHeight() {
        int height;
        height = this.renderNode.getHeight();
        return height;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getScaleX() {
        float scaleX;
        scaleX = this.renderNode.getScaleX();
        return scaleX;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setScaleX(float f) {
        this.renderNode.setScaleX(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getScaleY() {
        float scaleY;
        scaleY = this.renderNode.getScaleY();
        return scaleY;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setScaleY(float f) {
        this.renderNode.setScaleY(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getTranslationX() {
        float translationX;
        translationX = this.renderNode.getTranslationX();
        return translationX;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setTranslationX(float f) {
        this.renderNode.setTranslationX(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getTranslationY() {
        float translationY;
        translationY = this.renderNode.getTranslationY();
        return translationY;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setTranslationY(float f) {
        this.renderNode.setTranslationY(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getElevation() {
        float elevation;
        elevation = this.renderNode.getElevation();
        return elevation;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setElevation(float f) {
        this.renderNode.setElevation(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public int getAmbientShadowColor() {
        int ambientShadowColor;
        ambientShadowColor = this.renderNode.getAmbientShadowColor();
        return ambientShadowColor;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setAmbientShadowColor(int i) {
        this.renderNode.setAmbientShadowColor(i);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public int getSpotShadowColor() {
        int spotShadowColor;
        spotShadowColor = this.renderNode.getSpotShadowColor();
        return spotShadowColor;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setSpotShadowColor(int i) {
        this.renderNode.setSpotShadowColor(i);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getRotationZ() {
        float rotationZ;
        rotationZ = this.renderNode.getRotationZ();
        return rotationZ;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setRotationZ(float f) {
        this.renderNode.setRotationZ(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getRotationX() {
        float rotationX;
        rotationX = this.renderNode.getRotationX();
        return rotationX;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setRotationX(float f) {
        this.renderNode.setRotationX(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getRotationY() {
        float rotationY;
        rotationY = this.renderNode.getRotationY();
        return rotationY;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setRotationY(float f) {
        this.renderNode.setRotationY(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getCameraDistance() {
        float cameraDistance;
        cameraDistance = this.renderNode.getCameraDistance();
        return cameraDistance;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setCameraDistance(float f) {
        this.renderNode.setCameraDistance(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getPivotX() {
        float pivotX;
        pivotX = this.renderNode.getPivotX();
        return pivotX;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setPivotX(float f) {
        this.renderNode.setPivotX(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getPivotY() {
        float pivotY;
        pivotY = this.renderNode.getPivotY();
        return pivotY;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setPivotY(float f) {
        this.renderNode.setPivotY(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public boolean getClipToOutline() {
        boolean clipToOutline;
        clipToOutline = this.renderNode.getClipToOutline();
        return clipToOutline;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setClipToOutline(boolean z) {
        this.renderNode.setClipToOutline(z);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public boolean getClipToBounds() {
        boolean clipToBounds;
        clipToBounds = this.renderNode.getClipToBounds();
        return clipToBounds;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setClipToBounds(boolean z) {
        this.renderNode.setClipToBounds(z);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public float getAlpha() {
        float alpha;
        alpha = this.renderNode.getAlpha();
        return alpha;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setAlpha(float f) {
        this.renderNode.setAlpha(f);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    /* renamed from: getBlendMode-0nO6VwU, reason: from getter */
    public int getBlendMode() {
        return this.blendMode;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    /* renamed from: setBlendMode-s9anfk8 */
    public void mo6494setBlendModes9anfk8(int i) {
        this.blendMode = i;
        obtainLayerPaint().mo4464setBlendModes9anfk8(i);
        updateLayerProperties();
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
        obtainLayerPaint().setColorFilter(colorFilter);
        updateLayerProperties();
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    /* renamed from: getRenderEffect, reason: from getter */
    public RenderEffect getInternalRenderEffect() {
        return this.internalRenderEffect;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setRenderEffect(RenderEffect renderEffect) {
        this.internalRenderEffect = renderEffect;
        if (Build.VERSION.SDK_INT >= 31) {
            RenderNodeApi29VerificationHelper.INSTANCE.setRenderEffect(this.renderNode, renderEffect);
        }
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    /* renamed from: getCompositingStrategy--NrFUSI, reason: from getter */
    public int getInternalCompositingStrategy() {
        return this.internalCompositingStrategy;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    /* renamed from: setCompositingStrategy-aDBOjCE */
    public void mo6495setCompositingStrategyaDBOjCE(int i) {
        this.internalCompositingStrategy = i;
        updateLayerProperties();
    }

    private final void updateLayerProperties() {
        if (requiresCompositingLayer()) {
            m6567applyCompositingStrategyQu9p0E8(this.renderNode, CompositingStrategy.INSTANCE.m4707getOffscreenNrFUSI());
        } else {
            m6567applyCompositingStrategyQu9p0E8(this.renderNode, this.internalCompositingStrategy);
        }
    }

    private final boolean requiresCompositingLayer() {
        return CompositingStrategy.m4701equalsimpl0(getInternalCompositingStrategy(), CompositingStrategy.INSTANCE.m4707getOffscreenNrFUSI()) || requiresLayerPaint();
    }

    private final boolean requiresLayerPaint() {
        return (BlendMode.m4525equalsimpl0(getBlendMode(), BlendMode.INSTANCE.m4556getSrcOver0nO6VwU()) && getColorFilter() == null) ? false : true;
    }

    private final Paint obtainLayerPaint() {
        Paint paint = this.layerPaint;
        if (paint != null) {
            return paint;
        }
        Paint Paint = AndroidPaint_androidKt.Paint();
        this.layerPaint = Paint;
        return Paint;
    }

    /* renamed from: applyCompositingStrategy-Qu9p0E8, reason: not valid java name */
    private final void m6567applyCompositingStrategyQu9p0E8(RenderNode renderNode, int i) {
        if (CompositingStrategy.m4701equalsimpl0(i, CompositingStrategy.INSTANCE.m4707getOffscreenNrFUSI())) {
            Paint paint = this.layerPaint;
            renderNode.setUseCompositingLayer(true, paint != null ? paint.getInternalPaint() : null);
            renderNode.setHasOverlappingRendering(true);
        } else if (CompositingStrategy.m4701equalsimpl0(i, CompositingStrategy.INSTANCE.m4706getModulateAlphaNrFUSI())) {
            renderNode.setUseCompositingLayer(false, null);
            renderNode.setHasOverlappingRendering(false);
        } else {
            renderNode.setUseCompositingLayer(false, null);
            renderNode.setHasOverlappingRendering(true);
        }
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public boolean getHasDisplayList() {
        boolean hasDisplayList;
        hasDisplayList = this.renderNode.hasDisplayList();
        return hasDisplayList;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void setOutline(Outline outline) {
        this.renderNode.setOutline(outline);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public boolean setPosition(int left, int top, int right, int bottom) {
        boolean position;
        position = this.renderNode.setPosition(left, top, right, bottom);
        return position;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void offsetLeftAndRight(int offset) {
        this.renderNode.offsetLeftAndRight(offset);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void offsetTopAndBottom(int offset) {
        this.renderNode.offsetTopAndBottom(offset);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void record(CanvasHolder canvasHolder, Path clipPath, Function1<? super Canvas, Unit> drawBlock) {
        RecordingCanvas beginRecording;
        beginRecording = this.renderNode.beginRecording();
        android.graphics.Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(beginRecording);
        AndroidCanvas androidCanvas = canvasHolder.getAndroidCanvas();
        if (clipPath != null) {
            androidCanvas.save();
            Canvas.CC.m4587clipPathmtrdDE$default(androidCanvas, clipPath, 0, 2, null);
        }
        drawBlock.invoke(androidCanvas);
        if (clipPath != null) {
            androidCanvas.restore();
        }
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        this.renderNode.endRecording();
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void getMatrix(Matrix matrix) {
        this.renderNode.getMatrix(matrix);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void getInverseMatrix(Matrix matrix) {
        this.renderNode.getInverseMatrix(matrix);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void drawInto(android.graphics.Canvas canvas) {
        canvas.drawRenderNode(this.renderNode);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public boolean setHasOverlappingRendering(boolean hasOverlappingRendering) {
        boolean hasOverlappingRendering2;
        hasOverlappingRendering2 = this.renderNode.setHasOverlappingRendering(hasOverlappingRendering);
        return hasOverlappingRendering2;
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public DeviceRenderNodeData dumpRenderNodeData() {
        long uniqueId;
        int left;
        int top;
        int right;
        int bottom;
        int width;
        int height;
        float scaleX;
        float scaleY;
        float translationX;
        float translationY;
        float elevation;
        int ambientShadowColor;
        int spotShadowColor;
        float rotationZ;
        float rotationX;
        float rotationY;
        float cameraDistance;
        float pivotX;
        float pivotY;
        boolean clipToOutline;
        boolean clipToBounds;
        float alpha;
        uniqueId = this.renderNode.getUniqueId();
        left = this.renderNode.getLeft();
        top = this.renderNode.getTop();
        right = this.renderNode.getRight();
        bottom = this.renderNode.getBottom();
        width = this.renderNode.getWidth();
        height = this.renderNode.getHeight();
        scaleX = this.renderNode.getScaleX();
        scaleY = this.renderNode.getScaleY();
        translationX = this.renderNode.getTranslationX();
        translationY = this.renderNode.getTranslationY();
        elevation = this.renderNode.getElevation();
        ambientShadowColor = this.renderNode.getAmbientShadowColor();
        spotShadowColor = this.renderNode.getSpotShadowColor();
        rotationZ = this.renderNode.getRotationZ();
        rotationX = this.renderNode.getRotationX();
        rotationY = this.renderNode.getRotationY();
        cameraDistance = this.renderNode.getCameraDistance();
        pivotX = this.renderNode.getPivotX();
        pivotY = this.renderNode.getPivotY();
        clipToOutline = this.renderNode.getClipToOutline();
        clipToBounds = this.renderNode.getClipToBounds();
        alpha = this.renderNode.getAlpha();
        return new DeviceRenderNodeData(uniqueId, left, top, right, bottom, width, height, scaleX, scaleY, translationX, translationY, elevation, ambientShadowColor, spotShadowColor, rotationZ, rotationX, rotationY, cameraDistance, pivotX, pivotY, clipToOutline, clipToBounds, alpha, this.internalRenderEffect, getBlendMode(), getColorFilter(), this.internalCompositingStrategy, null);
    }

    @Override // androidx.compose.ui.platform.DeviceRenderNode
    public void discardDisplayList() {
        this.renderNode.discardDisplayList();
    }
}
