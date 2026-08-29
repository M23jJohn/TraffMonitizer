package com.traffmonetizer.client.preferences;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKt;
import com.traffmonetizer.client.preferences.model.AppPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PreferencesRepository.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/traffmonetizer/client/preferences/PreferencesRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appPreferencesFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/traffmonetizer/client/preferences/model/AppPreferences;", "appPreferences", "getAppPreferences", "()Lkotlinx/coroutines/flow/Flow;", "setProxyActive", "", "active", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setWifiOnly", "value", "setDebugMode", "saveToken", "token", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreferencesRepository {
    public static final int $stable = 8;
    private final Flow<AppPreferences> appPreferences;
    private final Flow<AppPreferences> appPreferencesFlow;
    private final Context context;

    public PreferencesRepository(Context context) {
        DataStore appPreferencesDataStore;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        appPreferencesDataStore = PreferencesRepositoryKt.getAppPreferencesDataStore(context);
        final Flow data = appPreferencesDataStore.getData();
        Flow<AppPreferences> flow = new Flow<AppPreferences>() { // from class: com.traffmonetizer.client.preferences.PreferencesRepository$special$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 2, 0}, xi = 48)
            /* renamed from: com.traffmonetizer.client.preferences.PreferencesRepository$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
                @DebugMetadata(c = "com.traffmonetizer.client.preferences.PreferencesRepository$special$$inlined$map$1$2", f = "PreferencesRepository.kt", i = {0, 0, 0, 0, 0}, l = {219}, m = "emit", n = {"value", "$completion", "value", "$this$map_u24lambda_u245", "$i$a$-unsafeTransform-FlowKt__TransformKt$map$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
                /* renamed from: com.traffmonetizer.client.preferences.PreferencesRepository$special$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    int i;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                            Object obj2 = anonymousClass1.result;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            i = anonymousClass1.label;
                            if (i != 0) {
                                ResultKt.throwOnFailure(obj2);
                                FlowCollector flowCollector = this.$this_unsafeFlow;
                                Preferences preferences = (Preferences) obj;
                                String str = (String) preferences.get(PreferenceKeys.INSTANCE.getTOKEN());
                                Boolean bool = (Boolean) preferences.get(PreferenceKeys.INSTANCE.getPROXY_ACTIVE());
                                boolean booleanValue = bool != null ? bool.booleanValue() : false;
                                Boolean bool2 = (Boolean) preferences.get(PreferenceKeys.INSTANCE.getWIFI_ONLY());
                                boolean booleanValue2 = bool2 != null ? bool2.booleanValue() : true;
                                Boolean bool3 = (Boolean) preferences.get(PreferenceKeys.INSTANCE.getDEBUG_MODE());
                                AppPreferences appPreferences = new AppPreferences(str, booleanValue, booleanValue2, bool3 != null ? bool3.booleanValue() : false);
                                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                                anonymousClass1.I$0 = 0;
                                anonymousClass1.label = 1;
                                if (flowCollector.emit(appPreferences, anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                int i2 = anonymousClass1.I$0;
                                Object obj3 = anonymousClass1.L$2;
                                Object obj4 = anonymousClass1.L$0;
                                ResultKt.throwOnFailure(obj2);
                            }
                            return Unit.INSTANCE;
                        }
                    }
                    anonymousClass1 = new AnonymousClass1(continuation);
                    Object obj22 = anonymousClass1.result;
                    Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = anonymousClass1.label;
                    if (i != 0) {
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super AppPreferences> flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
        this.appPreferencesFlow = flow;
        this.appPreferences = flow;
    }

    public final Flow<AppPreferences> getAppPreferences() {
        return this.appPreferences;
    }

    public final Object setProxyActive(boolean z, Continuation<? super Unit> continuation) {
        DataStore appPreferencesDataStore;
        appPreferencesDataStore = PreferencesRepositoryKt.getAppPreferencesDataStore(this.context);
        Object edit = PreferencesKt.edit(appPreferencesDataStore, new PreferencesRepository$setProxyActive$2(z, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object setWifiOnly(boolean z, Continuation<? super Unit> continuation) {
        DataStore appPreferencesDataStore;
        appPreferencesDataStore = PreferencesRepositoryKt.getAppPreferencesDataStore(this.context);
        Object edit = PreferencesKt.edit(appPreferencesDataStore, new PreferencesRepository$setWifiOnly$2(z, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object setDebugMode(boolean z, Continuation<? super Unit> continuation) {
        DataStore appPreferencesDataStore;
        appPreferencesDataStore = PreferencesRepositoryKt.getAppPreferencesDataStore(this.context);
        Object edit = PreferencesKt.edit(appPreferencesDataStore, new PreferencesRepository$setDebugMode$2(z, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object saveToken(String str, Continuation<? super Unit> continuation) {
        DataStore appPreferencesDataStore;
        appPreferencesDataStore = PreferencesRepositoryKt.getAppPreferencesDataStore(this.context);
        Object edit = PreferencesKt.edit(appPreferencesDataStore, new PreferencesRepository$saveToken$2(str, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object resetToken(Continuation<? super Unit> continuation) {
        DataStore appPreferencesDataStore;
        appPreferencesDataStore = PreferencesRepositoryKt.getAppPreferencesDataStore(this.context);
        Object edit = PreferencesKt.edit(appPreferencesDataStore, new PreferencesRepository$resetToken$2(null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }
}
