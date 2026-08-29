package dagger.internal;

/* loaded from: classes3.dex */
public final class DelegateFactory<T> implements Factory<T> {
    private Provider<T> delegate;

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public T get() {
        Provider<T> provider = this.delegate;
        if (provider == null) {
            throw new IllegalStateException();
        }
        return provider.get();
    }

    @Deprecated
    public void setDelegatedProvider(Provider<T> delegate) {
        setDelegate((Provider) this, (Provider) delegate);
    }

    @Deprecated
    public void setDelegatedProvider(javax.inject.Provider<T> delegate) {
        setDelegatedProvider((Provider) Providers.asDaggerProvider(delegate));
    }

    public static <T> void setDelegate(Provider<T> delegateFactory, Provider<T> delegate) {
        setDelegateInternal((DelegateFactory) delegateFactory, delegate);
    }

    @Deprecated
    public static <T> void setDelegate(javax.inject.Provider<T> delegateFactory, javax.inject.Provider<T> delegate) {
        setDelegateInternal((DelegateFactory) delegateFactory, Providers.asDaggerProvider(delegate));
    }

    private static <T> void setDelegateInternal(DelegateFactory<T> delegateFactory, Provider<T> delegate) {
        Preconditions.checkNotNull(delegate);
        if (((DelegateFactory) delegateFactory).delegate != null) {
            throw new IllegalStateException();
        }
        ((DelegateFactory) delegateFactory).delegate = delegate;
    }

    Provider<T> getDelegate() {
        return (Provider) Preconditions.checkNotNull(this.delegate);
    }
}
