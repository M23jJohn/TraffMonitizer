package com.ptrbrynt.kotlin_bloc.core;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlocObserver.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J9\u0010\u0003\u001a\u00020\u0004\"\u000e\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\u00020\u0004\"\f\b\u0000\u0010\u0005*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\b\u001a\u0002H\u0005H\u0016¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u00020\u0004\"\u0012\b\u0000\u0010\u0005*\f\u0012\u0004\u0012\u0002H\u0010\u0012\u0002\b\u00030\u000f\"\u0004\b\u0001\u0010\u00102\u0006\u0010\b\u001a\u0002H\u00052\u0006\u0010\u0011\u001a\u0002H\u0010H\u0016¢\u0006\u0002\u0010\u0012JK\u0010\u0013\u001a\u00020\u0004\"\u0014\b\u0000\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00070\u000f\"\u0004\b\u0001\u0010\u0010\"\u0004\b\u0002\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00070\u0015H\u0016¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/ptrbrynt/kotlin_bloc/core/BlocObserver;", "", "()V", "onChange", "", "B", "Lcom/ptrbrynt/kotlin_bloc/core/BlocBase;", "State", "bloc", "change", "Lcom/ptrbrynt/kotlin_bloc/core/Change;", "(Lcom/ptrbrynt/kotlin_bloc/core/BlocBase;Lcom/ptrbrynt/kotlin_bloc/core/Change;)V", "onCreate", "(Lcom/ptrbrynt/kotlin_bloc/core/BlocBase;)V", "onEvent", "Lcom/ptrbrynt/kotlin_bloc/core/Bloc;", "Event", NotificationCompat.CATEGORY_EVENT, "(Lcom/ptrbrynt/kotlin_bloc/core/Bloc;Ljava/lang/Object;)V", "onTransition", "transition", "Lcom/ptrbrynt/kotlin_bloc/core/Transition;", "(Lcom/ptrbrynt/kotlin_bloc/core/Bloc;Lcom/ptrbrynt/kotlin_bloc/core/Transition;)V", "core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BlocObserver {
    public <B extends BlocBase<State>, State> void onChange(B bloc, Change<State> change) {
        Intrinsics.checkNotNullParameter(bloc, "bloc");
        Intrinsics.checkNotNullParameter(change, "change");
    }

    public <B extends BlocBase<?>> void onCreate(B bloc) {
        Intrinsics.checkNotNullParameter(bloc, "bloc");
    }

    public <B extends Bloc<Event, ?>, Event> void onEvent(B bloc, Event event) {
        Intrinsics.checkNotNullParameter(bloc, "bloc");
    }

    public <B extends Bloc<Event, State>, Event, State> void onTransition(B bloc, Transition<Event, State> transition) {
        Intrinsics.checkNotNullParameter(bloc, "bloc");
        Intrinsics.checkNotNullParameter(transition, "transition");
    }
}
