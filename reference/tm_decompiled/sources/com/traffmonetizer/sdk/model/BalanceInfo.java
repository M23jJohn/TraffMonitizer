package com.traffmonetizer.sdk.model;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/traffmonetizer/sdk/model/BalanceInfo;", "", "balance", "", "last30Days", "(DD)V", "getBalance", "()D", "getLast30Days", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "TraffmonetizerSDK-v1.2.11_internalRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BalanceInfo {
    private final double balance;
    private final double last30Days;

    public BalanceInfo(double d, double d2) {
        this.balance = d;
        this.last30Days = d2;
    }

    public static /* synthetic */ BalanceInfo copy$default(BalanceInfo balanceInfo, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = balanceInfo.balance;
        }
        if ((i & 2) != 0) {
            d2 = balanceInfo.last30Days;
        }
        return balanceInfo.copy(d, d2);
    }

    /* renamed from: component1, reason: from getter */
    public final double getBalance() {
        return this.balance;
    }

    /* renamed from: component2, reason: from getter */
    public final double getLast30Days() {
        return this.last30Days;
    }

    public final BalanceInfo copy(double balance, double last30Days) {
        return new BalanceInfo(balance, last30Days);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BalanceInfo)) {
            return false;
        }
        BalanceInfo balanceInfo = (BalanceInfo) other;
        return Intrinsics.areEqual((Object) Double.valueOf(this.balance), (Object) Double.valueOf(balanceInfo.balance)) && Intrinsics.areEqual((Object) Double.valueOf(this.last30Days), (Object) Double.valueOf(balanceInfo.last30Days));
    }

    public final double getBalance() {
        return this.balance;
    }

    public final double getLast30Days() {
        return this.last30Days;
    }

    public int hashCode() {
        return UByte$$ExternalSyntheticBackport0.m(this.last30Days) + (UByte$$ExternalSyntheticBackport0.m(this.balance) * 31);
    }

    public String toString() {
        return "BalanceInfo(balance=" + this.balance + ", last30Days=" + this.last30Days + ')';
    }
}
