package com.traffmonetizer.client.ui;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AppNavigation.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\b\b\t\n\u000b\f\r\u000e\u000fB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\b\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017¨\u0006\u0018"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen;", "", "route", "", "<init>", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "Initial", "Login", "Registration", "MainScreen", "MainGraph", "HomeTab", "SettingsTab", "ConnectionCheckScreen", "Lcom/traffmonetizer/client/ui/Screen$ConnectionCheckScreen;", "Lcom/traffmonetizer/client/ui/Screen$HomeTab;", "Lcom/traffmonetizer/client/ui/Screen$Initial;", "Lcom/traffmonetizer/client/ui/Screen$Login;", "Lcom/traffmonetizer/client/ui/Screen$MainGraph;", "Lcom/traffmonetizer/client/ui/Screen$MainScreen;", "Lcom/traffmonetizer/client/ui/Screen$Registration;", "Lcom/traffmonetizer/client/ui/Screen$SettingsTab;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Screen {
    public static final int $stable = 0;
    private final String route;

    public /* synthetic */ Screen(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen$Initial;", "Lcom/traffmonetizer/client/ui/Screen;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Initial extends Screen {
        public static final int $stable = 0;
        public static final Initial INSTANCE = new Initial();

        private Initial() {
            super("initial_screen", null);
        }
    }

    private Screen(String str) {
        this.route = str;
    }

    public final String getRoute() {
        return this.route;
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen$Login;", "Lcom/traffmonetizer/client/ui/Screen;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Login extends Screen {
        public static final int $stable = 0;
        public static final Login INSTANCE = new Login();

        private Login() {
            super("login_screen", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen$Registration;", "Lcom/traffmonetizer/client/ui/Screen;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Registration extends Screen {
        public static final int $stable = 0;
        public static final Registration INSTANCE = new Registration();

        private Registration() {
            super("registration_screen", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen$MainScreen;", "Lcom/traffmonetizer/client/ui/Screen;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MainScreen extends Screen {
        public static final int $stable = 0;
        public static final MainScreen INSTANCE = new MainScreen();

        private MainScreen() {
            super("main_screen", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen$MainGraph;", "Lcom/traffmonetizer/client/ui/Screen;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MainGraph extends Screen {
        public static final int $stable = 0;
        public static final MainGraph INSTANCE = new MainGraph();

        private MainGraph() {
            super("main_graph", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen$HomeTab;", "Lcom/traffmonetizer/client/ui/Screen;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class HomeTab extends Screen {
        public static final int $stable = 0;
        public static final HomeTab INSTANCE = new HomeTab();

        private HomeTab() {
            super("home_tab", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen$SettingsTab;", "Lcom/traffmonetizer/client/ui/Screen;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SettingsTab extends Screen {
        public static final int $stable = 0;
        public static final SettingsTab INSTANCE = new SettingsTab();

        private SettingsTab() {
            super("settings_tab", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/ui/Screen$ConnectionCheckScreen;", "Lcom/traffmonetizer/client/ui/Screen;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ConnectionCheckScreen extends Screen {
        public static final int $stable = 0;
        public static final ConnectionCheckScreen INSTANCE = new ConnectionCheckScreen();

        private ConnectionCheckScreen() {
            super("connection_check_screen", null);
        }
    }
}
