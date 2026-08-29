package com.ptrbrynt.kotlin_bloc.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Change.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u000e\u0010\n\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0007J\u000e\u0010\u000b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0007J(\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/ptrbrynt/kotlin_bloc/core/Change;", "State", "", "state", "newState", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getNewState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getState", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/ptrbrynt/kotlin_bloc/core/Change;", "equals", "", "other", "hashCode", "", "toString", "", "core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Change<State> {
    private final State newState;
    private final State state;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Change copy$default(Change change, Object obj, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = change.state;
        }
        if ((i & 2) != 0) {
            obj2 = change.newState;
        }
        return change.copy(obj, obj2);
    }

    public final State component1() {
        return this.state;
    }

    public final State component2() {
        return this.newState;
    }

    public final Change<State> copy(State state, State newState) {
        return new Change<>(state, newState);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Change)) {
            return false;
        }
        Change change = (Change) other;
        return Intrinsics.areEqual(this.state, change.state) && Intrinsics.areEqual(this.newState, change.newState);
    }

    public int hashCode() {
        State state = this.state;
        int hashCode = (state == null ? 0 : state.hashCode()) * 31;
        State state2 = this.newState;
        return hashCode + (state2 != null ? state2.hashCode() : 0);
    }

    public String toString() {
        return "Change(state=" + this.state + ", newState=" + this.newState + ')';
    }

    public Change(State state, State state2) {
        this.state = state;
        this.newState = state2;
    }

    public final State getNewState() {
        return this.newState;
    }

    public final State getState() {
        return this.state;
    }
}
