package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.State;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigator;
import kotlin.Metadata;

@Metadata(d1 = {"androidx/navigation/compose/NavHostControllerKt__NavHostControllerKt", "androidx/navigation/compose/NavHostControllerKt__NavHostController_androidKt"}, k = 4, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NavHostControllerKt {
    public static final State<NavBackStackEntry> currentBackStackEntryAsState(NavController navController, Composer composer, int i) {
        return NavHostControllerKt__NavHostControllerKt.currentBackStackEntryAsState(navController, composer, i);
    }

    public static final NavHostController rememberNavController(Navigator<? extends NavDestination>[] navigatorArr, Composer composer, int i) {
        return NavHostControllerKt__NavHostController_androidKt.rememberNavController(navigatorArr, composer, i);
    }
}
