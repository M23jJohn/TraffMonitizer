package androidx.compose.runtime.tooling;

import androidx.compose.runtime.internal.Utils_androidKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;

/* compiled from: SourceInformation.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0002\u001a\u0012\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\u0007H\u0002\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\u0007H\u0002\u001a\u0012\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t*\u00020\u0007H\u0002\u001a\f\u0010\u000e\u001a\u00020\u0003*\u00020\u0003H\u0002¨\u0006\u000f"}, d2 = {"parseSourceInformation", "Landroidx/compose/runtime/tooling/SourceInformation;", "data", "", "parseSourceInformationInternal", "hasSection", "", "Landroidx/compose/runtime/tooling/SourceInfoParserState;", "parseParameterIndex", "", "Landroidx/compose/runtime/tooling/ParameterSourceInformation;", "parseParameterNames", "parseLocations", "Landroidx/compose/runtime/tooling/LocationSourceInformation;", "replaceComposePrefix", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SourceInformationKt {
    public static final SourceInformation parseSourceInformation(String str) {
        if (str.length() == 0) {
            return null;
        }
        try {
            return parseSourceInformationInternal(str);
        } catch (ParseException e) {
            Utils_androidKt.logError(e.getMessage(), e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final SourceInformation parseSourceInformationInternal(String str) {
        boolean z;
        boolean z2;
        String str2;
        String takeUntil;
        SourceInfoParserState sourceInfoParserState = new SourceInfoParserState(str);
        String str3 = null;
        if (sourceInfoParserState.matches('C')) {
            SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            if (sourceInfoParserState.matches('C')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                z = true;
            } else {
                z = false;
            }
            if (sourceInfoParserState.matches('(')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                str2 = sourceInfoParserState.takeUntil(")");
                sourceInfoParserState.expect(')');
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                z2 = true;
                List<ParameterSourceInformation> emptyList = CollectionsKt.emptyList();
                while (hasSection(sourceInfoParserState)) {
                    char current = sourceInfoParserState.current();
                    if (current == 'N') {
                        emptyList = parseParameterNames(sourceInfoParserState);
                    } else if (current == 'P') {
                        emptyList = parseParameterIndex(sourceInfoParserState);
                    } else {
                        sourceInfoParserState.advance(2);
                        int i = 0;
                        while (true) {
                            if (i > 0 || !sourceInfoParserState.matches(')')) {
                                if (sourceInfoParserState.atEnd()) {
                                    sourceInfoParserState.throwParseError("unexpected end");
                                    throw new KotlinNothingValueException();
                                }
                                if (sourceInfoParserState.matches('(')) {
                                    i++;
                                } else if (sourceInfoParserState.matches(')')) {
                                    i--;
                                }
                                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                            } else {
                                sourceInfoParserState.expect(')');
                                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                                break;
                            }
                        }
                    }
                }
                List<LocationSourceInformation> emptyList2 = CollectionsKt.emptyList();
                if (sourceInfoParserState.matches(':')) {
                    emptyList2 = parseLocations(sourceInfoParserState);
                } else {
                    SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                }
                takeUntil = sourceInfoParserState.takeUntil("#");
                if (takeUntil.length() <= 0) {
                    takeUntil = null;
                }
                if (sourceInfoParserState.matches('#')) {
                    SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                    str3 = sourceInfoParserState.takeUntilEnd();
                }
                String str4 = takeUntil;
                return new SourceInformation(z2, z, str2, str4, emptyList, str3, emptyList2, str);
            }
            z2 = true;
        } else {
            z = false;
            z2 = false;
        }
        str2 = null;
        List<ParameterSourceInformation> emptyList3 = CollectionsKt.emptyList();
        while (hasSection(sourceInfoParserState)) {
        }
        List<LocationSourceInformation> emptyList22 = CollectionsKt.emptyList();
        if (sourceInfoParserState.matches(':')) {
        }
        takeUntil = sourceInfoParserState.takeUntil("#");
        if (takeUntil.length() <= 0) {
        }
        if (sourceInfoParserState.matches('#')) {
        }
        String str42 = takeUntil;
        return new SourceInformation(z2, z, str2, str42, emptyList3, str3, emptyList22, str);
    }

    private static final boolean hasSection(SourceInfoParserState sourceInfoParserState) {
        return sourceInfoParserState.getI() < sourceInfoParserState.getData().length() - 1 && Character.isLetter(sourceInfoParserState.getData().charAt(sourceInfoParserState.getI())) && sourceInfoParserState.getData().charAt(sourceInfoParserState.getI() + 1) == '(';
    }

    private static final List<ParameterSourceInformation> parseParameterIndex(SourceInfoParserState sourceInfoParserState) {
        String str;
        sourceInfoParserState.advance(2);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!sourceInfoParserState.atEnd() && !sourceInfoParserState.matches(')')) {
            if (sourceInfoParserState.matches('!')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                String takeUntil = sourceInfoParserState.takeUntil("!,)");
                if (takeUntil.length() != 0) {
                    int parseInt = Integer.parseInt(takeUntil);
                    int i = 0;
                    while (parseInt > 0) {
                        int size = arrayList.size();
                        int i2 = 0;
                        while (true) {
                            if (i2 < size) {
                                if (((ParameterSourceInformation) arrayList.get(i2)).getSortedIndex() == i) {
                                    i++;
                                    break;
                                }
                                i2++;
                            } else {
                                arrayList.add(new ParameterSourceInformation(i, null, null, 6, null));
                                parseInt--;
                                break;
                            }
                        }
                    }
                } else {
                    z = true;
                }
            } else {
                int takeIntUntil = sourceInfoParserState.takeIntUntil("!:,)");
                if (sourceInfoParserState.matches(':')) {
                    SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                    str = replaceComposePrefix(sourceInfoParserState.takeUntil("!,)"));
                } else {
                    str = null;
                }
                if (z) {
                    int i3 = 0;
                    while (i3 < takeIntUntil) {
                        int size2 = arrayList.size();
                        int i4 = 0;
                        while (true) {
                            if (i4 < size2) {
                                if (((ParameterSourceInformation) arrayList.get(i4)).getSortedIndex() == i3) {
                                    i3++;
                                    break;
                                }
                                i4++;
                            } else {
                                arrayList.add(new ParameterSourceInformation(i3, null, null, 6, null));
                                break;
                            }
                        }
                    }
                    z = false;
                }
                arrayList.add(new ParameterSourceInformation(takeIntUntil, null, str, 2, null));
            }
            if (sourceInfoParserState.matches(',')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            }
        }
        sourceInfoParserState.expect(')');
        SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
        return arrayList;
    }

    private static final List<ParameterSourceInformation> parseParameterNames(SourceInfoParserState sourceInfoParserState) {
        String str;
        sourceInfoParserState.advance(2);
        ArrayList arrayList = new ArrayList();
        while (!sourceInfoParserState.atEnd() && !sourceInfoParserState.matches(')')) {
            String takeUntil = sourceInfoParserState.takeUntil(":,)");
            if (sourceInfoParserState.matches(':')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                str = replaceComposePrefix(sourceInfoParserState.takeUntil(",)"));
            } else {
                str = null;
            }
            arrayList.add(new ParameterSourceInformation(arrayList.size(), takeUntil, str));
            if (sourceInfoParserState.matches(',')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            }
        }
        sourceInfoParserState.expect(')');
        SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
        return arrayList;
    }

    private static final List<LocationSourceInformation> parseLocations(SourceInfoParserState sourceInfoParserState) {
        boolean z;
        Integer num;
        ArrayList arrayList = new ArrayList();
        while (!sourceInfoParserState.atEnd() && !sourceInfoParserState.matches(':')) {
            if (sourceInfoParserState.matches('*')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                z = true;
            } else {
                z = false;
            }
            Integer valueOf = !sourceInfoParserState.matches('@') ? Integer.valueOf(sourceInfoParserState.takeIntUntil("@") + 1) : null;
            SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            int takeIntUntil = sourceInfoParserState.takeIntUntil("L,:");
            if (sourceInfoParserState.matches('L')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                num = Integer.valueOf(sourceInfoParserState.takeIntUntil(",:"));
            } else {
                num = null;
            }
            arrayList.add(new LocationSourceInformation(valueOf != null ? valueOf.intValue() : -1, takeIntUntil, num != null ? num.intValue() : -1, z));
            if (sourceInfoParserState.matches(',')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            }
        }
        SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
        return arrayList;
    }

    private static final String replaceComposePrefix(String str) {
        return StringsKt.replaceFirst$default(str, "c#", "androidx.compose.", false, 4, (Object) null);
    }
}
