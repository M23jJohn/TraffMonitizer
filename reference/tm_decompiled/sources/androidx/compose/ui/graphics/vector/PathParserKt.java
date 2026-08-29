package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.vector.PathNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: PathParser.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\u001a\u001a\u0010\u0004\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u0005\u001aX\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002\u001aX\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0002\u001a\r\u0010\u001e\u001a\u00020\r*\u00020\rH\u0082\b\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u001f"}, d2 = {"EmptyArray", "", "getEmptyArray", "()[F", "toPath", "Landroidx/compose/ui/graphics/Path;", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "target", "drawArc", "", "p", "x0", "", "y0", "x1", "y1", "a", "b", "theta", "isMoreThanHalf", "", "isPositiveArc", "arcToBezier", "cx", "cy", "e1x", "e1y", "start", "sweep", "toRadians", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PathParserKt {
    private static final float[] EmptyArray = new float[0];

    private static final double toRadians(double d) {
        return (d / 180) * 3.141592653589793d;
    }

    public static final float[] getEmptyArray() {
        return EmptyArray;
    }

    public static /* synthetic */ Path toPath$default(List list, Path path, int i, Object obj) {
        if ((i & 1) != 0) {
            path = AndroidPath_androidKt.Path();
        }
        return toPath(list, path);
    }

    public static final Path toPath(List<? extends PathNode> list, Path path) {
        int i;
        float f;
        int i2;
        PathNode pathNode;
        float f2;
        float f3;
        float f4;
        float x2;
        float y2;
        float dy2;
        float f5;
        float f6;
        float dx1;
        float dy1;
        float dy22;
        List<? extends PathNode> list2 = list;
        Path path2 = path;
        int mo4477getFillTypeRgk1Os = path2.mo4477getFillTypeRgk1Os();
        path2.rewind();
        path2.mo4479setFillTypeoQ8Xj4U(mo4477getFillTypeRgk1Os);
        PathNode pathNode2 = list2.isEmpty() ? PathNode.Close.INSTANCE : list2.get(0);
        int size = list2.size();
        float f7 = 0.0f;
        int i3 = 0;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        while (i3 < size) {
            PathNode pathNode3 = list2.get(i3);
            if (pathNode3 instanceof PathNode.Close) {
                path2.close();
                i = size;
                f = f7;
                i2 = i3;
                pathNode = pathNode3;
                f8 = f12;
                f10 = f8;
                f9 = f13;
            } else {
                if (pathNode3 instanceof PathNode.RelativeMoveTo) {
                    PathNode.RelativeMoveTo relativeMoveTo = (PathNode.RelativeMoveTo) pathNode3;
                    f10 += relativeMoveTo.getDx();
                    f11 += relativeMoveTo.getDy();
                    path2.relativeMoveTo(relativeMoveTo.getDx(), relativeMoveTo.getDy());
                    i = size;
                    f = f7;
                    i2 = i3;
                    f12 = f10;
                    f13 = f11;
                } else {
                    if (pathNode3 instanceof PathNode.MoveTo) {
                        PathNode.MoveTo moveTo = (PathNode.MoveTo) pathNode3;
                        float x = moveTo.getX();
                        float y = moveTo.getY();
                        path2.moveTo(moveTo.getX(), moveTo.getY());
                        f10 = x;
                        f12 = f10;
                        f11 = y;
                        f13 = f11;
                    } else {
                        if (pathNode3 instanceof PathNode.RelativeLineTo) {
                            PathNode.RelativeLineTo relativeLineTo = (PathNode.RelativeLineTo) pathNode3;
                            path2.relativeLineTo(relativeLineTo.getDx(), relativeLineTo.getDy());
                            f10 += relativeLineTo.getDx();
                            dy2 = relativeLineTo.getDy();
                        } else {
                            if (pathNode3 instanceof PathNode.LineTo) {
                                PathNode.LineTo lineTo = (PathNode.LineTo) pathNode3;
                                path2.lineTo(lineTo.getX(), lineTo.getY());
                                x2 = lineTo.getX();
                                y2 = lineTo.getY();
                            } else if (pathNode3 instanceof PathNode.RelativeHorizontalTo) {
                                PathNode.RelativeHorizontalTo relativeHorizontalTo = (PathNode.RelativeHorizontalTo) pathNode3;
                                path2.relativeLineTo(relativeHorizontalTo.getDx(), f7);
                                f10 += relativeHorizontalTo.getDx();
                            } else if (pathNode3 instanceof PathNode.HorizontalTo) {
                                PathNode.HorizontalTo horizontalTo = (PathNode.HorizontalTo) pathNode3;
                                path2.lineTo(horizontalTo.getX(), f11);
                                f10 = horizontalTo.getX();
                            } else if (pathNode3 instanceof PathNode.RelativeVerticalTo) {
                                PathNode.RelativeVerticalTo relativeVerticalTo = (PathNode.RelativeVerticalTo) pathNode3;
                                path2.relativeLineTo(f7, relativeVerticalTo.getDy());
                                dy2 = relativeVerticalTo.getDy();
                            } else if (pathNode3 instanceof PathNode.VerticalTo) {
                                PathNode.VerticalTo verticalTo = (PathNode.VerticalTo) pathNode3;
                                path2.lineTo(f10, verticalTo.getY());
                                f11 = verticalTo.getY();
                            } else {
                                if (pathNode3 instanceof PathNode.RelativeCurveTo) {
                                    PathNode.RelativeCurveTo relativeCurveTo = (PathNode.RelativeCurveTo) pathNode3;
                                    path2.relativeCubicTo(relativeCurveTo.getDx1(), relativeCurveTo.getDy1(), relativeCurveTo.getDx2(), relativeCurveTo.getDy2(), relativeCurveTo.getDx3(), relativeCurveTo.getDy3());
                                    dx1 = relativeCurveTo.getDx2() + f10;
                                    dy1 = relativeCurveTo.getDy2() + f11;
                                    f10 += relativeCurveTo.getDx3();
                                    dy22 = relativeCurveTo.getDy3();
                                } else {
                                    if (pathNode3 instanceof PathNode.CurveTo) {
                                        PathNode.CurveTo curveTo = (PathNode.CurveTo) pathNode3;
                                        path.cubicTo(curveTo.getX1(), curveTo.getY1(), curveTo.getX2(), curveTo.getY2(), curveTo.getX3(), curveTo.getY3());
                                        float x22 = curveTo.getX2();
                                        float y22 = curveTo.getY2();
                                        float x3 = curveTo.getX3();
                                        float y3 = curveTo.getY3();
                                        f10 = x3;
                                        f11 = y3;
                                        i = size;
                                        f = f7;
                                        i2 = i3;
                                        pathNode = pathNode3;
                                        f8 = x22;
                                        f9 = y22;
                                    } else if (pathNode3 instanceof PathNode.RelativeReflectiveCurveTo) {
                                        if (pathNode2.getIsCurve()) {
                                            float f14 = f10 - f8;
                                            f6 = f11 - f9;
                                            f5 = f14;
                                        } else {
                                            f5 = f7;
                                            f6 = f5;
                                        }
                                        PathNode.RelativeReflectiveCurveTo relativeReflectiveCurveTo = (PathNode.RelativeReflectiveCurveTo) pathNode3;
                                        path.relativeCubicTo(f5, f6, relativeReflectiveCurveTo.getDx1(), relativeReflectiveCurveTo.getDy1(), relativeReflectiveCurveTo.getDx2(), relativeReflectiveCurveTo.getDy2());
                                        dx1 = relativeReflectiveCurveTo.getDx1() + f10;
                                        dy1 = relativeReflectiveCurveTo.getDy1() + f11;
                                        f10 += relativeReflectiveCurveTo.getDx2();
                                        dy22 = relativeReflectiveCurveTo.getDy2();
                                    } else {
                                        if (pathNode3 instanceof PathNode.ReflectiveCurveTo) {
                                            if (pathNode2.getIsCurve()) {
                                                float f15 = 2;
                                                f10 = (f10 * f15) - f8;
                                                f11 = (f15 * f11) - f9;
                                            }
                                            PathNode.ReflectiveCurveTo reflectiveCurveTo = (PathNode.ReflectiveCurveTo) pathNode3;
                                            path.cubicTo(f10, f11, reflectiveCurveTo.getX1(), reflectiveCurveTo.getY1(), reflectiveCurveTo.getX2(), reflectiveCurveTo.getY2());
                                            f4 = reflectiveCurveTo.getX1();
                                            float y1 = reflectiveCurveTo.getY1();
                                            float x23 = reflectiveCurveTo.getX2();
                                            float y23 = reflectiveCurveTo.getY2();
                                            f10 = x23;
                                            f11 = y23;
                                            i = size;
                                            f = f7;
                                            i2 = i3;
                                            pathNode = pathNode3;
                                            f9 = y1;
                                        } else if (pathNode3 instanceof PathNode.RelativeQuadTo) {
                                            PathNode.RelativeQuadTo relativeQuadTo = (PathNode.RelativeQuadTo) pathNode3;
                                            path.relativeQuadraticTo(relativeQuadTo.getDx1(), relativeQuadTo.getDy1(), relativeQuadTo.getDx2(), relativeQuadTo.getDy2());
                                            f8 = relativeQuadTo.getDx1() + f10;
                                            f9 = relativeQuadTo.getDy1() + f11;
                                            f10 += relativeQuadTo.getDx2();
                                            dy2 = relativeQuadTo.getDy2();
                                        } else if (pathNode3 instanceof PathNode.QuadTo) {
                                            PathNode.QuadTo quadTo = (PathNode.QuadTo) pathNode3;
                                            path.quadraticTo(quadTo.getX1(), quadTo.getY1(), quadTo.getX2(), quadTo.getY2());
                                            f8 = quadTo.getX1();
                                            f9 = quadTo.getY1();
                                            x2 = quadTo.getX2();
                                            y2 = quadTo.getY2();
                                        } else if (pathNode3 instanceof PathNode.RelativeReflectiveQuadTo) {
                                            if (pathNode2.getIsQuad()) {
                                                f2 = f10 - f8;
                                                f3 = f11 - f9;
                                            } else {
                                                f2 = f7;
                                                f3 = f2;
                                            }
                                            PathNode.RelativeReflectiveQuadTo relativeReflectiveQuadTo = (PathNode.RelativeReflectiveQuadTo) pathNode3;
                                            path.relativeQuadraticTo(f2, f3, relativeReflectiveQuadTo.getDx(), relativeReflectiveQuadTo.getDy());
                                            f4 = f2 + f10;
                                            float f16 = f3 + f11;
                                            f10 += relativeReflectiveQuadTo.getDx();
                                            f11 += relativeReflectiveQuadTo.getDy();
                                            f9 = f16;
                                            i = size;
                                            f = f7;
                                            i2 = i3;
                                            pathNode = pathNode3;
                                        } else if (pathNode3 instanceof PathNode.ReflectiveQuadTo) {
                                            if (pathNode2.getIsQuad()) {
                                                float f17 = 2;
                                                f10 = (f10 * f17) - f8;
                                                f11 = (f17 * f11) - f9;
                                            }
                                            PathNode.ReflectiveQuadTo reflectiveQuadTo = (PathNode.ReflectiveQuadTo) pathNode3;
                                            path.quadraticTo(f10, f11, reflectiveQuadTo.getX(), reflectiveQuadTo.getY());
                                            float f18 = f10;
                                            f10 = reflectiveQuadTo.getX();
                                            f8 = f18;
                                            i = size;
                                            f = f7;
                                            i2 = i3;
                                            f9 = f11;
                                            pathNode = pathNode3;
                                            f11 = reflectiveQuadTo.getY();
                                        } else if (pathNode3 instanceof PathNode.RelativeArcTo) {
                                            PathNode.RelativeArcTo relativeArcTo = (PathNode.RelativeArcTo) pathNode3;
                                            float arcStartDx = relativeArcTo.getArcStartDx() + f10;
                                            float arcStartDy = relativeArcTo.getArcStartDy() + f11;
                                            f = f7;
                                            pathNode = pathNode3;
                                            i = size;
                                            i2 = i3;
                                            drawArc(path, f10, f11, arcStartDx, arcStartDy, relativeArcTo.getHorizontalEllipseRadius(), relativeArcTo.getVerticalEllipseRadius(), relativeArcTo.getTheta(), relativeArcTo.isMoreThanHalf(), relativeArcTo.isPositiveArc());
                                            f8 = arcStartDx;
                                            f10 = f8;
                                            f9 = arcStartDy;
                                        } else {
                                            i = size;
                                            f = f7;
                                            i2 = i3;
                                            pathNode = pathNode3;
                                            if (!(pathNode instanceof PathNode.ArcTo)) {
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            PathNode.ArcTo arcTo = (PathNode.ArcTo) pathNode;
                                            drawArc(path, f10, f11, arcTo.getArcStartX(), arcTo.getArcStartY(), arcTo.getHorizontalEllipseRadius(), arcTo.getVerticalEllipseRadius(), arcTo.getTheta(), arcTo.isMoreThanHalf(), arcTo.isPositiveArc());
                                            f8 = arcTo.getArcStartX();
                                            f10 = f8;
                                            f9 = arcTo.getArcStartY();
                                        }
                                        f8 = f4;
                                    }
                                    i3 = i2 + 1;
                                    path2 = path;
                                    pathNode2 = pathNode;
                                    size = i;
                                    f7 = f;
                                    list2 = list;
                                }
                                f11 += dy22;
                                f8 = dx1;
                                f9 = dy1;
                            }
                            f11 = y2;
                            f10 = x2;
                        }
                        f11 += dy2;
                    }
                    i = size;
                    f = f7;
                    i2 = i3;
                }
                pathNode = pathNode3;
                i3 = i2 + 1;
                path2 = path;
                pathNode2 = pathNode;
                size = i;
                f7 = f;
                list2 = list;
            }
            f11 = f9;
            i3 = i2 + 1;
            path2 = path;
            pathNode2 = pathNode;
            size = i;
            f7 = f;
            list2 = list;
        }
        return path;
    }

    private static final void drawArc(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, boolean z, boolean z2) {
        double d8;
        double d9;
        double d10 = (d7 / 180) * 3.141592653589793d;
        double cos = Math.cos(d10);
        double sin = Math.sin(d10);
        double d11 = ((d * cos) + (d2 * sin)) / d5;
        double d12 = (((-d) * sin) + (d2 * cos)) / d6;
        double d13 = ((d3 * cos) + (d4 * sin)) / d5;
        double d14 = (((-d3) * sin) + (d4 * cos)) / d6;
        double d15 = d11 - d13;
        double d16 = d12 - d14;
        double d17 = 2;
        double d18 = (d11 + d13) / d17;
        double d19 = (d12 + d14) / d17;
        double d20 = (d15 * d15) + (d16 * d16);
        if (d20 == 0.0d) {
            return;
        }
        double d21 = (1.0d / d20) - 0.25d;
        if (d21 < 0.0d) {
            double sqrt = (float) (Math.sqrt(d20) / 1.99999d);
            drawArc(path, d, d2, d3, d4, d5 * sqrt, d6 * sqrt, d7, z, z2);
            return;
        }
        double sqrt2 = Math.sqrt(d21);
        double d22 = d15 * sqrt2;
        double d23 = sqrt2 * d16;
        if (z == z2) {
            d8 = d18 - d23;
            d9 = d19 + d22;
        } else {
            d8 = d18 + d23;
            d9 = d19 - d22;
        }
        double atan2 = Math.atan2(d12 - d9, d11 - d8);
        double atan22 = Math.atan2(d14 - d9, d13 - d8) - atan2;
        if (z2 != (atan22 >= 0.0d)) {
            atan22 = atan22 > 0.0d ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
        }
        double d24 = d8 * d5;
        double d25 = d9 * d6;
        arcToBezier(path, (d24 * cos) - (d25 * sin), (d24 * sin) + (d25 * cos), d5, d6, d, d2, d10, atan2, atan22);
    }

    private static final void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        double d10 = 4;
        int ceil = (int) Math.ceil(Math.abs((d9 * d10) / 3.141592653589793d));
        double cos = Math.cos(d7);
        double sin = Math.sin(d7);
        double cos2 = Math.cos(d8);
        double sin2 = Math.sin(d8);
        double d11 = -d3;
        double d12 = d11 * cos;
        double d13 = d4 * sin;
        double d14 = (d12 * sin2) - (d13 * cos2);
        double d15 = d11 * sin;
        double d16 = d4 * cos;
        double d17 = (sin2 * d15) + (cos2 * d16);
        double d18 = d9 / ceil;
        double d19 = d17;
        double d20 = d14;
        int i = 0;
        double d21 = d5;
        double d22 = d6;
        double d23 = d8;
        while (i < ceil) {
            double d24 = d23 + d18;
            double sin3 = Math.sin(d24);
            double cos3 = Math.cos(d24);
            int i2 = i;
            double d25 = (d + ((d3 * cos) * cos3)) - (d13 * sin3);
            double d26 = d10;
            double d27 = d2 + (d3 * sin * cos3) + (d16 * sin3);
            double d28 = (d12 * sin3) - (d13 * cos3);
            double d29 = (sin3 * d15) + (cos3 * d16);
            double d30 = d24 - d23;
            int i3 = ceil;
            double tan = Math.tan(d30 / 2);
            double sin4 = (Math.sin(d30) * (Math.sqrt(d26 + ((3.0d * tan) * tan)) - 1)) / 3;
            path.cubicTo((float) (d21 + (d20 * sin4)), (float) (d22 + (d19 * sin4)), (float) (d25 - (sin4 * d28)), (float) (d27 - (sin4 * d29)), (float) d25, (float) d27);
            sin = sin;
            d18 = d18;
            d21 = d25;
            d22 = d27;
            i = i2 + 1;
            d23 = d24;
            d19 = d29;
            ceil = i3;
            d20 = d28;
            cos = cos;
            d10 = d26;
        }
    }
}
