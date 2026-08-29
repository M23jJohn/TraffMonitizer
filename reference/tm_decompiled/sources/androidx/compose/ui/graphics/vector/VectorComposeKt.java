package androidx.compose.ui.graphics.vector;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: VectorCompose.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0085\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u0013\u001a£\u0001\u0010\u0014\u001a\u00020\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020\u0005H\u0007¢\u0006\u0004\b&\u0010'¨\u0006("}, d2 = {"Group", "", HintConstants.AUTOFILL_HINT_NAME, "", "rotation", "", "pivotX", "pivotY", "scaleX", "scaleY", "translationX", "translationY", "clipPathData", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/graphics/vector/VectorComposable;", "(Ljava/lang/String;FFFFFFFLjava/util/List;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Path", "pathData", "pathFillType", "Landroidx/compose/ui/graphics/PathFillType;", "fill", "Landroidx/compose/ui/graphics/Brush;", "fillAlpha", "stroke", "strokeAlpha", "strokeLineWidth", "strokeLineCap", "Landroidx/compose/ui/graphics/StrokeCap;", "strokeLineJoin", "Landroidx/compose/ui/graphics/StrokeJoin;", "strokeLineMiter", "trimPathStart", "trimPathEnd", "trimPathOffset", "Path-9cdaXJ4", "(Ljava/util/List;ILjava/lang/String;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Brush;FFIIFFFFLandroidx/compose/runtime/Composer;III)V", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class VectorComposeKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void Group(String str, float f, float f2, float f3, float f4, float f5, float f6, float f7, List<? extends PathNode> list, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        String str2;
        int i3;
        float f8;
        int i4;
        float f9;
        int i5;
        float f10;
        int i6;
        float f11;
        int i7;
        int i8;
        int i9;
        int i10;
        final float f12;
        float f13;
        final List<? extends PathNode> list2;
        final float f14;
        final float f15;
        ScopeUpdateScope endRestartGroup;
        float f16;
        float f17;
        float f18;
        VectorComposeKt$Group$1$1 rememberedValue;
        int i11;
        Composer startRestartGroup = composer.startRestartGroup(-1851426205);
        ComposerKt.sourceInformation(startRestartGroup, "C(Group)P(2,5,3,4,6,7,8,9)59@2563L20,58@2501L586:VectorCompose.kt#huu6hf");
        int i12 = i2 & 1;
        if (i12 != 0) {
            i3 = i | 6;
            str2 = str;
        } else if ((i & 6) == 0) {
            str2 = str;
            i3 = (startRestartGroup.changed(str2) ? 4 : 2) | i;
        } else {
            str2 = str;
            i3 = i;
        }
        int i13 = i2 & 2;
        if (i13 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            f8 = f;
            i3 |= startRestartGroup.changed(f8) ? 32 : 16;
            i4 = i2 & 4;
            if (i4 == 0) {
                i3 |= 384;
            } else if ((i & 384) == 0) {
                f9 = f2;
                i3 |= startRestartGroup.changed(f9) ? 256 : 128;
                i5 = i2 & 8;
                if (i5 != 0) {
                    i3 |= 3072;
                } else if ((i & 3072) == 0) {
                    f10 = f3;
                    i3 |= startRestartGroup.changed(f10) ? 2048 : 1024;
                    i6 = i2 & 16;
                    if (i6 == 0) {
                        i3 |= 24576;
                    } else if ((i & 24576) == 0) {
                        f11 = f4;
                        i3 |= startRestartGroup.changed(f11) ? 16384 : 8192;
                        i7 = i2 & 32;
                        if (i7 != 0) {
                            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            i3 |= startRestartGroup.changed(f5) ? 131072 : 65536;
                        }
                        i8 = i2 & 64;
                        if (i8 != 0) {
                            i3 |= 1572864;
                        } else if ((i & 1572864) == 0) {
                            i3 |= startRestartGroup.changed(f6) ? 1048576 : 524288;
                        }
                        i9 = i2 & 128;
                        if (i9 != 0) {
                            i3 |= 12582912;
                        } else if ((i & 12582912) == 0) {
                            i10 = i9;
                            i3 |= startRestartGroup.changed(f7) ? 8388608 : 4194304;
                            if ((i & 100663296) == 0) {
                                if ((i2 & 256) == 0 && startRestartGroup.changedInstance(list)) {
                                    i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                    i3 |= i11;
                                }
                                i11 = 33554432;
                                i3 |= i11;
                            }
                            if ((i2 & 512) == 0) {
                                i3 |= 805306368;
                            } else if ((i & 805306368) == 0) {
                                i3 |= startRestartGroup.changedInstance(function2) ? 536870912 : 268435456;
                            }
                            if (!startRestartGroup.shouldExecute((306783379 & i3) == 306783378, i3 & 1)) {
                                startRestartGroup.startDefaults();
                                if ((i & 1) == 0 || startRestartGroup.getDefaultsInvalid()) {
                                    if (i12 != 0) {
                                        str2 = "";
                                    }
                                    if (i13 != 0) {
                                        f8 = 0.0f;
                                    }
                                    if (i4 != 0) {
                                        f9 = 0.0f;
                                    }
                                    if (i5 != 0) {
                                        f10 = 0.0f;
                                    }
                                    if (i6 != 0) {
                                        f11 = 1.0f;
                                    }
                                    f16 = i7 == 0 ? f5 : 1.0f;
                                    f12 = i8 != 0 ? 0.0f : f6;
                                    f17 = i10 == 0 ? f7 : 0.0f;
                                    if ((i2 & 256) != 0) {
                                        list2 = VectorKt.getEmptyPath();
                                        i3 &= -234881025;
                                        startRestartGroup.endDefaults();
                                        if (ComposerKt.isTraceInProgress()) {
                                            f18 = f17;
                                        } else {
                                            f18 = f17;
                                            ComposerKt.traceEventStart(-1851426205, i3, -1, "androidx.compose.ui.graphics.vector.Group (VectorCompose.kt:57)");
                                        }
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1104105737, "CC(remember):VectorCompose.kt#9igjgp");
                                        rememberedValue = startRestartGroup.rememberedValue();
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = new Function0<GroupComponent>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$1$1
                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final GroupComponent invoke() {
                                                    return new GroupComponent();
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        Function0 function0 = (Function0) rememberedValue;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1291542257, "CC(ComposeNode)N(factory,update,content)361@14118L9:Composables.kt#9igjgp");
                                        if (!(startRestartGroup.getApplier() instanceof VectorApplier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        startRestartGroup.startNode();
                                        if (!startRestartGroup.getInserting()) {
                                            startRestartGroup.createNode(function0);
                                        } else {
                                            startRestartGroup.useNode();
                                        }
                                        Composer m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                                        Updater.m3912setimpl(m3905constructorimpl, str2, new Function2<GroupComponent, String, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$1
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, String str3) {
                                                invoke2(groupComponent, str3);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(GroupComponent groupComponent, String str3) {
                                                groupComponent.setName(str3);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f8), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$2
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                                invoke(groupComponent, f19.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(GroupComponent groupComponent, float f19) {
                                                groupComponent.setRotation(f19);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f9), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$3
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                                invoke(groupComponent, f19.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(GroupComponent groupComponent, float f19) {
                                                groupComponent.setPivotX(f19);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f10), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$4
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                                invoke(groupComponent, f19.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(GroupComponent groupComponent, float f19) {
                                                groupComponent.setPivotY(f19);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f11), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$5
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                                invoke(groupComponent, f19.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(GroupComponent groupComponent, float f19) {
                                                groupComponent.setScaleX(f19);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f16), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$6
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                                invoke(groupComponent, f19.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(GroupComponent groupComponent, float f19) {
                                                groupComponent.setScaleY(f19);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f12), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$7
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                                invoke(groupComponent, f19.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(GroupComponent groupComponent, float f19) {
                                                groupComponent.setTranslationX(f19);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f18), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$8
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                                invoke(groupComponent, f19.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(GroupComponent groupComponent, float f19) {
                                                groupComponent.setTranslationY(f19);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, list2, new Function2<GroupComponent, List<? extends PathNode>, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$9
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, List<? extends PathNode> list3) {
                                                invoke2(groupComponent, list3);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(GroupComponent groupComponent, List<? extends PathNode> list3) {
                                                groupComponent.setClipPathData(list3);
                                            }
                                        });
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1167503970, "C72@3072L9:VectorCompose.kt#huu6hf");
                                        function2.invoke(startRestartGroup, Integer.valueOf((i3 >> 27) & 14));
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        f13 = f18;
                                        f14 = f8;
                                        f15 = f16;
                                    }
                                } else {
                                    startRestartGroup.skipToGroupEnd();
                                    if ((i2 & 256) != 0) {
                                        i3 &= -234881025;
                                    }
                                    f16 = f5;
                                    f12 = f6;
                                    f17 = f7;
                                }
                                list2 = list;
                                startRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1104105737, "CC(remember):VectorCompose.kt#9igjgp");
                                rememberedValue = startRestartGroup.rememberedValue();
                                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                }
                                Function0 function02 = (Function0) rememberedValue;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1291542257, "CC(ComposeNode)N(factory,update,content)361@14118L9:Composables.kt#9igjgp");
                                if (!(startRestartGroup.getApplier() instanceof VectorApplier)) {
                                }
                                startRestartGroup.startNode();
                                if (!startRestartGroup.getInserting()) {
                                }
                                Composer m3905constructorimpl2 = Updater.m3905constructorimpl(startRestartGroup);
                                Updater.m3912setimpl(m3905constructorimpl2, str2, new Function2<GroupComponent, String, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$1
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, String str3) {
                                        invoke2(groupComponent, str3);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(GroupComponent groupComponent, String str3) {
                                        groupComponent.setName(str3);
                                    }
                                });
                                Updater.m3912setimpl(m3905constructorimpl2, Float.valueOf(f8), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$2
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                        invoke(groupComponent, f19.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(GroupComponent groupComponent, float f19) {
                                        groupComponent.setRotation(f19);
                                    }
                                });
                                Updater.m3912setimpl(m3905constructorimpl2, Float.valueOf(f9), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$3
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                        invoke(groupComponent, f19.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(GroupComponent groupComponent, float f19) {
                                        groupComponent.setPivotX(f19);
                                    }
                                });
                                Updater.m3912setimpl(m3905constructorimpl2, Float.valueOf(f10), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$4
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                        invoke(groupComponent, f19.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(GroupComponent groupComponent, float f19) {
                                        groupComponent.setPivotY(f19);
                                    }
                                });
                                Updater.m3912setimpl(m3905constructorimpl2, Float.valueOf(f11), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$5
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                        invoke(groupComponent, f19.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(GroupComponent groupComponent, float f19) {
                                        groupComponent.setScaleX(f19);
                                    }
                                });
                                Updater.m3912setimpl(m3905constructorimpl2, Float.valueOf(f16), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$6
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                        invoke(groupComponent, f19.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(GroupComponent groupComponent, float f19) {
                                        groupComponent.setScaleY(f19);
                                    }
                                });
                                Updater.m3912setimpl(m3905constructorimpl2, Float.valueOf(f12), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$7
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                        invoke(groupComponent, f19.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(GroupComponent groupComponent, float f19) {
                                        groupComponent.setTranslationX(f19);
                                    }
                                });
                                Updater.m3912setimpl(m3905constructorimpl2, Float.valueOf(f18), new Function2<GroupComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$8
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, Float f19) {
                                        invoke(groupComponent, f19.floatValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(GroupComponent groupComponent, float f19) {
                                        groupComponent.setTranslationY(f19);
                                    }
                                });
                                Updater.m3912setimpl(m3905constructorimpl2, list2, new Function2<GroupComponent, List<? extends PathNode>, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$2$9
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(GroupComponent groupComponent, List<? extends PathNode> list3) {
                                        invoke2(groupComponent, list3);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(GroupComponent groupComponent, List<? extends PathNode> list3) {
                                        groupComponent.setClipPathData(list3);
                                    }
                                });
                                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1167503970, "C72@3072L9:VectorCompose.kt#huu6hf");
                                function2.invoke(startRestartGroup, Integer.valueOf((i3 >> 27) & 14));
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                startRestartGroup.endNode();
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                f13 = f18;
                                f14 = f8;
                                f15 = f16;
                            } else {
                                startRestartGroup.skipToGroupEnd();
                                f12 = f6;
                                f13 = f7;
                                list2 = list;
                                f14 = f8;
                                f15 = f5;
                            }
                            final float f19 = f10;
                            final float f20 = f11;
                            endRestartGroup = startRestartGroup.endRestartGroup();
                            if (endRestartGroup == null) {
                                final String str3 = str2;
                                final float f21 = f9;
                                final float f22 = f13;
                                endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$4
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                                        invoke(composer2, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer2, int i14) {
                                        VectorComposeKt.Group(str3, f14, f21, f19, f20, f15, f12, f22, list2, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        i10 = i9;
                        if ((i & 100663296) == 0) {
                        }
                        if ((i2 & 512) == 0) {
                        }
                        if (!startRestartGroup.shouldExecute((306783379 & i3) == 306783378, i3 & 1)) {
                        }
                        final float f192 = f10;
                        final float f202 = f11;
                        endRestartGroup = startRestartGroup.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    f11 = f4;
                    i7 = i2 & 32;
                    if (i7 != 0) {
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                    }
                    i9 = i2 & 128;
                    if (i9 != 0) {
                    }
                    i10 = i9;
                    if ((i & 100663296) == 0) {
                    }
                    if ((i2 & 512) == 0) {
                    }
                    if (!startRestartGroup.shouldExecute((306783379 & i3) == 306783378, i3 & 1)) {
                    }
                    final float f1922 = f10;
                    final float f2022 = f11;
                    endRestartGroup = startRestartGroup.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                f10 = f3;
                i6 = i2 & 16;
                if (i6 == 0) {
                }
                f11 = f4;
                i7 = i2 & 32;
                if (i7 != 0) {
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                }
                i9 = i2 & 128;
                if (i9 != 0) {
                }
                i10 = i9;
                if ((i & 100663296) == 0) {
                }
                if ((i2 & 512) == 0) {
                }
                if (!startRestartGroup.shouldExecute((306783379 & i3) == 306783378, i3 & 1)) {
                }
                final float f19222 = f10;
                final float f20222 = f11;
                endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            f9 = f2;
            i5 = i2 & 8;
            if (i5 != 0) {
            }
            f10 = f3;
            i6 = i2 & 16;
            if (i6 == 0) {
            }
            f11 = f4;
            i7 = i2 & 32;
            if (i7 != 0) {
            }
            i8 = i2 & 64;
            if (i8 != 0) {
            }
            i9 = i2 & 128;
            if (i9 != 0) {
            }
            i10 = i9;
            if ((i & 100663296) == 0) {
            }
            if ((i2 & 512) == 0) {
            }
            if (!startRestartGroup.shouldExecute((306783379 & i3) == 306783378, i3 & 1)) {
            }
            final float f192222 = f10;
            final float f202222 = f11;
            endRestartGroup = startRestartGroup.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        f8 = f;
        i4 = i2 & 4;
        if (i4 == 0) {
        }
        f9 = f2;
        i5 = i2 & 8;
        if (i5 != 0) {
        }
        f10 = f3;
        i6 = i2 & 16;
        if (i6 == 0) {
        }
        f11 = f4;
        i7 = i2 & 32;
        if (i7 != 0) {
        }
        i8 = i2 & 64;
        if (i8 != 0) {
        }
        i9 = i2 & 128;
        if (i9 != 0) {
        }
        i10 = i9;
        if ((i & 100663296) == 0) {
        }
        if ((i2 & 512) == 0) {
        }
        if (!startRestartGroup.shouldExecute((306783379 & i3) == 306783378, i3 & 1)) {
        }
        final float f1922222 = f10;
        final float f2022222 = f11;
        endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01ea  */
    /* renamed from: Path-9cdaXJ4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m5379Path9cdaXJ4(final List<? extends PathNode> list, int i, String str, Brush brush, float f, Brush brush2, float f2, float f3, int i2, int i3, float f4, float f5, float f6, float f7, Composer composer, final int i4, final int i5, final int i6) {
        int i7;
        int i8;
        int i9;
        int i10;
        Brush brush3;
        int i11;
        float f8;
        int i12;
        Brush brush4;
        int i13;
        float f9;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        final String str2;
        final float f10;
        final float f11;
        final float f12;
        Composer composer2;
        final Brush brush5;
        final float f13;
        final Brush brush6;
        final int i27;
        final float f14;
        final int i28;
        final int i29;
        final float f15;
        final float f16;
        ScopeUpdateScope endRestartGroup;
        int i30;
        Composer startRestartGroup = composer.startRestartGroup(-1478270750);
        ComposerKt.sourceInformation(startRestartGroup, "C(Path)P(3,4:c#ui.graphics.PathFillType,2!4,10,7:c#ui.graphics.StrokeCap,8:c#ui.graphics.StrokeJoin!1,13)116@5136L19,115@5075L877:VectorCompose.kt#huu6hf");
        if ((i6 & 1) != 0) {
            i7 = i4 | 6;
        } else if ((i4 & 6) == 0) {
            i7 = (startRestartGroup.changedInstance(list) ? 4 : 2) | i4;
        } else {
            i7 = i4;
        }
        int i31 = i6 & 2;
        if (i31 != 0) {
            i7 |= 48;
        } else if ((i4 & 48) == 0) {
            i8 = i;
            i7 |= startRestartGroup.changed(i8) ? 32 : 16;
            i9 = i6 & 4;
            if (i9 == 0) {
                i7 |= 384;
            } else if ((i4 & 384) == 0) {
                i7 |= startRestartGroup.changed(str) ? 256 : 128;
                i10 = i6 & 8;
                if (i10 != 0) {
                    i7 |= 3072;
                } else if ((i4 & 3072) == 0) {
                    brush3 = brush;
                    i7 |= startRestartGroup.changed(brush3) ? 2048 : 1024;
                    i11 = i6 & 16;
                    if (i11 == 0) {
                        i7 |= 24576;
                    } else if ((i4 & 24576) == 0) {
                        f8 = f;
                        i7 |= startRestartGroup.changed(f8) ? 16384 : 8192;
                        i12 = i6 & 32;
                        if (i12 != 0) {
                            i7 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            brush4 = brush2;
                        } else {
                            brush4 = brush2;
                            if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                i7 |= startRestartGroup.changed(brush4) ? 131072 : 65536;
                            }
                        }
                        i13 = i6 & 64;
                        if (i13 != 0) {
                            i7 |= 1572864;
                            f9 = f2;
                        } else {
                            f9 = f2;
                            if ((i4 & 1572864) == 0) {
                                i7 |= startRestartGroup.changed(f9) ? 1048576 : 524288;
                            }
                        }
                        i14 = i6 & 128;
                        if (i14 != 0) {
                            i7 |= 12582912;
                        } else if ((i4 & 12582912) == 0) {
                            i7 |= startRestartGroup.changed(f3) ? 8388608 : 4194304;
                        }
                        i15 = i6 & 256;
                        if (i15 != 0) {
                            i7 |= 100663296;
                        } else if ((i4 & 100663296) == 0) {
                            i16 = i15;
                            i7 |= startRestartGroup.changed(i2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                            i17 = i6 & 512;
                            if (i17 == 0) {
                                i7 |= 805306368;
                            } else if ((i4 & 805306368) == 0) {
                                i18 = i17;
                                i7 |= startRestartGroup.changed(i3) ? 536870912 : 268435456;
                                i19 = i6 & 1024;
                                if (i19 != 0) {
                                    i21 = i5 | 6;
                                    i20 = i19;
                                } else if ((i5 & 6) == 0) {
                                    i20 = i19;
                                    i21 = i5 | (startRestartGroup.changed(f4) ? 4 : 2);
                                } else {
                                    i20 = i19;
                                    i21 = i5;
                                }
                                i22 = i6 & 2048;
                                if (i22 != 0) {
                                    i21 |= 48;
                                    i23 = i22;
                                } else if ((i5 & 48) == 0) {
                                    i23 = i22;
                                    i21 |= startRestartGroup.changed(f5) ? 32 : 16;
                                } else {
                                    i23 = i22;
                                }
                                int i32 = i21;
                                i24 = i6 & 4096;
                                if (i24 != 0) {
                                    i25 = i32 | 384;
                                } else {
                                    int i33 = i32;
                                    if ((i5 & 384) == 0) {
                                        i33 |= startRestartGroup.changed(f6) ? 256 : 128;
                                    }
                                    i25 = i33;
                                }
                                i26 = i6 & 8192;
                                if (i26 != 0) {
                                    i25 |= 3072;
                                } else if ((i5 & 3072) == 0) {
                                    i25 |= startRestartGroup.changed(f7) ? 2048 : 1024;
                                    if (startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i25 & 1171) != 1170, i7 & 1)) {
                                        startRestartGroup.skipToGroupEnd();
                                        str2 = str;
                                        f10 = f3;
                                        f11 = f5;
                                        f12 = f7;
                                        composer2 = startRestartGroup;
                                        brush5 = brush3;
                                        f13 = f8;
                                        brush6 = brush4;
                                        i27 = i8;
                                        f14 = f9;
                                        i28 = i2;
                                        i29 = i3;
                                        f15 = f4;
                                        f16 = f6;
                                    } else {
                                        int defaultFillType = i31 != 0 ? VectorKt.getDefaultFillType() : i8;
                                        String str3 = i9 != 0 ? "" : str;
                                        if (i10 != 0) {
                                            brush3 = null;
                                        }
                                        if (i11 != 0) {
                                            f8 = 1.0f;
                                        }
                                        if (i12 != 0) {
                                            brush4 = null;
                                        }
                                        if (i13 != 0) {
                                            f9 = 1.0f;
                                        }
                                        float f17 = i14 != 0 ? 0.0f : f3;
                                        int defaultStrokeLineCap = i16 != 0 ? VectorKt.getDefaultStrokeLineCap() : i2;
                                        int defaultStrokeLineJoin = i18 != 0 ? VectorKt.getDefaultStrokeLineJoin() : i3;
                                        float f18 = i20 != 0 ? 4.0f : f4;
                                        float f19 = i23 != 0 ? 0.0f : f5;
                                        float f20 = i24 == 0 ? f6 : 1.0f;
                                        float f21 = i26 == 0 ? f7 : 0.0f;
                                        if (ComposerKt.isTraceInProgress()) {
                                            i30 = defaultFillType;
                                            ComposerKt.traceEventStart(-1478270750, i7, i25, "androidx.compose.ui.graphics.vector.Path (VectorCompose.kt:114)");
                                        } else {
                                            i30 = defaultFillType;
                                        }
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1016006549, "CC(remember):VectorCompose.kt#9igjgp");
                                        VectorComposeKt$Path$1$1 rememberedValue = startRestartGroup.rememberedValue();
                                        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                                            rememberedValue = new Function0<PathComponent>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$1$1
                                                /* JADX WARN: Can't rename method to resolve collision */
                                                @Override // kotlin.jvm.functions.Function0
                                                public final PathComponent invoke() {
                                                    return new PathComponent();
                                                }
                                            };
                                            startRestartGroup.updateRememberedValue(rememberedValue);
                                        }
                                        Function0 function0 = (Function0) rememberedValue;
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1886828752, "CC(ComposeNode)N(factory,update):Composables.kt#9igjgp");
                                        if (!(startRestartGroup.getApplier() instanceof VectorApplier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        startRestartGroup.startNode();
                                        if (startRestartGroup.getInserting()) {
                                            startRestartGroup.createNode(function0);
                                        } else {
                                            startRestartGroup.useNode();
                                        }
                                        Composer m3905constructorimpl = Updater.m3905constructorimpl(startRestartGroup);
                                        Updater.m3912setimpl(m3905constructorimpl, str3, new Function2<PathComponent, String, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$1
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, String str4) {
                                                invoke2(pathComponent, str4);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(PathComponent pathComponent, String str4) {
                                                pathComponent.setName(str4);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, list, new Function2<PathComponent, List<? extends PathNode>, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$2
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, List<? extends PathNode> list2) {
                                                invoke2(pathComponent, list2);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(PathComponent pathComponent, List<? extends PathNode> list2) {
                                                pathComponent.setPathData(list2);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, PathFillType.m4912boximpl(i30), new Function2<PathComponent, PathFillType, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$3
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, PathFillType pathFillType) {
                                                m5381invokepweu1eQ(pathComponent, pathFillType.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke-pweu1eQ, reason: not valid java name */
                                            public final void m5381invokepweu1eQ(PathComponent pathComponent, int i34) {
                                                pathComponent.m5373setPathFillTypeoQ8Xj4U(i34);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, brush3, new Function2<PathComponent, Brush, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$4
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Brush brush7) {
                                                invoke2(pathComponent, brush7);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(PathComponent pathComponent, Brush brush7) {
                                                pathComponent.setFill(brush7);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f8), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$5
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f22) {
                                                invoke(pathComponent, f22.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(PathComponent pathComponent, float f22) {
                                                pathComponent.setFillAlpha(f22);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, brush4, new Function2<PathComponent, Brush, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$6
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Brush brush7) {
                                                invoke2(pathComponent, brush7);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(PathComponent pathComponent, Brush brush7) {
                                                pathComponent.setStroke(brush7);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f9), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$7
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f22) {
                                                invoke(pathComponent, f22.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(PathComponent pathComponent, float f22) {
                                                pathComponent.setStrokeAlpha(f22);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f17), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$8
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f22) {
                                                invoke(pathComponent, f22.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(PathComponent pathComponent, float f22) {
                                                pathComponent.setStrokeLineWidth(f22);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, StrokeJoin.m4998boximpl(defaultStrokeLineJoin), new Function2<PathComponent, StrokeJoin, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$9
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, StrokeJoin strokeJoin) {
                                                m5382invokekLtJ_vA(pathComponent, strokeJoin.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke-kLtJ_vA, reason: not valid java name */
                                            public final void m5382invokekLtJ_vA(PathComponent pathComponent, int i34) {
                                                pathComponent.m5375setStrokeLineJoinWw9F2mQ(i34);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, StrokeCap.m4988boximpl(defaultStrokeLineCap), new Function2<PathComponent, StrokeCap, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$10
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, StrokeCap strokeCap) {
                                                m5380invokeCSYIeUk(pathComponent, strokeCap.getValue());
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke-CSYIeUk, reason: not valid java name */
                                            public final void m5380invokeCSYIeUk(PathComponent pathComponent, int i34) {
                                                pathComponent.m5374setStrokeLineCapBeK7IIE(i34);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f18), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$11
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f22) {
                                                invoke(pathComponent, f22.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(PathComponent pathComponent, float f22) {
                                                pathComponent.setStrokeLineMiter(f22);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f19), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$12
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f22) {
                                                invoke(pathComponent, f22.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(PathComponent pathComponent, float f22) {
                                                pathComponent.setTrimPathStart(f22);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f20), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$13
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f22) {
                                                invoke(pathComponent, f22.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(PathComponent pathComponent, float f22) {
                                                pathComponent.setTrimPathEnd(f22);
                                            }
                                        });
                                        Updater.m3912setimpl(m3905constructorimpl, Float.valueOf(f21), new Function2<PathComponent, Float, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$2$14
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(PathComponent pathComponent, Float f22) {
                                                invoke(pathComponent, f22.floatValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(PathComponent pathComponent, float f22) {
                                                pathComponent.setTrimPathOffset(f22);
                                            }
                                        });
                                        startRestartGroup.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        str2 = str3;
                                        composer2 = startRestartGroup;
                                        brush5 = brush3;
                                        f13 = f8;
                                        brush6 = brush4;
                                        f14 = f9;
                                        i28 = defaultStrokeLineCap;
                                        i29 = defaultStrokeLineJoin;
                                        i27 = i30;
                                        f16 = f20;
                                        f12 = f21;
                                        f10 = f17;
                                        f15 = f18;
                                        f11 = f19;
                                    }
                                    endRestartGroup = composer2.endRestartGroup();
                                    if (endRestartGroup == null) {
                                        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.graphics.vector.VectorComposeKt$Path$3
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(2);
                                            }

                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                invoke(composer3, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer composer3, int i34) {
                                                VectorComposeKt.m5379Path9cdaXJ4(list, i27, str2, brush5, f13, brush6, f14, f10, i28, i29, f15, f11, f16, f12, composer3, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                if (startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i25 & 1171) != 1170, i7 & 1)) {
                                }
                                endRestartGroup = composer2.endRestartGroup();
                                if (endRestartGroup == null) {
                                }
                            }
                            i18 = i17;
                            i19 = i6 & 1024;
                            if (i19 != 0) {
                            }
                            i22 = i6 & 2048;
                            if (i22 != 0) {
                            }
                            int i322 = i21;
                            i24 = i6 & 4096;
                            if (i24 != 0) {
                            }
                            i26 = i6 & 8192;
                            if (i26 != 0) {
                            }
                            if (startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i25 & 1171) != 1170, i7 & 1)) {
                            }
                            endRestartGroup = composer2.endRestartGroup();
                            if (endRestartGroup == null) {
                            }
                        }
                        i16 = i15;
                        i17 = i6 & 512;
                        if (i17 == 0) {
                        }
                        i18 = i17;
                        i19 = i6 & 1024;
                        if (i19 != 0) {
                        }
                        i22 = i6 & 2048;
                        if (i22 != 0) {
                        }
                        int i3222 = i21;
                        i24 = i6 & 4096;
                        if (i24 != 0) {
                        }
                        i26 = i6 & 8192;
                        if (i26 != 0) {
                        }
                        if (startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i25 & 1171) != 1170, i7 & 1)) {
                        }
                        endRestartGroup = composer2.endRestartGroup();
                        if (endRestartGroup == null) {
                        }
                    }
                    f8 = f;
                    i12 = i6 & 32;
                    if (i12 != 0) {
                    }
                    i13 = i6 & 64;
                    if (i13 != 0) {
                    }
                    i14 = i6 & 128;
                    if (i14 != 0) {
                    }
                    i15 = i6 & 256;
                    if (i15 != 0) {
                    }
                    i16 = i15;
                    i17 = i6 & 512;
                    if (i17 == 0) {
                    }
                    i18 = i17;
                    i19 = i6 & 1024;
                    if (i19 != 0) {
                    }
                    i22 = i6 & 2048;
                    if (i22 != 0) {
                    }
                    int i32222 = i21;
                    i24 = i6 & 4096;
                    if (i24 != 0) {
                    }
                    i26 = i6 & 8192;
                    if (i26 != 0) {
                    }
                    if (startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i25 & 1171) != 1170, i7 & 1)) {
                    }
                    endRestartGroup = composer2.endRestartGroup();
                    if (endRestartGroup == null) {
                    }
                }
                brush3 = brush;
                i11 = i6 & 16;
                if (i11 == 0) {
                }
                f8 = f;
                i12 = i6 & 32;
                if (i12 != 0) {
                }
                i13 = i6 & 64;
                if (i13 != 0) {
                }
                i14 = i6 & 128;
                if (i14 != 0) {
                }
                i15 = i6 & 256;
                if (i15 != 0) {
                }
                i16 = i15;
                i17 = i6 & 512;
                if (i17 == 0) {
                }
                i18 = i17;
                i19 = i6 & 1024;
                if (i19 != 0) {
                }
                i22 = i6 & 2048;
                if (i22 != 0) {
                }
                int i322222 = i21;
                i24 = i6 & 4096;
                if (i24 != 0) {
                }
                i26 = i6 & 8192;
                if (i26 != 0) {
                }
                if (startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i25 & 1171) != 1170, i7 & 1)) {
                }
                endRestartGroup = composer2.endRestartGroup();
                if (endRestartGroup == null) {
                }
            }
            i10 = i6 & 8;
            if (i10 != 0) {
            }
            brush3 = brush;
            i11 = i6 & 16;
            if (i11 == 0) {
            }
            f8 = f;
            i12 = i6 & 32;
            if (i12 != 0) {
            }
            i13 = i6 & 64;
            if (i13 != 0) {
            }
            i14 = i6 & 128;
            if (i14 != 0) {
            }
            i15 = i6 & 256;
            if (i15 != 0) {
            }
            i16 = i15;
            i17 = i6 & 512;
            if (i17 == 0) {
            }
            i18 = i17;
            i19 = i6 & 1024;
            if (i19 != 0) {
            }
            i22 = i6 & 2048;
            if (i22 != 0) {
            }
            int i3222222 = i21;
            i24 = i6 & 4096;
            if (i24 != 0) {
            }
            i26 = i6 & 8192;
            if (i26 != 0) {
            }
            if (startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i25 & 1171) != 1170, i7 & 1)) {
            }
            endRestartGroup = composer2.endRestartGroup();
            if (endRestartGroup == null) {
            }
        }
        i8 = i;
        i9 = i6 & 4;
        if (i9 == 0) {
        }
        i10 = i6 & 8;
        if (i10 != 0) {
        }
        brush3 = brush;
        i11 = i6 & 16;
        if (i11 == 0) {
        }
        f8 = f;
        i12 = i6 & 32;
        if (i12 != 0) {
        }
        i13 = i6 & 64;
        if (i13 != 0) {
        }
        i14 = i6 & 128;
        if (i14 != 0) {
        }
        i15 = i6 & 256;
        if (i15 != 0) {
        }
        i16 = i15;
        i17 = i6 & 512;
        if (i17 == 0) {
        }
        i18 = i17;
        i19 = i6 & 1024;
        if (i19 != 0) {
        }
        i22 = i6 & 2048;
        if (i22 != 0) {
        }
        int i32222222 = i21;
        i24 = i6 & 4096;
        if (i24 != 0) {
        }
        i26 = i6 & 8192;
        if (i26 != 0) {
        }
        if (startRestartGroup.shouldExecute((i7 & 306783379) == 306783378 || (i25 & 1171) != 1170, i7 & 1)) {
        }
        endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }
}
