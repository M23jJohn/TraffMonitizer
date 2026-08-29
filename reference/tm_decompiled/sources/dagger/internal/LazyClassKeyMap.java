package dagger.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public final class LazyClassKeyMap<V> implements Map<Class<?>, V> {
    private final Map<String, V> delegate;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(Class<?> key, Object value) {
        return put2(key, (Class<?>) value);
    }

    public static <V> Map<Class<?>, V> of(Map<String, V> delegate) {
        return new LazyClassKeyMap(delegate);
    }

    private LazyClassKeyMap(Map<String, V> delegate) {
        this.delegate = delegate;
    }

    @Override // java.util.Map
    public V get(Object key) {
        if (!(key instanceof Class)) {
            throw new IllegalArgumentException("Key must be a class");
        }
        return this.delegate.get(((Class) key).getName());
    }

    @Override // java.util.Map
    public Set<Class<?>> keySet() {
        throw new UnsupportedOperationException("Maps created with @LazyClassKey do not support usage of keySet(). Consider @ClassKey instead.");
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return this.delegate.values();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        if (!(key instanceof Class)) {
            throw new IllegalArgumentException("Key must be a class");
        }
        return this.delegate.containsKey(((Class) key).getName());
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return this.delegate.containsValue(value);
    }

    @Override // java.util.Map
    public int size() {
        return this.delegate.size();
    }

    @Override // java.util.Map
    public Set<Map.Entry<Class<?>, V>> entrySet() {
        throw new UnsupportedOperationException("Maps created with @LazyClassKey do not support usage of entrySet(). Consider @ClassKey instead.");
    }

    @Override // java.util.Map
    public V remove(Object key) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    /* renamed from: put, reason: avoid collision after fix types in other method */
    public V put2(Class<?> key, V value) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends Class<?>, ? extends V> map) {
        throw new UnsupportedOperationException("Dagger map bindings are immutable");
    }

    public static class MapFactory<V> implements Factory<Map<Class<?>, V>> {
        Factory<Map<String, V>> delegate;

        public static <V> MapFactory<V> of(Factory<Map<String, V>> delegate) {
            return new MapFactory<>(delegate);
        }

        private MapFactory(Factory<Map<String, V>> delegate) {
            this.delegate = delegate;
        }

        @Override // javax.inject.Provider, jakarta.inject.Provider
        public Map<Class<?>, V> get() {
            return LazyClassKeyMap.of(this.delegate.get());
        }
    }

    public static class MapProviderFactory<V> implements Factory<Map<Class<?>, Provider<V>>> {
        Factory<Map<String, Provider<V>>> delegate;

        public static <V> MapProviderFactory<V> of(Factory<Map<String, Provider<V>>> delegate) {
            return new MapProviderFactory<>(delegate);
        }

        private MapProviderFactory(Factory<Map<String, Provider<V>>> delegate) {
            this.delegate = delegate;
        }

        @Override // javax.inject.Provider, jakarta.inject.Provider
        public Map<Class<?>, Provider<V>> get() {
            return LazyClassKeyMap.of(this.delegate.get());
        }
    }
}
