package dagger.internal;

/* loaded from: classes3.dex */
public final class Preconditions {
    public static <T> T checkNotNull(T reference) {
        reference.getClass();
        return reference;
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(errorMessage);
    }

    public static <T> T checkNotNullFromProvides(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }

    public static <T> T checkNotNullFromComponent(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException("Cannot return null from a non-@Nullable component method");
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object errorMessageArg) {
        if (reference != null) {
            return reference;
        }
        if (errorMessageTemplate.contains("%s")) {
            if (errorMessageTemplate.indexOf("%s") != errorMessageTemplate.lastIndexOf("%s")) {
                throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
            }
            if (errorMessageArg instanceof Class) {
                errorMessageArg = ((Class) errorMessageArg).getCanonicalName();
            }
            throw new NullPointerException(errorMessageTemplate.replace("%s", String.valueOf(errorMessageArg)));
        }
        throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
    }

    public static <T> void checkBuilderRequirement(T requirement, Class<T> clazz) {
        if (requirement != null) {
            return;
        }
        throw new IllegalStateException(clazz.getCanonicalName() + " must be set");
    }

    private Preconditions() {
    }
}
