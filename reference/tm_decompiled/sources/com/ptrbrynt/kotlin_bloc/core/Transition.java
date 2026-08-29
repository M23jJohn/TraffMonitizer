package com.ptrbrynt.kotlin_bloc.core;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0001¢\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\tJ\u000e\u0010\u000e\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ\u000e\u0010\u000f\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\tJ8\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00012\b\b\u0002\u0010\u0005\u001a\u00028\u00002\b\b\u0002\u0010\u0006\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0004\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/ptrbrynt/kotlin_bloc/core/Transition;", "Event", "State", "", "state", NotificationCompat.CATEGORY_EVENT, "newState", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getEvent", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getNewState", "getState", "component1", "component2", "component3", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/ptrbrynt/kotlin_bloc/core/Transition;", "equals", "", "other", "hashCode", "", "toString", "", "core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Transition<Event, State> {
    private final Event event;
    private final State newState;
    private final State state;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Transition copy$default(Transition transition, Object obj, Object obj2, Object obj3, int i, Object obj4) {
        if ((i & 1) != 0) {
            obj = transition.state;
        }
        if ((i & 2) != 0) {
            obj2 = transition.event;
        }
        if ((i & 4) != 0) {
            obj3 = transition.newState;
        }
        return transition.copy(obj, obj2, obj3);
    }

    public final State component1() {
        return this.state;
    }

    public final Event component2() {
        return this.event;
    }

    public final State component3() {
        return this.newState;
    }

    public final Transition<Event, State> copy(State state, Event event, State newState) {
        return new Transition<>(state, event, newState);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Transition)) {
            return false;
        }
        Transition transition = (Transition) other;
        return Intrinsics.areEqual(this.state, transition.state) && Intrinsics.areEqual(this.event, transition.event) && Intrinsics.areEqual(this.newState, transition.newState);
    }

    public int hashCode() {
        State state = this.state;
        int hashCode = (state == null ? 0 : state.hashCode()) * 31;
        Event event = this.event;
        int hashCode2 = (hashCode + (event == null ? 0 : event.hashCode())) * 31;
        State state2 = this.newState;
        return hashCode2 + (state2 != null ? state2.hashCode() : 0);
    }

    public String toString() {
        return "Transition(state=" + this.state + ", event=" + this.event + ", newState=" + this.newState + ')';
    }

    public Transition(State state, Event event, State state2) {
        this.state = state;
        this.event = event;
        this.newState = state2;
    }

    public final Event getEvent() {
        return this.event;
    }

    public final State getNewState() {
        return this.newState;
    }

    public final State getState() {
        return this.state;
    }
}
