package com.tm;

import com.ptrbrynt.kotlin_bloc.core.Bloc;
import com.ptrbrynt.kotlin_bloc.core.BlocBase;
import com.ptrbrynt.kotlin_bloc.core.BlocObserver;
import com.ptrbrynt.kotlin_bloc.core.Change;
import com.ptrbrynt.kotlin_bloc.core.Transition;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Y0 extends BlocObserver {
    @Override // com.ptrbrynt.kotlin_bloc.core.BlocObserver
    public final void onChange(BlocBase bloc, Change change) {
        Intrinsics.checkNotNullParameter(bloc, "bloc");
        Intrinsics.checkNotNullParameter(change, "change");
        super.onChange(bloc, change);
    }

    @Override // com.ptrbrynt.kotlin_bloc.core.BlocObserver
    public final void onCreate(BlocBase bloc) {
        Intrinsics.checkNotNullParameter(bloc, "bloc");
        super.onCreate(bloc);
    }

    @Override // com.ptrbrynt.kotlin_bloc.core.BlocObserver
    public final void onEvent(Bloc bloc, Object obj) {
        Intrinsics.checkNotNullParameter(bloc, "bloc");
        super.onEvent(bloc, obj);
    }

    @Override // com.ptrbrynt.kotlin_bloc.core.BlocObserver
    public final void onTransition(Bloc bloc, Transition transition) {
        Intrinsics.checkNotNullParameter(bloc, "bloc");
        Intrinsics.checkNotNullParameter(transition, "transition");
        super.onTransition(bloc, transition);
    }
}
