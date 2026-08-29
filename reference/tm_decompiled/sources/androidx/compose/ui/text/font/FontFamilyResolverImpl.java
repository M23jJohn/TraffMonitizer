package androidx.compose.ui.text.font;

import androidx.compose.runtime.State;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.TypefaceResult;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: FontFamilyResolver.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0096@¢\u0006\u0002\u0010\u0018J7\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u001a2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u001a2\u0006\u0010#\u001a\u00020\u0012H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/compose/ui/text/font/FontFamilyResolverImpl;", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "platformFontLoader", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "platformResolveInterceptor", "Landroidx/compose/ui/text/font/PlatformResolveInterceptor;", "typefaceRequestCache", "Landroidx/compose/ui/text/font/TypefaceRequestCache;", "fontListFontFamilyTypefaceAdapter", "Landroidx/compose/ui/text/font/FontListFontFamilyTypefaceAdapter;", "platformFamilyTypefaceAdapter", "Landroidx/compose/ui/text/font/PlatformFontFamilyTypefaceAdapter;", "<init>", "(Landroidx/compose/ui/text/font/PlatformFontLoader;Landroidx/compose/ui/text/font/PlatformResolveInterceptor;Landroidx/compose/ui/text/font/TypefaceRequestCache;Landroidx/compose/ui/text/font/FontListFontFamilyTypefaceAdapter;Landroidx/compose/ui/text/font/PlatformFontFamilyTypefaceAdapter;)V", "getPlatformFontLoader$ui_text", "()Landroidx/compose/ui/text/font/PlatformFontLoader;", "createDefaultTypeface", "Lkotlin/Function1;", "Landroidx/compose/ui/text/font/TypefaceRequest;", "", "preload", "", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "(Landroidx/compose/ui/text/font/FontFamily;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolve", "Landroidx/compose/runtime/State;", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "fontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "resolve-DPcqOEQ", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/font/FontWeight;II)Landroidx/compose/runtime/State;", "typefaceRequest", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FontFamilyResolverImpl implements FontFamily.Resolver {
    public static final int $stable = 8;
    private final Function1<TypefaceRequest, Object> createDefaultTypeface;
    private final FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter;
    private final PlatformFontFamilyTypefaceAdapter platformFamilyTypefaceAdapter;
    private final PlatformFontLoader platformFontLoader;
    private final PlatformResolveInterceptor platformResolveInterceptor;
    private final TypefaceRequestCache typefaceRequestCache;

    public FontFamilyResolverImpl(PlatformFontLoader platformFontLoader, PlatformResolveInterceptor platformResolveInterceptor, TypefaceRequestCache typefaceRequestCache, FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter, PlatformFontFamilyTypefaceAdapter platformFontFamilyTypefaceAdapter) {
        this.platformFontLoader = platformFontLoader;
        this.platformResolveInterceptor = platformResolveInterceptor;
        this.typefaceRequestCache = typefaceRequestCache;
        this.fontListFontFamilyTypefaceAdapter = fontListFontFamilyTypefaceAdapter;
        this.platformFamilyTypefaceAdapter = platformFontFamilyTypefaceAdapter;
        this.createDefaultTypeface = new Function1() { // from class: androidx.compose.ui.text.font.FontFamilyResolverImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Object createDefaultTypeface$lambda$0;
                createDefaultTypeface$lambda$0 = FontFamilyResolverImpl.createDefaultTypeface$lambda$0(FontFamilyResolverImpl.this, (TypefaceRequest) obj);
                return createDefaultTypeface$lambda$0;
            }
        };
    }

    /* renamed from: getPlatformFontLoader$ui_text, reason: from getter */
    public final PlatformFontLoader getPlatformFontLoader() {
        return this.platformFontLoader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ FontFamilyResolverImpl(PlatformFontLoader platformFontLoader, PlatformResolveInterceptor platformResolveInterceptor, TypefaceRequestCache typefaceRequestCache, FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter, PlatformFontFamilyTypefaceAdapter platformFontFamilyTypefaceAdapter, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(platformFontLoader, (i & 2) != 0 ? PlatformResolveInterceptor.INSTANCE.getDefault$ui_text() : platformResolveInterceptor, (i & 4) != 0 ? FontFamilyResolverKt.getGlobalTypefaceRequestCache() : typefaceRequestCache, (i & 8) != 0 ? new FontListFontFamilyTypefaceAdapter(FontFamilyResolverKt.getGlobalAsyncTypefaceCache(), null, 2, 0 == true ? 1 : 0) : fontListFontFamilyTypefaceAdapter, (i & 16) != 0 ? new PlatformFontFamilyTypefaceAdapter() : platformFontFamilyTypefaceAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object createDefaultTypeface$lambda$0(FontFamilyResolverImpl fontFamilyResolverImpl, TypefaceRequest typefaceRequest) {
        return fontFamilyResolverImpl.resolve(TypefaceRequest.m7006copye1PVR60$default(typefaceRequest, null, null, 0, 0, null, 30, null)).getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0069 A[LOOP:0: B:11:0x0067->B:12:0x0069, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // androidx.compose.ui.text.font.FontFamily.Resolver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object preload(FontFamily fontFamily, Continuation<? super Unit> continuation) {
        FontFamilyResolverImpl$preload$1 fontFamilyResolverImpl$preload$1;
        int i;
        int size;
        int i2;
        if (continuation instanceof FontFamilyResolverImpl$preload$1) {
            fontFamilyResolverImpl$preload$1 = (FontFamilyResolverImpl$preload$1) continuation;
            if ((fontFamilyResolverImpl$preload$1.label & Integer.MIN_VALUE) != 0) {
                fontFamilyResolverImpl$preload$1.label -= Integer.MIN_VALUE;
                Object obj = fontFamilyResolverImpl$preload$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = fontFamilyResolverImpl$preload$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!(fontFamily instanceof FontListFontFamily)) {
                        return Unit.INSTANCE;
                    }
                    FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter = this.fontListFontFamilyTypefaceAdapter;
                    PlatformFontLoader platformFontLoader = this.platformFontLoader;
                    fontFamilyResolverImpl$preload$1.L$0 = fontFamily;
                    fontFamilyResolverImpl$preload$1.label = 1;
                    if (fontListFontFamilyTypefaceAdapter.preload(fontFamily, platformFontLoader, fontFamilyResolverImpl$preload$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    fontFamily = (FontFamily) fontFamilyResolverImpl$preload$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                List<Font> fonts = ((FontListFontFamily) fontFamily).getFonts();
                ArrayList arrayList = new ArrayList(fonts.size());
                size = fonts.size();
                for (i2 = 0; i2 < size; i2++) {
                    Font font = fonts.get(i2);
                    arrayList.add(new TypefaceRequest(this.platformResolveInterceptor.interceptFontFamily(fontFamily), this.platformResolveInterceptor.interceptFontWeight(font.getWeight()), this.platformResolveInterceptor.mo6912interceptFontStyleT2F_aPo(font.getStyle()), FontSynthesis.INSTANCE.m6981getAllGVVA2EU(), this.platformFontLoader.getCacheKey(), null));
                }
                this.typefaceRequestCache.preWarmCache(arrayList, new Function1() { // from class: androidx.compose.ui.text.font.FontFamilyResolverImpl$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        TypefaceResult preload$lambda$4;
                        preload$lambda$4 = FontFamilyResolverImpl.preload$lambda$4(FontFamilyResolverImpl.this, (TypefaceRequest) obj2);
                        return preload$lambda$4;
                    }
                });
                return Unit.INSTANCE;
            }
        }
        fontFamilyResolverImpl$preload$1 = new FontFamilyResolverImpl$preload$1(this, continuation);
        Object obj2 = fontFamilyResolverImpl$preload$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = fontFamilyResolverImpl$preload$1.label;
        if (i != 0) {
        }
        List<Font> fonts2 = ((FontListFontFamily) fontFamily).getFonts();
        ArrayList arrayList2 = new ArrayList(fonts2.size());
        size = fonts2.size();
        while (i2 < size) {
        }
        this.typefaceRequestCache.preWarmCache(arrayList2, new Function1() { // from class: androidx.compose.ui.text.font.FontFamilyResolverImpl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj22) {
                TypefaceResult preload$lambda$4;
                preload$lambda$4 = FontFamilyResolverImpl.preload$lambda$4(FontFamilyResolverImpl.this, (TypefaceRequest) obj22);
                return preload$lambda$4;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TypefaceResult preload$lambda$4(FontFamilyResolverImpl fontFamilyResolverImpl, TypefaceRequest typefaceRequest) {
        TypefaceResult resolve = fontFamilyResolverImpl.fontListFontFamilyTypefaceAdapter.resolve(typefaceRequest, fontFamilyResolverImpl.platformFontLoader, new Function1() { // from class: androidx.compose.ui.text.font.FontFamilyResolverImpl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit preload$lambda$4$lambda$2;
                preload$lambda$4$lambda$2 = FontFamilyResolverImpl.preload$lambda$4$lambda$2((TypefaceResult.Immutable) obj);
                return preload$lambda$4$lambda$2;
            }
        }, fontFamilyResolverImpl.createDefaultTypeface);
        if (resolve != null) {
            return resolve;
        }
        TypefaceResult resolve2 = fontFamilyResolverImpl.platformFamilyTypefaceAdapter.resolve(typefaceRequest, fontFamilyResolverImpl.platformFontLoader, new Function1() { // from class: androidx.compose.ui.text.font.FontFamilyResolverImpl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit preload$lambda$4$lambda$3;
                preload$lambda$4$lambda$3 = FontFamilyResolverImpl.preload$lambda$4$lambda$3((TypefaceResult.Immutable) obj);
                return preload$lambda$4$lambda$3;
            }
        }, fontFamilyResolverImpl.createDefaultTypeface);
        if (resolve2 != null) {
            return resolve2;
        }
        throw new IllegalStateException("Could not load font");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit preload$lambda$4$lambda$2(TypefaceResult.Immutable immutable) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit preload$lambda$4$lambda$3(TypefaceResult.Immutable immutable) {
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.text.font.FontFamily.Resolver
    /* renamed from: resolve-DPcqOEQ */
    public State<Object> mo6935resolveDPcqOEQ(FontFamily fontFamily, FontWeight fontWeight, int fontStyle, int fontSynthesis) {
        return resolve(new TypefaceRequest(this.platformResolveInterceptor.interceptFontFamily(fontFamily), this.platformResolveInterceptor.interceptFontWeight(fontWeight), this.platformResolveInterceptor.mo6912interceptFontStyleT2F_aPo(fontStyle), this.platformResolveInterceptor.mo6913interceptFontSynthesisMscr08Y(fontSynthesis), this.platformFontLoader.getCacheKey(), null));
    }

    private final State<Object> resolve(final TypefaceRequest typefaceRequest) {
        return this.typefaceRequestCache.runCached(typefaceRequest, new Function1() { // from class: androidx.compose.ui.text.font.FontFamilyResolverImpl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TypefaceResult resolve$lambda$5;
                resolve$lambda$5 = FontFamilyResolverImpl.resolve$lambda$5(FontFamilyResolverImpl.this, typefaceRequest, (Function1) obj);
                return resolve$lambda$5;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TypefaceResult resolve$lambda$5(FontFamilyResolverImpl fontFamilyResolverImpl, TypefaceRequest typefaceRequest, Function1 function1) {
        TypefaceResult resolve = fontFamilyResolverImpl.fontListFontFamilyTypefaceAdapter.resolve(typefaceRequest, fontFamilyResolverImpl.platformFontLoader, function1, fontFamilyResolverImpl.createDefaultTypeface);
        if (resolve != null) {
            return resolve;
        }
        TypefaceResult resolve2 = fontFamilyResolverImpl.platformFamilyTypefaceAdapter.resolve(typefaceRequest, fontFamilyResolverImpl.platformFontLoader, function1, fontFamilyResolverImpl.createDefaultTypeface);
        if (resolve2 != null) {
            return resolve2;
        }
        throw new IllegalStateException("Could not load font");
    }
}
