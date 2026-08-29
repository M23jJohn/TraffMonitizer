package androidx.navigation.compose;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.SizeTransform;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.navigation.NamedNavArgument;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDeepLink;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavType;
import androidx.navigation.compose.ComposeNavGraphNavigator;
import androidx.navigation.compose.ComposeNavigator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: NavGraphBuilder.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aR\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000e\u001a\u0085\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\u0002\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132\"\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\r¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u001a\u001a\u00ad\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\u0002\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u001b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132\"\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\r¢\u0006\u0002\b\u0013¢\u0006\u0002\u0010\u001d\u001aÅ\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u001e\u0018\u0001*\u00020\u001f*\u00020\u00022\u001d\b\u0002\u0010 \u001a\u0017\u0012\u0004\u0012\u00020\"\u0012\r\u0012\u000b\u0012\u0002\b\u00030#¢\u0006\u0002\b\u00120!2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\n\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u001b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u001c¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132$\b\b\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\r¢\u0006\u0002\b\u0013H\u0086\b¢\u0006\u0002\u0010$\u001aÌ\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u001e*\u00020\u001f*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u001d\b\u0002\u0010 \u001a\u0017\u0012\u0004\u0012\u00020\"\u0012\r\u0012\u000b\u0012\u0002\b\u00030#¢\u0006\u0002\b\u00120!2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\u0002\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u001b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u001c¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132\"\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\r¢\u0006\u0002\b\u0013¢\u0006\u0002\u0010&\u001aU\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010(\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013H\u0007\u001aé\u0001\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010(\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062#\b\u0002\u0010\u000f\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u00132#\b\u0002\u0010\u0014\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u00132#\b\u0002\u0010\u0016\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u00132#\b\u0002\u0010\u0017\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u00132\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013H\u0007\u001a¥\u0002\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010(\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\u0002\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u001b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013\u001aÁ\u0002\u0010'\u001a\u00020\u0001\"\n\b\u0000\u0010\u001e\u0018\u0001*\u00020\u001f*\u00020\u00022\n\u0010(\u001a\u0006\u0012\u0002\b\u00030%2\u001d\b\u0002\u0010 \u001a\u0017\u0012\u0004\u0012\u00020\"\u0012\r\u0012\u000b\u0012\u0002\b\u00030#¢\u0006\u0002\b\u00120!2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\n\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u001b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u001c¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132\u0019\b\b\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013H\u0086\b\u001aÈ\u0002\u0010'\u001a\u00020\u0001\"\b\b\u0000\u0010\u001e*\u00020\u001f*\u00020\u00022\n\u0010(\u001a\u0006\u0012\u0002\b\u00030%2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u001d\b\u0002\u0010 \u001a\u0017\u0012\u0004\u0012\u00020\"\u0012\r\u0012\u000b\u0012\u0002\b\u00030#¢\u0006\u0002\b\u00120!2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\u0002\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u001b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u001c¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013\u001a½\u0002\u0010'\u001a\u00020\u0001\"\n\b\u0000\u0010\u001e\u0018\u0001*\u00020\u001f*\u00020\u00022\u0006\u0010(\u001a\u00020\u001f2\u001d\b\u0002\u0010 \u001a\u0017\u0012\u0004\u0012\u00020\"\u0012\r\u0012\u000b\u0012\u0002\b\u00030#¢\u0006\u0002\b\u00120!2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\n\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\n\u0010\u001b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u001c¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132\u0019\b\b\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013H\u0086\b\u001aÄ\u0002\u0010'\u001a\u00020\u0001\"\b\b\u0000\u0010\u001e*\u00020\u001f*\u00020\u00022\u0006\u0010(\u001a\u00020\u001f2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u001d\b\u0002\u0010 \u001a\u0017\u0012\u0004\u0012\u00020\"\u0012\r\u0012\u000b\u0012\u0002\b\u00030#¢\u0006\u0002\b\u00120!2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\u0002\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0011¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u0015¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132(\b\u0002\u0010\u001b\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u000b\u0012\t\u0018\u00010\u001c¢\u0006\u0002\b\u0012\u0018\u00010\u000b¢\u0006\u0002\b\u00132\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013\u001aZ\u0010*\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\b\b\u0002\u0010+\u001a\u00020,2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0002\u0010-\u001ar\u0010*\u001a\u00020\u0001\"\n\b\u0000\u0010\u001e\u0018\u0001*\u00020\u001f*\u00020\u00022\u001d\b\u0002\u0010 \u001a\u0017\u0012\u0004\u0012\u00020\"\u0012\r\u0012\u000b\u0012\u0002\b\u00030#¢\u0006\u0002\b\u00120!2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\b\b\u0002\u0010+\u001a\u00020,2\u0019\b\b\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0086\b¢\u0006\u0002\u0010.\u001ay\u0010*\u001a\u00020\u0001\"\b\b\u0000\u0010\u001e*\u00020\u001f*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u001e0%2\u001d\b\u0002\u0010 \u001a\u0017\u0012\u0004\u0012\u00020\"\u0012\r\u0012\u000b\u0012\u0002\b\u00030#¢\u0006\u0002\b\u00120!2\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\b\b\u0002\u0010+\u001a\u00020,2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0002\u0010/¨\u00060"}, d2 = {ComposeNavigator.NAME, "", "Landroidx/navigation/NavGraphBuilder;", "route", "", "arguments", "", "Landroidx/navigation/NamedNavArgument;", "deepLinks", "Landroidx/navigation/NavDeepLink;", "content", "Lkotlin/Function1;", "Landroidx/navigation/NavBackStackEntry;", "Landroidx/compose/runtime/Composable;", "(Landroidx/navigation/NavGraphBuilder;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function3;)V", "enterTransition", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/compose/animation/EnterTransition;", "Lkotlin/jvm/JvmSuppressWildcards;", "Lkotlin/ExtensionFunctionType;", "exitTransition", "Landroidx/compose/animation/ExitTransition;", "popEnterTransition", "popExitTransition", "Lkotlin/Function2;", "Landroidx/compose/animation/AnimatedContentScope;", "(Landroidx/navigation/NavGraphBuilder;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "sizeTransform", "Landroidx/compose/animation/SizeTransform;", "(Landroidx/navigation/NavGraphBuilder;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "T", "", "typeMap", "", "Lkotlin/reflect/KType;", "Landroidx/navigation/NavType;", "(Landroidx/navigation/NavGraphBuilder;Ljava/util/Map;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "Lkotlin/reflect/KClass;", "(Landroidx/navigation/NavGraphBuilder;Lkotlin/reflect/KClass;Ljava/util/Map;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "navigation", "startDestination", "builder", DialogNavigator.NAME, "dialogProperties", "Landroidx/compose/ui/window/DialogProperties;", "(Landroidx/navigation/NavGraphBuilder;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function3;)V", "(Landroidx/navigation/NavGraphBuilder;Ljava/util/Map;Ljava/util/List;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function3;)V", "(Landroidx/navigation/NavGraphBuilder;Lkotlin/reflect/KClass;Ljava/util/Map;Ljava/util/List;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function3;)V", "navigation-compose_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NavGraphBuilderKt {
    public static /* synthetic */ void composable$default(NavGraphBuilder navGraphBuilder, String str, List list, List list2, Function3 function3, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        composable(navGraphBuilder, str, list, list2, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of composable builder that supports AnimatedContent")
    public static final /* synthetic */ void composable(NavGraphBuilder navGraphBuilder, String str, List list, List list2, final Function3 function3) {
        ComposeNavigator.Destination destination = new ComposeNavigator.Destination((ComposeNavigator) navGraphBuilder.getProvider().getNavigator(ComposeNavigator.class), (Function4<? super AnimatedContentScope, NavBackStackEntry, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambdaInstance(-1516831465, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: androidx.navigation.compose.NavGraphBuilderKt$composable$1
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, int i) {
                ComposerKt.sourceInformation(composer, "C55@2137L14:NavGraphBuilder.kt#opm8kd");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1516831465, i, -1, "androidx.navigation.compose.composable.<anonymous> (NavGraphBuilder.kt:55)");
                }
                function3.invoke(navBackStackEntry, composer, Integer.valueOf((i >> 3) & 14));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        destination.setRoute(str);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NamedNavArgument namedNavArgument = (NamedNavArgument) it.next();
            destination.addArgument(namedNavArgument.getName(), namedNavArgument.getArgument());
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            destination.addDeepLink((NavDeepLink) it2.next());
        }
        navGraphBuilder.addDestination(destination);
    }

    public static /* synthetic */ void composable$default(NavGraphBuilder navGraphBuilder, String str, List list, List list2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list3 = list;
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        List list4 = list2;
        Function1 function15 = (i & 8) != 0 ? null : function1;
        Function1 function16 = (i & 16) != 0 ? null : function12;
        composable(navGraphBuilder, str, list3, list4, function15, function16, (i & 32) != 0 ? function15 : function13, (i & 64) != 0 ? function16 : function14, function4);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of composable builder that supports sizeTransform")
    public static final /* synthetic */ void composable(NavGraphBuilder navGraphBuilder, String str, List list, List list2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function4 function4) {
        ComposeNavigatorDestinationBuilder composeNavigatorDestinationBuilder = new ComposeNavigatorDestinationBuilder((ComposeNavigator) navGraphBuilder.getProvider().getNavigator(ComposeNavigator.class), str, function4);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NamedNavArgument namedNavArgument = (NamedNavArgument) it.next();
            composeNavigatorDestinationBuilder.argument(namedNavArgument.getName(), namedNavArgument.getArgument());
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            composeNavigatorDestinationBuilder.deepLink((NavDeepLink) it2.next());
        }
        composeNavigatorDestinationBuilder.setEnterTransition(function1);
        composeNavigatorDestinationBuilder.setExitTransition(function12);
        composeNavigatorDestinationBuilder.setPopEnterTransition(function13);
        composeNavigatorDestinationBuilder.setPopExitTransition(function14);
        navGraphBuilder.destination(composeNavigatorDestinationBuilder);
    }

    public static /* synthetic */ void composable$default(NavGraphBuilder navGraphBuilder, String str, List list, List list2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list3 = list;
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        List list4 = list2;
        Function1 function16 = (i & 8) != 0 ? null : function1;
        Function1 function17 = (i & 16) != 0 ? null : function12;
        composable(navGraphBuilder, str, (List<NamedNavArgument>) list3, (List<NavDeepLink>) list4, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function16, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function17, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) ((i & 32) != 0 ? function16 : function13), (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) ((i & 64) != 0 ? function17 : function14), (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) ((i & 128) != 0 ? null : function15), (Function4<? super AnimatedContentScope, ? super NavBackStackEntry, ? super Composer, ? super Integer, Unit>) function4);
    }

    public static final void composable(NavGraphBuilder navGraphBuilder, String str, List<NamedNavArgument> list, List<NavDeepLink> list2, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, Function4<? super AnimatedContentScope, ? super NavBackStackEntry, ? super Composer, ? super Integer, Unit> function4) {
        ComposeNavigatorDestinationBuilder composeNavigatorDestinationBuilder = new ComposeNavigatorDestinationBuilder((ComposeNavigator) navGraphBuilder.getProvider().getNavigator(ComposeNavigator.class), str, function4);
        for (NamedNavArgument namedNavArgument : list) {
            composeNavigatorDestinationBuilder.argument(namedNavArgument.getName(), namedNavArgument.getArgument());
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            composeNavigatorDestinationBuilder.deepLink((NavDeepLink) it.next());
        }
        composeNavigatorDestinationBuilder.setEnterTransition(function1);
        composeNavigatorDestinationBuilder.setExitTransition(function12);
        composeNavigatorDestinationBuilder.setPopEnterTransition(function13);
        composeNavigatorDestinationBuilder.setPopExitTransition(function14);
        composeNavigatorDestinationBuilder.setSizeTransform(function15);
        navGraphBuilder.destination(composeNavigatorDestinationBuilder);
    }

    public static /* synthetic */ void composable$default(NavGraphBuilder navGraphBuilder, Map map, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function4 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list2 = list;
        Function1 function16 = (i & 4) != 0 ? null : function1;
        Function1 function17 = (i & 8) != 0 ? null : function12;
        Function1 function18 = (i & 16) != 0 ? function16 : function13;
        Function1 function19 = (i & 32) != 0 ? function17 : function14;
        Function1 function110 = (i & 64) != 0 ? null : function15;
        Intrinsics.reifiedOperationMarker(4, "T");
        composable(navGraphBuilder, Reflection.getOrCreateKotlinClass(Object.class), (Map<KType, NavType<?>>) map2, (List<NavDeepLink>) list2, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function16, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function17, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function18, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function19, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) function110, (Function4<? super AnimatedContentScope, ? super NavBackStackEntry, ? super Composer, ? super Integer, Unit>) function4);
    }

    public static final /* synthetic */ <T> void composable(NavGraphBuilder navGraphBuilder, Map<KType, NavType<?>> map, List<NavDeepLink> list, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, Function4<? super AnimatedContentScope, ? super NavBackStackEntry, ? super Composer, ? super Integer, Unit> function4) {
        Intrinsics.reifiedOperationMarker(4, "T");
        composable(navGraphBuilder, Reflection.getOrCreateKotlinClass(Object.class), map, list, function1, function12, function13, function14, function15, function4);
    }

    public static /* synthetic */ void composable$default(NavGraphBuilder navGraphBuilder, KClass kClass, Map map, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function4 function4, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list2 = list;
        Function1 function16 = (i & 8) != 0 ? null : function1;
        Function1 function17 = (i & 16) != 0 ? null : function12;
        composable(navGraphBuilder, kClass, (Map<KType, NavType<?>>) map2, (List<NavDeepLink>) list2, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function16, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function17, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) ((i & 32) != 0 ? function16 : function13), (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) ((i & 64) != 0 ? function17 : function14), (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) ((i & 128) != 0 ? null : function15), (Function4<? super AnimatedContentScope, ? super NavBackStackEntry, ? super Composer, ? super Integer, Unit>) function4);
    }

    public static final <T> void composable(NavGraphBuilder navGraphBuilder, KClass<T> kClass, Map<KType, NavType<?>> map, List<NavDeepLink> list, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, Function4<? super AnimatedContentScope, ? super NavBackStackEntry, ? super Composer, ? super Integer, Unit> function4) {
        ComposeNavigatorDestinationBuilder composeNavigatorDestinationBuilder = new ComposeNavigatorDestinationBuilder((ComposeNavigator) navGraphBuilder.getProvider().getNavigator(ComposeNavigator.class), kClass, map, function4);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            composeNavigatorDestinationBuilder.deepLink((NavDeepLink) it.next());
        }
        composeNavigatorDestinationBuilder.setEnterTransition(function1);
        composeNavigatorDestinationBuilder.setExitTransition(function12);
        composeNavigatorDestinationBuilder.setPopEnterTransition(function13);
        composeNavigatorDestinationBuilder.setPopExitTransition(function14);
        composeNavigatorDestinationBuilder.setSizeTransform(function15);
        navGraphBuilder.destination(composeNavigatorDestinationBuilder);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, String str, String str2, List list, List list2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list3 = list;
        if ((i & 8) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        navigation(navGraphBuilder, str, str2, (List<NamedNavArgument>) list3, (List<NavDeepLink>) list2, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) null, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) null, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) null, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) null, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) null, (Function1<? super NavGraphBuilder, Unit>) function1);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, String str, String str2, List list, List list2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list3 = list;
        List emptyList = (i & 8) != 0 ? CollectionsKt.emptyList() : list2;
        Function1 function16 = (i & 16) != 0 ? null : function1;
        Function1 function17 = (i & 32) != 0 ? null : function12;
        navigation(navGraphBuilder, str, str2, (List<NamedNavArgument>) list3, (List<NavDeepLink>) emptyList, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function16, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function17, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) ((i & 64) != 0 ? function16 : function13), (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) ((i & 128) != 0 ? function17 : function14), (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) null, (Function1<? super NavGraphBuilder, Unit>) function15);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, String str, String str2, List list, List list2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, Object obj) {
        Function1 function17;
        NavGraphBuilder navGraphBuilder2;
        String str3;
        String str4;
        Function1 function18;
        List emptyList = (i & 4) != 0 ? CollectionsKt.emptyList() : list;
        List emptyList2 = (i & 8) != 0 ? CollectionsKt.emptyList() : list2;
        Function1 function19 = (i & 16) != 0 ? null : function1;
        Function1 function110 = (i & 32) != 0 ? null : function12;
        Function1 function111 = (i & 64) != 0 ? function19 : function13;
        Function1 function112 = (i & 128) != 0 ? function110 : function14;
        if ((i & 256) != 0) {
            function17 = null;
            str3 = str;
            str4 = str2;
            function18 = function16;
            navGraphBuilder2 = navGraphBuilder;
        } else {
            function17 = function15;
            navGraphBuilder2 = navGraphBuilder;
            str3 = str;
            str4 = str2;
            function18 = function16;
        }
        navigation(navGraphBuilder2, str3, str4, (List<NamedNavArgument>) emptyList, (List<NavDeepLink>) emptyList2, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function19, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function110, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function111, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function112, (Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) function17, (Function1<? super NavGraphBuilder, Unit>) function18);
    }

    public static final void navigation(NavGraphBuilder navGraphBuilder, String str, String str2, List<NamedNavArgument> list, List<NavDeepLink> list2, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, Function1<? super NavGraphBuilder, Unit> function16) {
        NavGraphBuilder navGraphBuilder2 = new NavGraphBuilder(navGraphBuilder.getProvider(), str, str2);
        function16.invoke(navGraphBuilder2);
        NavGraph build = navGraphBuilder2.build();
        for (NamedNavArgument namedNavArgument : list) {
            build.addArgument(namedNavArgument.getName(), namedNavArgument.getArgument());
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            build.addDeepLink((NavDeepLink) it.next());
        }
        if (build instanceof ComposeNavGraphNavigator.ComposeNavGraph) {
            ComposeNavGraphNavigator.ComposeNavGraph composeNavGraph = (ComposeNavGraphNavigator.ComposeNavGraph) build;
            composeNavGraph.setEnterTransition$navigation_compose_release(function1);
            composeNavGraph.setExitTransition$navigation_compose_release(function12);
            composeNavGraph.setPopEnterTransition$navigation_compose_release(function13);
            composeNavGraph.setPopExitTransition$navigation_compose_release(function14);
            composeNavGraph.setSizeTransform$navigation_compose_release(function15);
        }
        navGraphBuilder.addDestination(build);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, KClass kClass, Map map, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        List emptyList = (i & 4) != 0 ? CollectionsKt.emptyList() : list;
        Function1 function17 = (i & 8) != 0 ? null : function1;
        Function1 function18 = (i & 16) != 0 ? null : function12;
        Function1 function19 = (i & 32) != 0 ? function17 : function13;
        Function1 function110 = (i & 64) != 0 ? function18 : function14;
        Function1 function111 = (i & 128) != 0 ? null : function15;
        Intrinsics.reifiedOperationMarker(4, "T");
        navigation(navGraphBuilder, (KClass<?>) kClass, Reflection.getOrCreateKotlinClass(Object.class), (Map<KType, NavType<?>>) map2, (List<NavDeepLink>) emptyList, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function17, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function18, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function19, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function110, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) function111, (Function1<? super NavGraphBuilder, Unit>) function16);
    }

    public static final /* synthetic */ <T> void navigation(NavGraphBuilder navGraphBuilder, KClass<?> kClass, Map<KType, NavType<?>> map, List<NavDeepLink> list, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, Function1<? super NavGraphBuilder, Unit> function16) {
        Intrinsics.reifiedOperationMarker(4, "T");
        navigation(navGraphBuilder, kClass, Reflection.getOrCreateKotlinClass(Object.class), map, list, function1, function12, function13, function14, function15, function16);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, KClass kClass, KClass kClass2, Map map, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, Object obj) {
        Function1 function17;
        NavGraphBuilder navGraphBuilder2;
        KClass kClass3;
        KClass kClass4;
        Function1 function18;
        Map emptyMap = (i & 4) != 0 ? MapsKt.emptyMap() : map;
        List emptyList = (i & 8) != 0 ? CollectionsKt.emptyList() : list;
        Function1 function19 = (i & 16) != 0 ? null : function1;
        Function1 function110 = (i & 32) != 0 ? null : function12;
        Function1 function111 = (i & 64) != 0 ? function19 : function13;
        Function1 function112 = (i & 128) != 0 ? function110 : function14;
        if ((i & 256) != 0) {
            function17 = null;
            kClass3 = kClass;
            kClass4 = kClass2;
            function18 = function16;
            navGraphBuilder2 = navGraphBuilder;
        } else {
            function17 = function15;
            navGraphBuilder2 = navGraphBuilder;
            kClass3 = kClass;
            kClass4 = kClass2;
            function18 = function16;
        }
        navigation(navGraphBuilder2, (KClass<?>) kClass3, kClass4, (Map<KType, NavType<?>>) emptyMap, (List<NavDeepLink>) emptyList, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function19, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function110, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function111, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function112, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) function17, (Function1<? super NavGraphBuilder, Unit>) function18);
    }

    public static final <T> void navigation(NavGraphBuilder navGraphBuilder, KClass<?> kClass, KClass<T> kClass2, Map<KType, NavType<?>> map, List<NavDeepLink> list, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, Function1<? super NavGraphBuilder, Unit> function16) {
        NavGraphBuilder navGraphBuilder2 = new NavGraphBuilder(navGraphBuilder.getProvider(), kClass, (KClass<?>) kClass2, map);
        function16.invoke(navGraphBuilder2);
        NavGraph build = navGraphBuilder2.build();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            build.addDeepLink((NavDeepLink) it.next());
        }
        if (build instanceof ComposeNavGraphNavigator.ComposeNavGraph) {
            ComposeNavGraphNavigator.ComposeNavGraph composeNavGraph = (ComposeNavGraphNavigator.ComposeNavGraph) build;
            composeNavGraph.setEnterTransition$navigation_compose_release(function1);
            composeNavGraph.setExitTransition$navigation_compose_release(function12);
            composeNavGraph.setPopEnterTransition$navigation_compose_release(function13);
            composeNavGraph.setPopExitTransition$navigation_compose_release(function14);
            composeNavGraph.setSizeTransform$navigation_compose_release(function15);
        }
        navGraphBuilder.addDestination(build);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, Object obj, Map map, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, Object obj2) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        List emptyList = (i & 4) != 0 ? CollectionsKt.emptyList() : list;
        Function1 function17 = (i & 8) != 0 ? null : function1;
        Function1 function18 = (i & 16) != 0 ? null : function12;
        Function1 function19 = (i & 32) != 0 ? function17 : function13;
        Function1 function110 = (i & 64) != 0 ? function18 : function14;
        Function1 function111 = (i & 128) != 0 ? null : function15;
        Intrinsics.reifiedOperationMarker(4, "T");
        navigation(navGraphBuilder, obj, Reflection.getOrCreateKotlinClass(Object.class), (Map<KType, NavType<?>>) map2, (List<NavDeepLink>) emptyList, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function17, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function18, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function19, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function110, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) function111, (Function1<? super NavGraphBuilder, Unit>) function16);
    }

    public static final /* synthetic */ <T> void navigation(NavGraphBuilder navGraphBuilder, Object obj, Map<KType, NavType<?>> map, List<NavDeepLink> list, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, Function1<? super NavGraphBuilder, Unit> function16) {
        Intrinsics.reifiedOperationMarker(4, "T");
        navigation(navGraphBuilder, obj, Reflection.getOrCreateKotlinClass(Object.class), map, list, function1, function12, function13, function14, function15, function16);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, Object obj, KClass kClass, Map map, List list, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, Object obj2) {
        Function1 function17;
        NavGraphBuilder navGraphBuilder2;
        Object obj3;
        KClass kClass2;
        Function1 function18;
        Map emptyMap = (i & 4) != 0 ? MapsKt.emptyMap() : map;
        List emptyList = (i & 8) != 0 ? CollectionsKt.emptyList() : list;
        Function1 function19 = (i & 16) != 0 ? null : function1;
        Function1 function110 = (i & 32) != 0 ? null : function12;
        Function1 function111 = (i & 64) != 0 ? function19 : function13;
        Function1 function112 = (i & 128) != 0 ? function110 : function14;
        if ((i & 256) != 0) {
            function17 = null;
            obj3 = obj;
            kClass2 = kClass;
            function18 = function16;
            navGraphBuilder2 = navGraphBuilder;
        } else {
            function17 = function15;
            navGraphBuilder2 = navGraphBuilder;
            obj3 = obj;
            kClass2 = kClass;
            function18 = function16;
        }
        navigation(navGraphBuilder2, obj3, kClass2, (Map<KType, NavType<?>>) emptyMap, (List<NavDeepLink>) emptyList, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function19, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function110, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition>) function111, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition>) function112, (Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform>) function17, (Function1<? super NavGraphBuilder, Unit>) function18);
    }

    public static final <T> void navigation(NavGraphBuilder navGraphBuilder, Object obj, KClass<T> kClass, Map<KType, NavType<?>> map, List<NavDeepLink> list, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, SizeTransform> function15, Function1<? super NavGraphBuilder, Unit> function16) {
        NavGraphBuilder navGraphBuilder2 = new NavGraphBuilder(navGraphBuilder.getProvider(), obj, (KClass<?>) kClass, map);
        function16.invoke(navGraphBuilder2);
        NavGraph build = navGraphBuilder2.build();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            build.addDeepLink((NavDeepLink) it.next());
        }
        if (build instanceof ComposeNavGraphNavigator.ComposeNavGraph) {
            ComposeNavGraphNavigator.ComposeNavGraph composeNavGraph = (ComposeNavGraphNavigator.ComposeNavGraph) build;
            composeNavGraph.setEnterTransition$navigation_compose_release(function1);
            composeNavGraph.setExitTransition$navigation_compose_release(function12);
            composeNavGraph.setPopEnterTransition$navigation_compose_release(function13);
            composeNavGraph.setPopExitTransition$navigation_compose_release(function14);
            composeNavGraph.setSizeTransform$navigation_compose_release(function15);
        }
        navGraphBuilder.addDestination(build);
    }

    public static /* synthetic */ void dialog$default(NavGraphBuilder navGraphBuilder, String str, List list, List list2, DialogProperties dialogProperties, Function3 function3, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list3 = list;
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        dialog(navGraphBuilder, str, (List<NamedNavArgument>) list3, (List<NavDeepLink>) list2, (i & 8) != 0 ? new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null) : dialogProperties, (Function3<? super NavBackStackEntry, ? super Composer, ? super Integer, Unit>) function3);
    }

    public static final void dialog(NavGraphBuilder navGraphBuilder, String str, List<NamedNavArgument> list, List<NavDeepLink> list2, DialogProperties dialogProperties, Function3<? super NavBackStackEntry, ? super Composer, ? super Integer, Unit> function3) {
        DialogNavigatorDestinationBuilder dialogNavigatorDestinationBuilder = new DialogNavigatorDestinationBuilder((DialogNavigator) navGraphBuilder.getProvider().getNavigator(DialogNavigator.class), str, dialogProperties, function3);
        for (NamedNavArgument namedNavArgument : list) {
            dialogNavigatorDestinationBuilder.argument(namedNavArgument.getName(), namedNavArgument.getArgument());
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            dialogNavigatorDestinationBuilder.deepLink((NavDeepLink) it.next());
        }
        navGraphBuilder.destination(dialogNavigatorDestinationBuilder);
    }

    public static /* synthetic */ void dialog$default(NavGraphBuilder navGraphBuilder, Map map, List list, DialogProperties dialogProperties, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list2 = list;
        DialogProperties dialogProperties2 = (i & 4) != 0 ? new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null) : dialogProperties;
        Intrinsics.reifiedOperationMarker(4, "T");
        dialog(navGraphBuilder, Reflection.getOrCreateKotlinClass(Object.class), (Map<KType, NavType<?>>) map2, (List<NavDeepLink>) list2, dialogProperties2, (Function3<? super NavBackStackEntry, ? super Composer, ? super Integer, Unit>) function3);
    }

    public static final /* synthetic */ <T> void dialog(NavGraphBuilder navGraphBuilder, Map<KType, NavType<?>> map, List<NavDeepLink> list, DialogProperties dialogProperties, Function3<? super NavBackStackEntry, ? super Composer, ? super Integer, Unit> function3) {
        Intrinsics.reifiedOperationMarker(4, "T");
        dialog(navGraphBuilder, Reflection.getOrCreateKotlinClass(Object.class), map, list, dialogProperties, function3);
    }

    public static /* synthetic */ void dialog$default(NavGraphBuilder navGraphBuilder, KClass kClass, Map map, List list, DialogProperties dialogProperties, Function3 function3, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        dialog(navGraphBuilder, kClass, (Map<KType, NavType<?>>) map2, (List<NavDeepLink>) list, (i & 8) != 0 ? new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null) : dialogProperties, (Function3<? super NavBackStackEntry, ? super Composer, ? super Integer, Unit>) function3);
    }

    public static final <T> void dialog(NavGraphBuilder navGraphBuilder, KClass<T> kClass, Map<KType, NavType<?>> map, List<NavDeepLink> list, DialogProperties dialogProperties, Function3<? super NavBackStackEntry, ? super Composer, ? super Integer, Unit> function3) {
        DialogNavigatorDestinationBuilder dialogNavigatorDestinationBuilder = new DialogNavigatorDestinationBuilder((DialogNavigator) navGraphBuilder.getProvider().getNavigator(DialogNavigator.class), kClass, map, dialogProperties, function3);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            dialogNavigatorDestinationBuilder.deepLink((NavDeepLink) it.next());
        }
        navGraphBuilder.destination(dialogNavigatorDestinationBuilder);
    }
}
