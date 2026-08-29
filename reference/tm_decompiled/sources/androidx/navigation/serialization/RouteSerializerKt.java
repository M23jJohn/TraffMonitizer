package androidx.navigation.serialization;

import androidx.autofill.HintConstants;
import androidx.navigation.NamedNavArgument;
import androidx.navigation.NamedNavArgumentKt;
import androidx.navigation.NavArgumentBuilder;
import androidx.navigation.NavType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.ContextAwareKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;

/* compiled from: RouteSerializer.kt */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\u001a>\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0000\u001a8\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005H\u0007\u001a;\u0010\f\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\r2\u0006\u0010\u000e\u001a\u0002H\u00022\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00070\u0005H\u0007¢\u0006\u0002\u0010\u000f\u001a&\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002\u001a.\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0007*\u00020\u00152\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005H\u0002\u001a\u0018\u0010\u0016\u001a\u00020\u0017\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\u008c\u0001\u0010\u0018\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00052S\u0010\u0019\u001aO\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0007¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00110\u001aH\u0003¢\u0006\u0002\b \u001a\u008e\u0001\u0010\u0018\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00070\u00052S\u0010\u0019\u001aO\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0007¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00110\u001aH\u0003¢\u0006\u0002\b!\u001a(\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a\f\u0010&\u001a\u00020'*\u00020\u0015H\u0000¨\u0006("}, d2 = {"generateRoutePattern", "", "T", "Lkotlinx/serialization/KSerializer;", "typeMap", "", "Lkotlin/reflect/KType;", "Landroidx/navigation/NavType;", "path", "generateNavArguments", "", "Landroidx/navigation/NamedNavArgument;", "generateRouteWithArgs", "", "route", "(Ljava/lang/Object;Ljava/util/Map;)Ljava/lang/String;", "assertNotAbstractClass", "", "handler", "Lkotlin/Function0;", "computeNavType", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "generateHashCode", "", "forEachIndexed", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "argName", "navType", "forEachIndexedKType", "forEachIndexedName", "unknownNavTypeErrorMessage", "fieldName", "fieldType", "className", "isValueClass", "", "navigation-common_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RouteSerializerKt {
    public static /* synthetic */ String generateRoutePattern$default(KSerializer kSerializer, Map map, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return generateRoutePattern(kSerializer, map, str);
    }

    public static final <T> String generateRoutePattern(final KSerializer<T> kSerializer, Map<KType, ? extends NavType<?>> typeMap, String str) {
        final RouteBuilder routeBuilder;
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        Intrinsics.checkNotNullParameter(typeMap, "typeMap");
        assertNotAbstractClass(kSerializer, new Function0() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit generateRoutePattern$lambda$0;
                generateRoutePattern$lambda$0 = RouteSerializerKt.generateRoutePattern$lambda$0(KSerializer.this);
                return generateRoutePattern$lambda$0;
            }
        });
        if (str != null) {
            routeBuilder = new RouteBuilder(str, kSerializer);
        } else {
            routeBuilder = new RouteBuilder(kSerializer);
        }
        forEachIndexedKType(kSerializer, typeMap, new Function3() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Unit generateRoutePattern$lambda$1;
                generateRoutePattern$lambda$1 = RouteSerializerKt.generateRoutePattern$lambda$1(RouteBuilder.this, ((Integer) obj).intValue(), (String) obj2, (NavType) obj3);
                return generateRoutePattern$lambda$1;
            }
        });
        return routeBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit generateRoutePattern$lambda$0(KSerializer kSerializer) {
        StringBuilder sb = new StringBuilder("Cannot generate route pattern from polymorphic class ");
        KClass<?> capturedKClass = ContextAwareKt.getCapturedKClass(kSerializer.getDescriptor());
        sb.append(capturedKClass != null ? capturedKClass.getSimpleName() : null);
        sb.append(". Routes can only be generated from concrete classes or objects.");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit generateRoutePattern$lambda$1(RouteBuilder routeBuilder, int i, String argName, NavType navType) {
        Intrinsics.checkNotNullParameter(argName, "argName");
        Intrinsics.checkNotNullParameter(navType, "navType");
        routeBuilder.appendPattern(i, argName, navType);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ List generateNavArguments$default(KSerializer kSerializer, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        return generateNavArguments(kSerializer, map);
    }

    public static final <T> List<NamedNavArgument> generateNavArguments(final KSerializer<T> kSerializer, final Map<KType, ? extends NavType<?>> typeMap) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        Intrinsics.checkNotNullParameter(typeMap, "typeMap");
        assertNotAbstractClass(kSerializer, new Function0() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit generateNavArguments$lambda$2;
                generateNavArguments$lambda$2 = RouteSerializerKt.generateNavArguments$lambda$2(KSerializer.this);
                return generateNavArguments$lambda$2;
            }
        });
        int elementsCount = kSerializer.getDescriptor().getElementsCount();
        ArrayList arrayList = new ArrayList(elementsCount);
        for (final int i = 0; i < elementsCount; i++) {
            final String elementName = kSerializer.getDescriptor().getElementName(i);
            arrayList.add(NamedNavArgumentKt.navArgument(elementName, new Function1() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit generateNavArguments$lambda$4$lambda$3;
                    generateNavArguments$lambda$4$lambda$3 = RouteSerializerKt.generateNavArguments$lambda$4$lambda$3(KSerializer.this, i, typeMap, elementName, (NavArgumentBuilder) obj);
                    return generateNavArguments$lambda$4$lambda$3;
                }
            }));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit generateNavArguments$lambda$2(KSerializer kSerializer) {
        throw new IllegalArgumentException("Cannot generate NavArguments for polymorphic serializer " + kSerializer + ". Arguments can only be generated from concrete classes or objects.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit generateNavArguments$lambda$4$lambda$3(KSerializer kSerializer, int i, Map map, String str, NavArgumentBuilder navArgument) {
        Intrinsics.checkNotNullParameter(navArgument, "$this$navArgument");
        SerialDescriptor elementDescriptor = kSerializer.getDescriptor().getElementDescriptor(i);
        boolean isNullable = elementDescriptor.isNullable();
        NavType<?> computeNavType = computeNavType(elementDescriptor, map);
        if (computeNavType != null) {
            navArgument.setType(computeNavType);
            navArgument.setNullable(isNullable);
            if (kSerializer.getDescriptor().isElementOptional(i)) {
                navArgument.setUnknownDefaultValuePresent$navigation_common_release(true);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalArgumentException(unknownNavTypeErrorMessage(str, elementDescriptor.getSerialName(), kSerializer.getDescriptor().getSerialName(), map.toString()));
    }

    public static final <T> String generateRouteWithArgs(T route, Map<String, ? extends NavType<Object>> typeMap) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(typeMap, "typeMap");
        KSerializer serializer = SerializersKt.serializer(Reflection.getOrCreateKotlinClass(route.getClass()));
        final Map<String, List<String>> encodeToArgMap = new RouteEncoder(serializer, typeMap).encodeToArgMap(route);
        final RouteBuilder routeBuilder = new RouteBuilder(serializer);
        forEachIndexedName(serializer, typeMap, new Function3() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Unit generateRouteWithArgs$lambda$5;
                generateRouteWithArgs$lambda$5 = RouteSerializerKt.generateRouteWithArgs$lambda$5(encodeToArgMap, routeBuilder, ((Integer) obj).intValue(), (String) obj2, (NavType) obj3);
                return generateRouteWithArgs$lambda$5;
            }
        });
        return routeBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit generateRouteWithArgs$lambda$5(Map map, RouteBuilder routeBuilder, int i, String argName, NavType navType) {
        Intrinsics.checkNotNullParameter(argName, "argName");
        Intrinsics.checkNotNullParameter(navType, "navType");
        Object obj = map.get(argName);
        Intrinsics.checkNotNull(obj);
        routeBuilder.appendArg(i, argName, navType, (List) obj);
        return Unit.INSTANCE;
    }

    private static final <T> void assertNotAbstractClass(KSerializer<T> kSerializer, Function0<Unit> function0) {
        if (kSerializer instanceof PolymorphicSerializer) {
            function0.invoke();
        }
    }

    private static final NavType<Object> computeNavType(SerialDescriptor serialDescriptor, Map<KType, ? extends NavType<?>> map) {
        Object obj;
        Iterator<T> it = map.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (NavTypeConverterKt.matchKType(serialDescriptor, (KType) obj)) {
                break;
            }
        }
        KType kType = (KType) obj;
        NavType<?> navType = kType != null ? map.get(kType) : null;
        if (!(navType instanceof NavType)) {
            navType = null;
        }
        if (navType == null) {
            navType = NavTypeConverterKt.getNavType(serialDescriptor);
        }
        if (Intrinsics.areEqual(navType, UNKNOWN.INSTANCE)) {
            return null;
        }
        Intrinsics.checkNotNull(navType, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any?>");
        return navType;
    }

    public static final <T> int generateHashCode(KSerializer<T> kSerializer) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        int hashCode = kSerializer.getDescriptor().getSerialName().hashCode();
        int elementsCount = kSerializer.getDescriptor().getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            hashCode = (hashCode * 31) + kSerializer.getDescriptor().getElementName(i).hashCode();
        }
        return hashCode;
    }

    static /* synthetic */ void forEachIndexedKType$default(KSerializer kSerializer, Map map, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        forEachIndexedKType(kSerializer, map, function3);
    }

    private static final <T> void forEachIndexedKType(KSerializer<T> kSerializer, Map<KType, ? extends NavType<?>> map, Function3<? super Integer, ? super String, ? super NavType<Object>, Unit> function3) {
        int elementsCount = kSerializer.getDescriptor().getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            String elementName = kSerializer.getDescriptor().getElementName(i);
            NavType<Object> computeNavType = computeNavType(kSerializer.getDescriptor().getElementDescriptor(i), map);
            if (computeNavType == null) {
                throw new IllegalArgumentException(unknownNavTypeErrorMessage(elementName, kSerializer.getDescriptor().getElementDescriptor(i).getSerialName(), kSerializer.getDescriptor().getSerialName(), map.toString()));
            }
            function3.invoke(Integer.valueOf(i), elementName, computeNavType);
        }
    }

    private static final <T> void forEachIndexedName(KSerializer<T> kSerializer, Map<String, ? extends NavType<Object>> map, Function3<? super Integer, ? super String, ? super NavType<Object>, Unit> function3) {
        int elementsCount = kSerializer.getDescriptor().getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            String elementName = kSerializer.getDescriptor().getElementName(i);
            NavType<Object> navType = map.get(elementName);
            if (navType == null) {
                throw new IllegalStateException(("Cannot locate NavType for argument [" + elementName + ']').toString());
            }
            function3.invoke(Integer.valueOf(i), elementName, navType);
        }
    }

    private static final String unknownNavTypeErrorMessage(String str, String str2, String str3, String str4) {
        return "Route " + str3 + " could not find any NavType for argument " + str + " of type " + str2 + " - typeMap received was " + str4;
    }

    public static final boolean isValueClass(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return Intrinsics.areEqual(serialDescriptor.getKind(), StructureKind.CLASS.INSTANCE) && serialDescriptor.getIsInline() && serialDescriptor.getElementsCount() == 1;
    }
}
