package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: KeyMapping.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0000\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"commonKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "shortcutModifier", "Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "defaultKeyMapping", "getDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "foundation_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    public static final KeyMapping commonKeyMapping(final Function1<? super KeyEvent, Boolean> function1) {
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$commonKeyMapping$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* renamed from: map-ZmokQxo */
            public KeyCommand mo1304mapZmokQxo(android.view.KeyEvent event) {
                if (function1.invoke(KeyEvent.m5759boximpl(event)).booleanValue() && KeyEvent_androidKt.m5782isShiftPressedZmokQxo(event)) {
                    if (Key.m5468equalsimpl0(KeyEvent_androidKt.m5776getKeyZmokQxo(event), MappedKeys.INSTANCE.m1358getZEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (function1.invoke(KeyEvent.m5759boximpl(event)).booleanValue()) {
                    long m5776getKeyZmokQxo = KeyEvent_androidKt.m5776getKeyZmokQxo(event);
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1336getCEK5gGoQ()) || Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1347getInsertEK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1355getVEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1356getXEK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1333getAEK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1357getYEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1358getZEK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyEvent_androidKt.m5780isCtrlPressedZmokQxo(event)) {
                    return null;
                }
                if (KeyEvent_androidKt.m5782isShiftPressedZmokQxo(event)) {
                    long m5776getKeyZmokQxo2 = KeyEvent_androidKt.m5776getKeyZmokQxo(event);
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1342getDirectionLeftEK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1343getDirectionRightEK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1344getDirectionUpEK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1341getDirectionDownEK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1352getPageUpEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1351getPageDownEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1349getMoveHomeEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1348getMoveEndEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1347getInsertEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                long m5776getKeyZmokQxo3 = KeyEvent_androidKt.m5776getKeyZmokQxo(event);
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1342getDirectionLeftEK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1343getDirectionRightEK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1344getDirectionUpEK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1341getDirectionDownEK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1340getDirectionCenterEK5gGoQ())) {
                    return KeyCommand.CENTER;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1352getPageUpEK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1351getPageDownEK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1349getMoveHomeEK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1348getMoveEndEK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1345getEnterEK5gGoQ()) || Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1350getNumPadEnterEK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1335getBackspaceEK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1339getDeleteEK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1353getPasteEK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1338getCutEK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1337getCopyEK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1354getTabEK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }

    static {
        final KeyMapping commonKeyMapping = commonKeyMapping(new PropertyReference1Impl() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return Boolean.valueOf(KeyEvent_androidKt.m5780isCtrlPressedZmokQxo(((KeyEvent) obj).m5765unboximpl()));
            }
        });
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$2$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* renamed from: map-ZmokQxo */
            public KeyCommand mo1304mapZmokQxo(android.view.KeyEvent event) {
                KeyCommand keyCommand = null;
                if (KeyEvent_androidKt.m5782isShiftPressedZmokQxo(event) && KeyEvent_androidKt.m5780isCtrlPressedZmokQxo(event)) {
                    long m5776getKeyZmokQxo = KeyEvent_androidKt.m5776getKeyZmokQxo(event);
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1342getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1343getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1344getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo, MappedKeys.INSTANCE.m1341getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyEvent_androidKt.m5780isCtrlPressedZmokQxo(event)) {
                    long m5776getKeyZmokQxo2 = KeyEvent_androidKt.m5776getKeyZmokQxo(event);
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1342getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1343getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1344getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1341getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1346getHEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1339getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1335getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_WORD;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo2, MappedKeys.INSTANCE.m1334getBackslashEK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyEvent_androidKt.m5782isShiftPressedZmokQxo(event)) {
                    long m5776getKeyZmokQxo3 = KeyEvent_androidKt.m5776getKeyZmokQxo(event);
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1349getMoveHomeEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_START;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo3, MappedKeys.INSTANCE.m1348getMoveEndEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_END;
                    }
                } else if (KeyEvent_androidKt.m5779isAltPressedZmokQxo(event)) {
                    long m5776getKeyZmokQxo4 = KeyEvent_androidKt.m5776getKeyZmokQxo(event);
                    if (Key.m5468equalsimpl0(m5776getKeyZmokQxo4, MappedKeys.INSTANCE.m1335getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                    } else if (Key.m5468equalsimpl0(m5776getKeyZmokQxo4, MappedKeys.INSTANCE.m1339getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_TO_LINE_END;
                    }
                }
                return keyCommand == null ? KeyMapping.this.mo1304mapZmokQxo(event) : keyCommand;
            }
        };
    }
}
