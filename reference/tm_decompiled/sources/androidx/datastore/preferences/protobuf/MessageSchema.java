package androidx.datastore.preferences.protobuf;

import androidx.core.view.MotionEventCompat;
import androidx.datastore.preferences.protobuf.ArrayDecoders;
import androidx.datastore.preferences.protobuf.ByteString;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.MapEntryLite;
import androidx.datastore.preferences.protobuf.WireFormat;
import androidx.datastore.preferences.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

@CheckReturnValue
/* loaded from: classes3.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int CHECK_INITIALIZED_BIT = 1024;
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int HAS_HAS_BIT = 4096;
    private static final int INTS_PER_FIELD = 3;
    private static final int LEGACY_ENUM_IS_CLOSED_BIT = 2048;
    private static final int LEGACY_ENUM_IS_CLOSED_MASK = Integer.MIN_VALUE;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_BIT = 256;
    private static final int REQUIRED_MASK = 268435456;
    private static final int UTF8_CHECK_BIT = 512;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final int repeatedFieldOffsetStart;
    private final ProtoSyntax syntax;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private static boolean isEnforceUtf8(int value) {
        return (value & ENFORCE_UTF8_MASK) != 0;
    }

    private static boolean isLegacyEnumIsClosed(int value) {
        return (value & Integer.MIN_VALUE) != 0;
    }

    private static boolean isRequired(int value) {
        return (value & REQUIRED_MASK) != 0;
    }

    private static long offset(int value) {
        return value & 1048575;
    }

    private static int type(int value) {
        return (value & FIELD_TYPE_MASK) >>> 20;
    }

    private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, ProtoSyntax syntax, boolean useCachedSizeField, int[] intArray, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = buffer;
        this.objects = objects;
        this.minFieldNumber = minFieldNumber;
        this.maxFieldNumber = maxFieldNumber;
        this.lite = defaultInstance instanceof GeneratedMessageLite;
        this.syntax = syntax;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(defaultInstance);
        this.useCachedSizeField = useCachedSizeField;
        this.intArray = intArray;
        this.checkInitializedCount = checkInitialized;
        this.repeatedFieldOffsetStart = mapFieldPositions;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
        this.mapFieldSchema = mapFieldSchema;
    }

    static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0253  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int i;
        int charAt;
        int i2;
        int i3;
        int i4;
        int i5;
        int[] iArr;
        int i6;
        int i7;
        int i8;
        char charAt2;
        int i9;
        char charAt3;
        int i10;
        char charAt4;
        int i11;
        char charAt5;
        int i12;
        char charAt6;
        int i13;
        char charAt7;
        int i14;
        char charAt8;
        int i15;
        char charAt9;
        int i16;
        int i17;
        int i18;
        int i19;
        int objectFieldOffset;
        String str;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        java.lang.reflect.Field reflectField;
        char charAt10;
        int i26;
        int i27;
        Object obj;
        java.lang.reflect.Field reflectField2;
        Object obj2;
        java.lang.reflect.Field reflectField3;
        int i28;
        char charAt11;
        int i29;
        char charAt12;
        int i30;
        char charAt13;
        int i31;
        char charAt14;
        String stringInfo = messageInfo.getStringInfo();
        int length = stringInfo.length();
        char c = 55296;
        if (stringInfo.charAt(0) >= 55296) {
            int i32 = 1;
            while (true) {
                i = i32 + 1;
                if (stringInfo.charAt(i32) < 55296) {
                    break;
                }
                i32 = i;
            }
        } else {
            i = 1;
        }
        int i33 = i + 1;
        int charAt15 = stringInfo.charAt(i);
        if (charAt15 >= 55296) {
            int i34 = charAt15 & 8191;
            int i35 = 13;
            while (true) {
                i31 = i33 + 1;
                charAt14 = stringInfo.charAt(i33);
                if (charAt14 < 55296) {
                    break;
                }
                i34 |= (charAt14 & 8191) << i35;
                i35 += 13;
                i33 = i31;
            }
            charAt15 = i34 | (charAt14 << i35);
            i33 = i31;
        }
        if (charAt15 == 0) {
            i4 = 0;
            charAt = 0;
            i3 = 0;
            i7 = 0;
            i2 = 0;
            i6 = 0;
            iArr = EMPTY_INT_ARRAY;
            i5 = 0;
        } else {
            int i36 = i33 + 1;
            int charAt16 = stringInfo.charAt(i33);
            if (charAt16 >= 55296) {
                int i37 = charAt16 & 8191;
                int i38 = 13;
                while (true) {
                    i15 = i36 + 1;
                    charAt9 = stringInfo.charAt(i36);
                    if (charAt9 < 55296) {
                        break;
                    }
                    i37 |= (charAt9 & 8191) << i38;
                    i38 += 13;
                    i36 = i15;
                }
                charAt16 = i37 | (charAt9 << i38);
                i36 = i15;
            }
            int i39 = i36 + 1;
            int charAt17 = stringInfo.charAt(i36);
            if (charAt17 >= 55296) {
                int i40 = charAt17 & 8191;
                int i41 = 13;
                while (true) {
                    i14 = i39 + 1;
                    charAt8 = stringInfo.charAt(i39);
                    if (charAt8 < 55296) {
                        break;
                    }
                    i40 |= (charAt8 & 8191) << i41;
                    i41 += 13;
                    i39 = i14;
                }
                charAt17 = i40 | (charAt8 << i41);
                i39 = i14;
            }
            int i42 = i39 + 1;
            int charAt18 = stringInfo.charAt(i39);
            if (charAt18 >= 55296) {
                int i43 = charAt18 & 8191;
                int i44 = 13;
                while (true) {
                    i13 = i42 + 1;
                    charAt7 = stringInfo.charAt(i42);
                    if (charAt7 < 55296) {
                        break;
                    }
                    i43 |= (charAt7 & 8191) << i44;
                    i44 += 13;
                    i42 = i13;
                }
                charAt18 = i43 | (charAt7 << i44);
                i42 = i13;
            }
            int i45 = i42 + 1;
            int charAt19 = stringInfo.charAt(i42);
            if (charAt19 >= 55296) {
                int i46 = charAt19 & 8191;
                int i47 = 13;
                while (true) {
                    i12 = i45 + 1;
                    charAt6 = stringInfo.charAt(i45);
                    if (charAt6 < 55296) {
                        break;
                    }
                    i46 |= (charAt6 & 8191) << i47;
                    i47 += 13;
                    i45 = i12;
                }
                charAt19 = i46 | (charAt6 << i47);
                i45 = i12;
            }
            int i48 = i45 + 1;
            charAt = stringInfo.charAt(i45);
            if (charAt >= 55296) {
                int i49 = charAt & 8191;
                int i50 = 13;
                while (true) {
                    i11 = i48 + 1;
                    charAt5 = stringInfo.charAt(i48);
                    if (charAt5 < 55296) {
                        break;
                    }
                    i49 |= (charAt5 & 8191) << i50;
                    i50 += 13;
                    i48 = i11;
                }
                charAt = i49 | (charAt5 << i50);
                i48 = i11;
            }
            int i51 = i48 + 1;
            int charAt20 = stringInfo.charAt(i48);
            if (charAt20 >= 55296) {
                int i52 = charAt20 & 8191;
                int i53 = 13;
                while (true) {
                    i10 = i51 + 1;
                    charAt4 = stringInfo.charAt(i51);
                    if (charAt4 < 55296) {
                        break;
                    }
                    i52 |= (charAt4 & 8191) << i53;
                    i53 += 13;
                    i51 = i10;
                }
                charAt20 = i52 | (charAt4 << i53);
                i51 = i10;
            }
            int i54 = i51 + 1;
            int charAt21 = stringInfo.charAt(i51);
            if (charAt21 >= 55296) {
                int i55 = charAt21 & 8191;
                int i56 = 13;
                while (true) {
                    i9 = i54 + 1;
                    charAt3 = stringInfo.charAt(i54);
                    if (charAt3 < 55296) {
                        break;
                    }
                    i55 |= (charAt3 & 8191) << i56;
                    i56 += 13;
                    i54 = i9;
                }
                charAt21 = i55 | (charAt3 << i56);
                i54 = i9;
            }
            int i57 = i54 + 1;
            int charAt22 = stringInfo.charAt(i54);
            if (charAt22 >= 55296) {
                int i58 = charAt22 & 8191;
                int i59 = 13;
                while (true) {
                    i8 = i57 + 1;
                    charAt2 = stringInfo.charAt(i57);
                    if (charAt2 < 55296) {
                        break;
                    }
                    i58 |= (charAt2 & 8191) << i59;
                    i59 += 13;
                    i57 = i8;
                }
                charAt22 = i58 | (charAt2 << i59);
                i57 = i8;
            }
            int[] iArr2 = new int[charAt22 + charAt20 + charAt21];
            i2 = (charAt16 * 2) + charAt17;
            int i60 = charAt20;
            i3 = charAt18;
            i4 = i60;
            i5 = charAt16;
            iArr = iArr2;
            i6 = charAt22;
            i33 = i57;
            i7 = charAt19;
        }
        Unsafe unsafe = UNSAFE;
        Object[] objects = messageInfo.getObjects();
        Class<?> cls = messageInfo.getDefaultInstance().getClass();
        int[] iArr3 = new int[charAt * 3];
        Object[] objArr = new Object[charAt * 2];
        int i61 = i6 + i4;
        int i62 = i61;
        int i63 = i6;
        int i64 = 0;
        int i65 = 0;
        while (i33 < length) {
            int i66 = i33 + 1;
            int charAt23 = stringInfo.charAt(i33);
            if (charAt23 >= c) {
                int i67 = charAt23 & 8191;
                int i68 = i66;
                int i69 = 13;
                while (true) {
                    i30 = i68 + 1;
                    charAt13 = stringInfo.charAt(i68);
                    if (charAt13 < c) {
                        break;
                    }
                    i67 |= (charAt13 & 8191) << i69;
                    i69 += 13;
                    i68 = i30;
                }
                charAt23 = i67 | (charAt13 << i69);
                i16 = i30;
            } else {
                i16 = i66;
            }
            int i70 = i16 + 1;
            int charAt24 = stringInfo.charAt(i16);
            if (charAt24 >= c) {
                int i71 = charAt24 & 8191;
                int i72 = i70;
                int i73 = 13;
                while (true) {
                    i29 = i72 + 1;
                    charAt12 = stringInfo.charAt(i72);
                    if (charAt12 < c) {
                        break;
                    }
                    i71 |= (charAt12 & 8191) << i73;
                    i73 += 13;
                    i72 = i29;
                }
                charAt24 = i71 | (charAt12 << i73);
                i17 = i29;
            } else {
                i17 = i70;
            }
            int i74 = charAt24 & 255;
            int i75 = length;
            if ((charAt24 & 1024) != 0) {
                iArr[i64] = i65;
                i64++;
            }
            int[] iArr4 = iArr3;
            if (i74 >= ONEOF_TYPE_OFFSET) {
                int i76 = i17 + 1;
                int charAt25 = stringInfo.charAt(i17);
                char c2 = 55296;
                if (charAt25 >= 55296) {
                    int i77 = charAt25 & 8191;
                    int i78 = 13;
                    while (true) {
                        i28 = i76 + 1;
                        charAt11 = stringInfo.charAt(i76);
                        if (charAt11 < c2) {
                            break;
                        }
                        i77 |= (charAt11 & 8191) << i78;
                        i78 += 13;
                        i76 = i28;
                        c2 = 55296;
                    }
                    charAt25 = i77 | (charAt11 << i78);
                    i76 = i28;
                }
                int i79 = i74 - 51;
                int i80 = i76;
                if (i79 == 9 || i79 == 17) {
                    i27 = i2 + 1;
                    objArr[((i65 / 3) * 2) + 1] = objects[i2];
                } else {
                    if (i79 == 12 && (messageInfo.getSyntax().equals(ProtoSyntax.PROTO2) || (charAt24 & 2048) != 0)) {
                        i27 = i2 + 1;
                        objArr[((i65 / 3) * 2) + 1] = objects[i2];
                    }
                    int i81 = charAt25 * 2;
                    obj = objects[i81];
                    if (!(obj instanceof java.lang.reflect.Field)) {
                        reflectField2 = (java.lang.reflect.Field) obj;
                    } else {
                        reflectField2 = reflectField(cls, (String) obj);
                        objects[i81] = reflectField2;
                    }
                    int objectFieldOffset2 = (int) unsafe.objectFieldOffset(reflectField2);
                    int i82 = i81 + 1;
                    obj2 = objects[i82];
                    if (!(obj2 instanceof java.lang.reflect.Field)) {
                        reflectField3 = (java.lang.reflect.Field) obj2;
                    } else {
                        reflectField3 = reflectField(cls, (String) obj2);
                        objects[i82] = reflectField3;
                    }
                    str = stringInfo;
                    i24 = (int) unsafe.objectFieldOffset(reflectField3);
                    i25 = objectFieldOffset2;
                    i23 = 0;
                    i18 = charAt23;
                    i33 = i80;
                }
                i2 = i27;
                int i812 = charAt25 * 2;
                obj = objects[i812];
                if (!(obj instanceof java.lang.reflect.Field)) {
                }
                int objectFieldOffset22 = (int) unsafe.objectFieldOffset(reflectField2);
                int i822 = i812 + 1;
                obj2 = objects[i822];
                if (!(obj2 instanceof java.lang.reflect.Field)) {
                }
                str = stringInfo;
                i24 = (int) unsafe.objectFieldOffset(reflectField3);
                i25 = objectFieldOffset22;
                i23 = 0;
                i18 = charAt23;
                i33 = i80;
            } else {
                int i83 = i2 + 1;
                java.lang.reflect.Field reflectField4 = reflectField(cls, (String) objects[i2]);
                if (i74 == 9 || i74 == 17) {
                    i18 = charAt23;
                    objArr[((i65 / 3) * 2) + 1] = reflectField4.getType();
                } else {
                    if (i74 == 27 || i74 == 49) {
                        i18 = charAt23;
                        i26 = i2 + 2;
                        objArr[((i65 / 3) * 2) + 1] = objects[i83];
                    } else if (i74 == 12 || i74 == 30 || i74 == 44) {
                        i18 = charAt23;
                        if (messageInfo.getSyntax() == ProtoSyntax.PROTO2 || (charAt24 & 2048) != 0) {
                            i26 = i2 + 2;
                            objArr[((i65 / 3) * 2) + 1] = objects[i83];
                        }
                    } else if (i74 == 50) {
                        int i84 = i63 + 1;
                        iArr[i63] = i65;
                        int i85 = (i65 / 3) * 2;
                        int i86 = i2 + 2;
                        objArr[i85] = objects[i83];
                        if ((charAt24 & 2048) != 0) {
                            i19 = i2 + 3;
                            objArr[i85 + 1] = objects[i86];
                            i18 = charAt23;
                            i63 = i84;
                        } else {
                            i19 = i86;
                            i63 = i84;
                            i18 = charAt23;
                        }
                        objectFieldOffset = (int) unsafe.objectFieldOffset(reflectField4);
                        if ((charAt24 & 4096) != 0 || i74 > 17) {
                            str = stringInfo;
                            i20 = i19;
                            i21 = 1048575;
                            i22 = i17;
                            i23 = 0;
                        } else {
                            int i87 = i17 + 1;
                            int charAt26 = stringInfo.charAt(i17);
                            if (charAt26 >= 55296) {
                                int i88 = charAt26 & 8191;
                                int i89 = 13;
                                while (true) {
                                    i22 = i87 + 1;
                                    charAt10 = stringInfo.charAt(i87);
                                    if (charAt10 < 55296) {
                                        break;
                                    }
                                    i88 |= (charAt10 & 8191) << i89;
                                    i89 += 13;
                                    i87 = i22;
                                }
                                charAt26 = i88 | (charAt10 << i89);
                            } else {
                                i22 = i87;
                            }
                            int i90 = (i5 * 2) + (charAt26 / 32);
                            Object obj3 = objects[i90];
                            str = stringInfo;
                            if (obj3 instanceof java.lang.reflect.Field) {
                                reflectField = (java.lang.reflect.Field) obj3;
                            } else {
                                reflectField = reflectField(cls, (String) obj3);
                                objects[i90] = reflectField;
                            }
                            i20 = i19;
                            i21 = (int) unsafe.objectFieldOffset(reflectField);
                            i23 = charAt26 % 32;
                        }
                        int i91 = i21;
                        if (i74 >= 18 && i74 <= 49) {
                            iArr[i62] = objectFieldOffset;
                            i62++;
                        }
                        i24 = i91;
                        i25 = objectFieldOffset;
                        i2 = i20;
                        i33 = i22;
                    } else {
                        i18 = charAt23;
                    }
                    i19 = i26;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(reflectField4);
                    if ((charAt24 & 4096) != 0) {
                    }
                    str = stringInfo;
                    i20 = i19;
                    i21 = 1048575;
                    i22 = i17;
                    i23 = 0;
                    int i912 = i21;
                    if (i74 >= 18) {
                        iArr[i62] = objectFieldOffset;
                        i62++;
                    }
                    i24 = i912;
                    i25 = objectFieldOffset;
                    i2 = i20;
                    i33 = i22;
                }
                i19 = i83;
                objectFieldOffset = (int) unsafe.objectFieldOffset(reflectField4);
                if ((charAt24 & 4096) != 0) {
                }
                str = stringInfo;
                i20 = i19;
                i21 = 1048575;
                i22 = i17;
                i23 = 0;
                int i9122 = i21;
                if (i74 >= 18) {
                }
                i24 = i9122;
                i25 = objectFieldOffset;
                i2 = i20;
                i33 = i22;
            }
            int i92 = i65 + 1;
            iArr4[i65] = i18;
            int i93 = i65 + 2;
            int i94 = i24;
            iArr4[i92] = ((charAt24 & 512) != 0 ? ENFORCE_UTF8_MASK : 0) | ((charAt24 & 256) != 0 ? REQUIRED_MASK : 0) | ((charAt24 & 2048) != 0 ? Integer.MIN_VALUE : 0) | (i74 << 20) | i25;
            i65 += 3;
            iArr4[i93] = (i23 << 20) | i94;
            length = i75;
            iArr3 = iArr4;
            stringInfo = str;
            c = 55296;
        }
        return new MessageSchema<>(iArr3, objArr, i3, i7, messageInfo.getDefaultInstance(), messageInfo.getSyntax(), false, iArr, i6, i61, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static java.lang.reflect.Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException unused) {
            java.lang.reflect.Field[] declaredFields = messageClass.getDeclaredFields();
            for (java.lang.reflect.Field field : declaredFields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        int i;
        FieldInfo[] fields = messageInfo.getFields();
        if (fields.length == 0) {
            fieldNumber = 0;
            fieldNumber2 = 0;
        } else {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i2 = 0;
        int i3 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i2++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i3++;
            }
        }
        int[] iArr2 = i2 > 0 ? new int[i2] : null;
        int[] iArr3 = i3 > 0 ? new int[i3] : null;
        int[] checkInitialized = messageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < fields.length) {
            FieldInfo fieldInfo2 = fields[i4];
            int fieldNumber3 = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i5, objArr);
            if (i6 < checkInitialized.length && checkInitialized[i6] == fieldNumber3) {
                checkInitialized[i6] = i5;
                i6++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr2[i7] = i5;
                i7++;
            } else if (fieldInfo2.getType().id() >= 18 && fieldInfo2.getType().id() <= 49) {
                i = i5;
                iArr3[i8] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                i8++;
                i4++;
                i5 = i + 3;
            }
            i = i5;
            i4++;
            i5 = i + 3;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[checkInitialized.length + iArr2.length + iArr3.length];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length + iArr2.length, iArr3.length);
        return new MessageSchema<>(iArr, objArr, fieldNumber, fieldNumber2, messageInfo.getDefaultInstance(), messageInfo.getSyntax(), true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, Object[] objects) {
        int objectFieldOffset;
        int id;
        long objectFieldOffset2;
        int i;
        int i2;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            id = fi.getType().id() + ONEOF_TYPE_OFFSET;
            objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            objectFieldOffset2 = UnsafeUtil.objectFieldOffset(oneof.getCaseField());
        } else {
            FieldType type = fi.getType();
            objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            id = type.id();
            if (!type.isList() && !type.isMap()) {
                java.lang.reflect.Field presenceField = fi.getPresenceField();
                i = presenceField == null ? 1048575 : (int) UnsafeUtil.objectFieldOffset(presenceField);
                i2 = Integer.numberOfTrailingZeros(fi.getPresenceMask());
            } else if (fi.getCachedSizeField() == null) {
                i = 0;
                i2 = 0;
            } else {
                objectFieldOffset2 = UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
            }
            buffer[bufferIndex] = fi.getFieldNumber();
            buffer[bufferIndex + 1] = (fi.isRequired() ? REQUIRED_MASK : 0) | (!fi.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | (id << 20) | objectFieldOffset;
            buffer[bufferIndex + 2] = i | (i2 << 20);
            Class<?> messageFieldClass = fi.getMessageFieldClass();
            if (fi.getMapDefaultEntry() != null) {
                if (messageFieldClass != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                    return;
                } else {
                    if (fi.getEnumVerifier() != null) {
                        objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                        return;
                    }
                    return;
                }
            }
            int i3 = (bufferIndex / 3) * 2;
            objects[i3] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects[i3 + 1] = messageFieldClass;
                return;
            } else {
                if (fi.getEnumVerifier() != null) {
                    objects[i3 + 1] = fi.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        i = (int) objectFieldOffset2;
        i2 = 0;
        buffer[bufferIndex] = fi.getFieldNumber();
        if (!fi.isEnforceUtf8()) {
        }
        buffer[bufferIndex + 1] = (fi.isRequired() ? REQUIRED_MASK : 0) | (!fi.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | (id << 20) | objectFieldOffset;
        buffer[bufferIndex + 2] = i | (i2 << 20);
        Class<?> messageFieldClass2 = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
        }
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public boolean equals(T message, T other) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(message, other, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(message).equals(this.unknownFieldSchema.getFromMessage(other))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(message).equals(this.extensionSchema.getExtensions(other));
        }
        return true;
    }

    private boolean equals(T message, T other, int pos) {
        int typeAndOffsetAt = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (!arePresentForEquals(message, other, pos) || Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) != Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset))) {
                }
                break;
            case 1:
                if (!arePresentForEquals(message, other, pos) || Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) != Float.floatToIntBits(UnsafeUtil.getFloat(other, offset))) {
                }
                break;
            case 2:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong(message, offset) != UnsafeUtil.getLong(other, offset)) {
                }
                break;
            case 3:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong(message, offset) != UnsafeUtil.getLong(other, offset)) {
                }
                break;
            case 4:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt(message, offset) != UnsafeUtil.getInt(other, offset)) {
                }
                break;
            case 5:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong(message, offset) != UnsafeUtil.getLong(other, offset)) {
                }
                break;
            case 6:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt(message, offset) != UnsafeUtil.getInt(other, offset)) {
                }
                break;
            case 7:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getBoolean(message, offset) != UnsafeUtil.getBoolean(other, offset)) {
                }
                break;
            case 8:
                if (!arePresentForEquals(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                }
                break;
            case 9:
                if (!arePresentForEquals(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                }
                break;
            case 10:
                if (!arePresentForEquals(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                }
                break;
            case 11:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt(message, offset) != UnsafeUtil.getInt(other, offset)) {
                }
                break;
            case 12:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt(message, offset) != UnsafeUtil.getInt(other, offset)) {
                }
                break;
            case 13:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt(message, offset) != UnsafeUtil.getInt(other, offset)) {
                }
                break;
            case 14:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong(message, offset) != UnsafeUtil.getLong(other, offset)) {
                }
                break;
            case 15:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getInt(message, offset) != UnsafeUtil.getInt(other, offset)) {
                }
                break;
            case 16:
                if (!arePresentForEquals(message, other, pos) || UnsafeUtil.getLong(message, offset) != UnsafeUtil.getLong(other, offset)) {
                }
                break;
            case 17:
                if (!arePresentForEquals(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                }
                break;
            case ONEOF_TYPE_OFFSET /* 51 */:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                if (!isOneofCaseEqual(message, other, pos) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                }
                break;
        }
        return true;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int hashCode(T message) {
        int i;
        int hashLong;
        int length = this.buffer.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i3);
            int numberAt = numberAt(i3);
            long offset = offset(typeAndOffsetAt);
            int i4 = 37;
            switch (type(typeAndOffsetAt)) {
                case 0:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)));
                    i2 = i + hashLong;
                    break;
                case 1:
                    i = i2 * 53;
                    hashLong = Float.floatToIntBits(UnsafeUtil.getFloat(message, offset));
                    i2 = i + hashLong;
                    break;
                case 2:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 3:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 4:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 5:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 6:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 7:
                    i = i2 * 53;
                    hashLong = Internal.hashBoolean(UnsafeUtil.getBoolean(message, offset));
                    i2 = i + hashLong;
                    break;
                case 8:
                    i = i2 * 53;
                    hashLong = ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    i2 = i + hashLong;
                    break;
                case 9:
                    Object object = UnsafeUtil.getObject(message, offset);
                    if (object != null) {
                        i4 = object.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 10:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 11:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 12:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 13:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 14:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 15:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(message, offset);
                    i2 = i + hashLong;
                    break;
                case 16:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    i2 = i + hashLong;
                    break;
                case 17:
                    Object object2 = UnsafeUtil.getObject(message, offset);
                    if (object2 != null) {
                        i4 = object2.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                case 39:
                case 40:
                case 41:
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                case 44:
                case 45:
                case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                case 48:
                case 49:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 50:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case ONEOF_TYPE_OFFSET /* 51 */:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Float.floatToIntBits(oneofFloatAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashBoolean(oneofBooleanAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(message, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(message, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(message, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.unknownFieldSchema.getFromMessage(message).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.getExtensions(message).hashCode() : hashCode;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, T other) {
        checkMutable(message);
        other.getClass();
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(message, other, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    private void mergeSingleField(T message, T other, int pos) {
        int typeAndOffsetAt = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(pos);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble(message, offset, UnsafeUtil.getDouble(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat(message, offset, UnsafeUtil.getFloat(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean(message, offset, UnsafeUtil.getBoolean(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 9:
                mergeMessage(message, other, pos);
                break;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 17:
                mergeMessage(message, other, pos);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
            case 39:
            case 40:
            case 41:
            case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
            case 44:
            case 45:
            case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(message, other, offset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
                break;
            case ONEOF_TYPE_OFFSET /* 51 */:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(other, numberAt, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, numberAt, pos);
                    break;
                }
                break;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                mergeOneofMessage(message, other, pos);
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(other, numberAt, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, numberAt, pos);
                    break;
                }
                break;
            case 68:
                mergeOneofMessage(message, other, pos);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T targetParent, T sourceParent, int pos) {
        if (isFieldPresent(sourceParent, pos)) {
            long offset = offset(typeAndOffsetAt(pos));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(sourceParent, offset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
            }
            Schema messageFieldSchema = getMessageFieldSchema(pos);
            if (!isFieldPresent(targetParent, pos)) {
                if (!isMutable(object)) {
                    unsafe.putObject(targetParent, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(targetParent, offset, newInstance);
                }
                setFieldPresent(targetParent, pos);
                return;
            }
            Object object2 = unsafe.getObject(targetParent, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(targetParent, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T targetParent, T sourceParent, int pos) {
        int numberAt = numberAt(pos);
        if (isOneofPresent(sourceParent, numberAt, pos)) {
            long offset = offset(typeAndOffsetAt(pos));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(sourceParent, offset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
            }
            Schema messageFieldSchema = getMessageFieldSchema(pos);
            if (!isOneofPresent(targetParent, numberAt, pos)) {
                if (!isMutable(object)) {
                    unsafe.putObject(targetParent, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(targetParent, offset, newInstance);
                }
                setOneofPresent(targetParent, numberAt, pos);
                return;
            }
            Object object2 = unsafe.getObject(targetParent, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(targetParent, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.datastore.preferences.protobuf.Schema
    public int getSerializedSize(T message) {
        int i;
        int computeDoubleSize;
        int computeFloatSize;
        int computeInt64Size;
        int computeSizeFixed64ListNoTag;
        int computeTagSize;
        int computeUInt32SizeNoTag;
        MessageSchema<T> messageSchema = this;
        T t = message;
        Unsafe unsafe = UNSAFE;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        while (i3 < messageSchema.buffer.length) {
            int typeAndOffsetAt = messageSchema.typeAndOffsetAt(i3);
            int type = type(typeAndOffsetAt);
            int numberAt = messageSchema.numberAt(i3);
            int i7 = messageSchema.buffer[i3 + 2];
            int i8 = i7 & i2;
            if (type <= 17) {
                if (i8 != i6) {
                    i4 = i8 == i2 ? 0 : unsafe.getInt(t, i8);
                    i6 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            int i9 = i5;
            long offset = offset(typeAndOffsetAt);
            if (type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) {
                i8 = 0;
            }
            switch (type) {
                case 0:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 1:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeFloatSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i5 = i9 + computeFloatSize;
                        messageSchema = this;
                        t = message;
                        break;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 2:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeInt64Size = CodedOutputStream.computeInt64Size(numberAt, unsafe.getLong(t, offset));
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 3:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeInt64Size = CodedOutputStream.computeUInt64Size(numberAt, unsafe.getLong(t, offset));
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 4:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeInt64Size = CodedOutputStream.computeInt32Size(numberAt, unsafe.getInt(t, offset));
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 5:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeFloatSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i5 = i9 + computeFloatSize;
                        messageSchema = this;
                        t = message;
                        break;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 6:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeFloatSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i5 = i9 + computeFloatSize;
                        messageSchema = this;
                        t = message;
                        break;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 7:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeFloatSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i5 = i9 + computeFloatSize;
                        messageSchema = this;
                        t = message;
                        break;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 8:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        Object object = unsafe.getObject(t, offset);
                        if (object instanceof ByteString) {
                            computeInt64Size = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object);
                        } else {
                            computeInt64Size = CodedOutputStream.computeStringSize(numberAt, (String) object);
                        }
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 9:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(t, offset), messageSchema.getMessageFieldSchema(i3));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 10:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeInt64Size = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(t, offset));
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 11:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeInt64Size = CodedOutputStream.computeUInt32Size(numberAt, unsafe.getInt(t, offset));
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 12:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeInt64Size = CodedOutputStream.computeEnumSize(numberAt, unsafe.getInt(t, offset));
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 13:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeFloatSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i5 = i9 + computeFloatSize;
                        messageSchema = this;
                        t = message;
                        break;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 14:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeFloatSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i5 = i9 + computeFloatSize;
                        messageSchema = this;
                        t = message;
                        break;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 15:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeInt64Size = CodedOutputStream.computeSInt32Size(numberAt, unsafe.getInt(t, offset));
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 16:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeInt64Size = CodedOutputStream.computeSInt64Size(numberAt, unsafe.getLong(t, offset));
                        i5 = i9 + computeInt64Size;
                        messageSchema = this;
                        break;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 17:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(t, offset), messageSchema.getMessageFieldSchema(i3));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 18:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 19:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 20:
                    computeDoubleSize = SchemaUtil.computeSizeInt64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 21:
                    computeDoubleSize = SchemaUtil.computeSizeUInt64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 22:
                    computeDoubleSize = SchemaUtil.computeSizeInt32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 23:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 24:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 25:
                    computeDoubleSize = SchemaUtil.computeSizeBoolList(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 26:
                    computeDoubleSize = SchemaUtil.computeSizeStringList(numberAt, (List) unsafe.getObject(t, offset));
                    i5 = i9 + computeDoubleSize;
                    break;
                case 27:
                    computeDoubleSize = SchemaUtil.computeSizeMessageList(numberAt, (List) unsafe.getObject(t, offset), messageSchema.getMessageFieldSchema(i3));
                    i5 = i9 + computeDoubleSize;
                    break;
                case 28:
                    computeDoubleSize = SchemaUtil.computeSizeByteStringList(numberAt, (List) unsafe.getObject(t, offset));
                    i5 = i9 + computeDoubleSize;
                    break;
                case 29:
                    computeDoubleSize = SchemaUtil.computeSizeUInt32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 30:
                    computeDoubleSize = SchemaUtil.computeSizeEnumList(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 31:
                    computeDoubleSize = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 32:
                    computeDoubleSize = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 33:
                    computeDoubleSize = SchemaUtil.computeSizeSInt32List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 34:
                    computeDoubleSize = SchemaUtil.computeSizeSInt64List(numberAt, (List) unsafe.getObject(t, offset), false);
                    i5 = i9 + computeDoubleSize;
                    break;
                case 35:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 36:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 37:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 39:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 40:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 41:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 44:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 45:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 48:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        i5 = i9 + computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        break;
                    }
                    i5 = i9;
                    break;
                case 49:
                    computeDoubleSize = SchemaUtil.computeSizeGroupList(numberAt, (List) unsafe.getObject(t, offset), messageSchema.getMessageFieldSchema(i3));
                    i5 = i9 + computeDoubleSize;
                    break;
                case 50:
                    computeDoubleSize = messageSchema.mapFieldSchema.getSerializedSize(numberAt, unsafe.getObject(t, offset), messageSchema.getMapFieldDefaultEntry(i3));
                    i5 = i9 + computeDoubleSize;
                    break;
                case ONEOF_TYPE_OFFSET /* 51 */:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 52:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 53:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(t, offset));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 54:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(t, offset));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 55:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(t, offset));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 56:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 57:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 58:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 59:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        Object object2 = unsafe.getObject(t, offset);
                        if (object2 instanceof ByteString) {
                            computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2);
                        } else {
                            computeDoubleSize = CodedOutputStream.computeStringSize(numberAt, (String) object2);
                        }
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(t, offset), messageSchema.getMessageFieldSchema(i3));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(t, offset));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 62:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(t, offset));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 63:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(t, offset));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 64:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 65:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 66:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(t, offset));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 67:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(t, offset));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                case 68:
                    if (messageSchema.isOneofPresent(t, numberAt, i3)) {
                        computeDoubleSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(t, offset), messageSchema.getMessageFieldSchema(i3));
                        i5 = i9 + computeDoubleSize;
                        break;
                    }
                    i5 = i9;
                    break;
                default:
                    i5 = i9;
                    break;
            }
            i3 += 3;
            i2 = 1048575;
        }
        int unknownFieldsSerializedSize = i5 + messageSchema.getUnknownFieldsSerializedSize(messageSchema.unknownFieldSchema, t);
        return messageSchema.hasExtensions ? unknownFieldsSerializedSize + messageSchema.extensionSchema.getExtensions(t).getSerializedSize() : unknownFieldsSerializedSize;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        return schema.getSerializedSize(schema.getFromMessage(message));
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else {
            writeFieldsInAscendingOrder(message, writer);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:201:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrder(T message, Writer writer) throws IOException {
        Map.Entry<?, ?> entry;
        Iterator<Map.Entry<?, Object>> it;
        int length;
        int i;
        boolean z;
        Map.Entry<?, ?> entry2;
        int i2;
        int i3;
        int i4;
        boolean z2;
        MessageSchema<T> messageSchema = this;
        if (messageSchema.hasExtensions) {
            FieldSet<?> extensions = messageSchema.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                Iterator<Map.Entry<?, Object>> it2 = extensions.iterator();
                entry = (Map.Entry) it2.next();
                it = it2;
                length = messageSchema.buffer.length;
                Unsafe unsafe = UNSAFE;
                int i5 = 1048575;
                i = 0;
                int i6 = 0;
                while (i < length) {
                    int typeAndOffsetAt = messageSchema.typeAndOffsetAt(i);
                    int numberAt = messageSchema.numberAt(i);
                    int type = type(typeAndOffsetAt);
                    if (type <= 17) {
                        int i7 = messageSchema.buffer[i + 2];
                        z = true;
                        int i8 = i7 & 1048575;
                        if (i8 != i5) {
                            i6 = i8 == 1048575 ? 0 : unsafe.getInt(message, i8);
                            i5 = i8;
                        }
                        int i9 = 1 << (i7 >>> 20);
                        entry2 = entry;
                        i2 = i5;
                        i3 = i6;
                        i4 = i9;
                    } else {
                        z = true;
                        entry2 = entry;
                        i2 = i5;
                        i3 = i6;
                        i4 = 0;
                    }
                    while (entry2 != null && messageSchema.extensionSchema.extensionNumber(entry2) <= numberAt) {
                        messageSchema.extensionSchema.serializeExtension(writer, entry2);
                        entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    long offset = offset(typeAndOffsetAt);
                    switch (type) {
                        case 0:
                            if (!messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                break;
                            } else {
                                writer.writeDouble(numberAt, doubleAt(message, offset));
                                break;
                            }
                        case 1:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeFloat(numberAt, floatAt(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 2:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeInt64(numberAt, unsafe.getLong(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 3:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeUInt64(numberAt, unsafe.getLong(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 4:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeInt32(numberAt, unsafe.getInt(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 5:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeFixed64(numberAt, unsafe.getLong(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 6:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeFixed32(numberAt, unsafe.getInt(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 7:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeBool(numberAt, booleanAt(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 8:
                            if (!messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                break;
                            } else {
                                messageSchema.writeString(numberAt, unsafe.getObject(message, offset), writer);
                                break;
                            }
                        case 9:
                            if (!messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                break;
                            } else {
                                writer.writeMessage(numberAt, unsafe.getObject(message, offset), messageSchema.getMessageFieldSchema(i));
                                break;
                            }
                        case 10:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 11:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeUInt32(numberAt, unsafe.getInt(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 12:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeEnum(numberAt, unsafe.getInt(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 13:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeSFixed32(numberAt, unsafe.getInt(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 14:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeSFixed64(numberAt, unsafe.getLong(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 15:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeSInt32(numberAt, unsafe.getInt(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 16:
                            if (messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                writer.writeSInt64(numberAt, unsafe.getLong(message, offset));
                            }
                            messageSchema = this;
                            break;
                        case 17:
                            if (!messageSchema.isFieldPresent(message, i, i2, i3, i4)) {
                                break;
                            } else {
                                writer.writeGroup(numberAt, unsafe.getObject(message, offset), messageSchema.getMessageFieldSchema(i));
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, messageSchema.getMessageFieldSchema(i));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer);
                            break;
                        case 29:
                            z2 = false;
                            SchemaUtil.writeUInt32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 30:
                            z2 = false;
                            SchemaUtil.writeEnumList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 31:
                            z2 = false;
                            SchemaUtil.writeSFixed32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 32:
                            z2 = false;
                            SchemaUtil.writeSFixed64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 33:
                            z2 = false;
                            SchemaUtil.writeSInt32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 34:
                            z2 = false;
                            SchemaUtil.writeSInt64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                            SchemaUtil.writeUInt64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                            SchemaUtil.writeBoolList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                            SchemaUtil.writeUInt32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                            SchemaUtil.writeSFixed64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                            SchemaUtil.writeSInt32List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, z);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(messageSchema.numberAt(i), (List) unsafe.getObject(message, offset), writer, messageSchema.getMessageFieldSchema(i));
                            break;
                        case 50:
                            messageSchema.writeMapHelper(writer, numberAt, unsafe.getObject(message, offset), i);
                            break;
                        case ONEOF_TYPE_OFFSET /* 51 */:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(message, offset));
                            }
                            break;
                        case 52:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeFloat(numberAt, oneofFloatAt(message, offset));
                            }
                            break;
                        case 53:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeInt64(numberAt, oneofLongAt(message, offset));
                            }
                            break;
                        case 54:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeUInt64(numberAt, oneofLongAt(message, offset));
                            }
                            break;
                        case 55:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeInt32(numberAt, oneofIntAt(message, offset));
                            }
                            break;
                        case 56:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeFixed64(numberAt, oneofLongAt(message, offset));
                            }
                            break;
                        case 57:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeFixed32(numberAt, oneofIntAt(message, offset));
                            }
                            break;
                        case 58:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeBool(numberAt, oneofBooleanAt(message, offset));
                            }
                            break;
                        case 59:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                messageSchema.writeString(numberAt, unsafe.getObject(message, offset), writer);
                            }
                            break;
                        case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeMessage(numberAt, unsafe.getObject(message, offset), messageSchema.getMessageFieldSchema(i));
                            }
                            break;
                        case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(message, offset));
                            }
                            break;
                        case 62:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeUInt32(numberAt, oneofIntAt(message, offset));
                            }
                            break;
                        case 63:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeEnum(numberAt, oneofIntAt(message, offset));
                            }
                            break;
                        case 64:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(message, offset));
                            }
                            break;
                        case 65:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(message, offset));
                            }
                            break;
                        case 66:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeSInt32(numberAt, oneofIntAt(message, offset));
                            }
                            break;
                        case 67:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeSInt64(numberAt, oneofLongAt(message, offset));
                            }
                            break;
                        case 68:
                            if (messageSchema.isOneofPresent(message, numberAt, i)) {
                                writer.writeGroup(numberAt, unsafe.getObject(message, offset), messageSchema.getMessageFieldSchema(i));
                            }
                            break;
                    }
                    i += 3;
                    i6 = i3;
                    i5 = i2;
                    entry = entry2;
                }
                while (entry != null) {
                    messageSchema.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                messageSchema.writeUnknownInMessageTo(messageSchema.unknownFieldSchema, message, writer);
            }
        }
        entry = null;
        it = null;
        length = messageSchema.buffer.length;
        Unsafe unsafe2 = UNSAFE;
        int i52 = 1048575;
        i = 0;
        int i62 = 0;
        while (i < length) {
        }
        while (entry != null) {
        }
        messageSchema.writeUnknownInMessageTo(messageSchema.unknownFieldSchema, message, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:275:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it;
        Map.Entry<?, ?> entry;
        int length;
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                it = extensions.descendingIterator();
                entry = (Map.Entry) it.next();
                for (length = this.buffer.length - 3; length >= 0; length -= 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(length);
                    int numberAt = numberAt(length);
                    while (entry != null && this.extensionSchema.extensionNumber(entry) > numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (isFieldPresent(message, length)) {
                                writer.writeDouble(numberAt, doubleAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(message, length)) {
                                writer.writeFloat(numberAt, floatAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(message, length)) {
                                writer.writeInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(message, length)) {
                                writer.writeUInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(message, length)) {
                                writer.writeInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(message, length)) {
                                writer.writeFixed64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(message, length)) {
                                writer.writeFixed32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(message, length)) {
                                writer.writeBool(numberAt, booleanAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (isFieldPresent(message, length)) {
                                writeString(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (isFieldPresent(message, length)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (isFieldPresent(message, length)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (isFieldPresent(message, length)) {
                                writer.writeUInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(message, length)) {
                                writer.writeEnum(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(message, length)) {
                                writer.writeSFixed32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(message, length)) {
                                writer.writeSFixed64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(message, length)) {
                                writer.writeSInt32(numberAt, intAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(message, length)) {
                                writer.writeSInt64(numberAt, longAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (isFieldPresent(message, length)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(length), (List) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), length);
                            break;
                        case ONEOF_TYPE_OFFSET /* 51 */:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeFloat(numberAt, oneofFloatAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeUInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeFixed64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeFixed32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeBool(numberAt, oneofBooleanAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (isOneofPresent(message, numberAt, length)) {
                                writeString(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeUInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeEnum(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeSInt32(numberAt, oneofIntAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeSInt64(numberAt, oneofLongAt(message, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (isOneofPresent(message, numberAt, length)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(message, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
            }
        }
        it = null;
        entry = null;
        while (length >= 0) {
        }
        while (entry != null) {
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        extensionRegistry.getClass();
        checkMutable(message);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private <UT, UB, ET extends androidx.datastore.preferences.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema<UT, UB> r18, androidx.datastore.preferences.protobuf.ExtensionSchema<ET> r19, T r20, androidx.datastore.preferences.protobuf.Reader r21, androidx.datastore.preferences.protobuf.ExtensionRegistryLite r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1976
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema, androidx.datastore.preferences.protobuf.ExtensionSchema, java.lang.Object, androidx.datastore.preferences.protobuf.Reader, androidx.datastore.preferences.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) message;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = newInstance;
        return newInstance;
    }

    /* renamed from: androidx.datastore.preferences.protobuf.MessageSchema$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int decodeVarint64 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return decodeVarint64;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int decodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return decodeVarint32;
            case 12:
            case 13:
                int decodeVarint642 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return decodeVarint642;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) messageType), data, position, limit, registers);
            case 15:
                int decodeVarint322 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return decodeVarint322;
            case 16:
                int decodeVarint643 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return decodeVarint643;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> target, ArrayDecoders.Registers registers) throws IOException {
        int decodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
        int i = registers.int1;
        if (i < 0 || i > limit - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i2 = decodeVarint32 + i;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (decodeVarint32 < i2) {
            int i3 = decodeVarint32 + 1;
            int i4 = data[decodeVarint32];
            if (i4 < 0) {
                i3 = ArrayDecoders.decodeVarint32(i4, data, i3, registers);
                i4 = registers.int1;
            }
            int i5 = i3;
            int i6 = i4 >>> 3;
            int i7 = i4 & 7;
            if (i6 == 1) {
                if (i7 == metadata.keyType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(data, i5, limit, metadata.keyType, null, registers);
                    obj = registers.object1;
                } else {
                    decodeVarint32 = ArrayDecoders.skipField(i4, data, i5, limit, registers);
                }
            } else {
                if (i6 == 2 && i7 == metadata.valueType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(data, i5, limit, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                }
                decodeVarint32 = ArrayDecoders.skipField(i4, data, i5, limit, registers);
            }
        }
        if (decodeVarint32 != i2) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        target.put(obj, obj2);
        return i2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int parseRepeatedField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        int decodeVarint32List;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe.getObject(message, fieldOffset);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            unsafe.putObject(message, fieldOffset, protobufList);
        }
        Internal.ProtobufList protobufList2 = protobufList;
        switch (fieldType) {
            case 18:
            case 35:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedDoubleList(data, position, protobufList2, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 19:
            case 36:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFloatList(data, position, protobufList2, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 20:
            case 21:
            case 37:
            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint64List(data, position, protobufList2, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 22:
            case 29:
            case 39:
            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint32List(data, position, protobufList2, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 23:
            case 32:
            case 40:
            case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed64List(data, position, protobufList2, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 24:
            case 31:
            case 41:
            case 45:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed32List(data, position, protobufList2, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 25:
            case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedBoolList(data, position, protobufList2, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 26:
                if (wireType == 2) {
                    if ((typeAndOffset & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(tag, data, position, limit, protobufList2, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 27:
                if (wireType == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 28:
                if (wireType == 2) {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 30:
            case 44:
                if (wireType != 2) {
                    if (wireType == 0) {
                        decodeVarint32List = ArrayDecoders.decodeVarint32List(tag, data, position, limit, protobufList2, registers);
                    }
                    return position;
                }
                decodeVarint32List = ArrayDecoders.decodePackedVarint32List(data, position, protobufList2, registers);
                SchemaUtil.filterUnknownEnumList((Object) message, number, (List<Integer>) protobufList2, getEnumFieldVerifier(bufferPosition), (Object) null, (UnknownFieldSchema<UT, Object>) this.unknownFieldSchema);
                return decodeVarint32List;
            case 33:
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt32List(data, position, protobufList2, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt32List(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 34:
            case 48:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt64List(data, position, protobufList2, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt64List(tag, data, position, limit, protobufList2, registers);
                }
                return position;
            case 49:
                if (wireType == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, protobufList2, registers);
                }
                return position;
            default:
                return position;
        }
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object object = unsafe.getObject(message, fieldOffset);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            unsafe.putObject(message, fieldOffset, newMapField);
            object = newMapField;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        long j = this.buffer[bufferPosition + 2] & 1048575;
        switch (fieldType) {
            case ONEOF_TYPE_OFFSET /* 51 */:
                if (wireType != 1) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                int i = position + 8;
                unsafe.putInt(message, j, number);
                return i;
            case 52:
                if (wireType != 5) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                int i2 = position + 4;
                unsafe.putInt(message, j, number);
                return i2;
            case 53:
            case 54:
                if (wireType != 0) {
                    return position;
                }
                int decodeVarint64 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Long.valueOf(registers.long1));
                unsafe.putInt(message, j, number);
                return decodeVarint64;
            case 55:
            case 62:
                if (wireType != 0) {
                    return position;
                }
                int decodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
                unsafe.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
                unsafe.putInt(message, j, number);
                return decodeVarint32;
            case 56:
            case 65:
                if (wireType != 1) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                int i3 = position + 8;
                unsafe.putInt(message, j, number);
                return i3;
            case 57:
            case 64:
                if (wireType != 5) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                int i4 = position + 4;
                unsafe.putInt(message, j, number);
                return i4;
            case 58:
                if (wireType != 0) {
                    return position;
                }
                int decodeVarint642 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Boolean.valueOf(registers.long1 != 0));
                unsafe.putInt(message, j, number);
                return decodeVarint642;
            case 59:
                if (wireType != 2) {
                    return position;
                }
                int decodeVarint322 = ArrayDecoders.decodeVarint32(data, position, registers);
                int i5 = registers.int1;
                if (i5 == 0) {
                    unsafe.putObject(message, fieldOffset, "");
                } else {
                    if ((typeAndOffset & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(data, decodeVarint322, decodeVarint322 + i5)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    unsafe.putObject(message, fieldOffset, new String(data, decodeVarint322, i5, Internal.UTF_8));
                    decodeVarint322 += i5;
                }
                unsafe.putInt(message, j, number);
                return decodeVarint322;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                if (wireType != 2) {
                    return position;
                }
                Object mutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                int mergeMessageField = ArrayDecoders.mergeMessageField(mutableOneofMessageFieldForMerge, getMessageFieldSchema(bufferPosition), data, position, limit, registers);
                storeOneofMessageField(message, number, bufferPosition, mutableOneofMessageFieldForMerge);
                return mergeMessageField;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                if (wireType != 2) {
                    return position;
                }
                int decodeBytes = ArrayDecoders.decodeBytes(data, position, registers);
                unsafe.putObject(message, fieldOffset, registers.object1);
                unsafe.putInt(message, j, number);
                return decodeBytes;
            case 63:
                if (wireType != 0) {
                    return position;
                }
                int decodeVarint323 = ArrayDecoders.decodeVarint32(data, position, registers);
                int i6 = registers.int1;
                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(bufferPosition);
                if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i6)) {
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(i6));
                    unsafe.putInt(message, j, number);
                    return decodeVarint323;
                }
                getMutableUnknownFields(message).storeField(tag, Long.valueOf(i6));
                return decodeVarint323;
            case 66:
                if (wireType != 0) {
                    return position;
                }
                int decodeVarint324 = ArrayDecoders.decodeVarint32(data, position, registers);
                unsafe.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                unsafe.putInt(message, j, number);
                return decodeVarint324;
            case 67:
                if (wireType != 0) {
                    return position;
                }
                int decodeVarint643 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                unsafe.putInt(message, j, number);
                return decodeVarint643;
            case 68:
                if (wireType == 3) {
                    Object mutableOneofMessageFieldForMerge2 = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                    int mergeGroupField = ArrayDecoders.mergeGroupField(mutableOneofMessageFieldForMerge2, getMessageFieldSchema(bufferPosition), data, position, limit, (tag & (-8)) | 4, registers);
                    storeOneofMessageField(message, number, bufferPosition, mutableOneofMessageFieldForMerge2);
                    return mergeGroupField;
                }
                break;
        }
        return position;
    }

    private Schema getMessageFieldSchema(int pos) {
        int i = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[i];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i + 1]);
        this.objects[i] = schemaFor;
        return schemaFor;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:104:0x009d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0417 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0424  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    int parseMessage(T message, byte[] data, int position, int limit, int endDelimited, ArrayDecoders.Registers registers) throws IOException {
        T t;
        int i;
        int i2;
        Unsafe unsafe;
        MessageSchema<T> messageSchema;
        int i3;
        int positionForFieldNumber;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        ArrayDecoders.Registers registers2;
        T t2;
        int decodeUnknownField;
        int i9;
        long j;
        ArrayDecoders.Registers registers3;
        int i10;
        Unsafe unsafe2;
        int i11;
        byte[] bArr;
        int i12;
        Unsafe unsafe3;
        ArrayDecoders.Registers registers4;
        T t3;
        byte[] bArr2;
        int i13;
        int i14;
        byte[] bArr3;
        int i15;
        ArrayDecoders.Registers registers5;
        int i16;
        int i17;
        MessageSchema<T> messageSchema2 = this;
        T t4 = message;
        byte[] bArr4 = data;
        int i18 = limit;
        ArrayDecoders.Registers registers6 = registers;
        checkMutable(t4);
        Unsafe unsafe4 = UNSAFE;
        int i19 = -1;
        int i20 = position;
        int i21 = -1;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = 1048575;
        while (true) {
            if (i20 < i18) {
                int i26 = i20 + 1;
                int i27 = bArr4[i20];
                if (i27 < 0) {
                    i26 = ArrayDecoders.decodeVarint32(i27, bArr4, i26, registers6);
                    i27 = registers6.int1;
                }
                int i28 = i26;
                i24 = i27;
                int i29 = i24 >>> 3;
                int i30 = i24 & 7;
                if (i29 > i21) {
                    positionForFieldNumber = messageSchema2.positionForFieldNumber(i29, i22 / 3);
                } else {
                    positionForFieldNumber = messageSchema2.positionForFieldNumber(i29);
                }
                if (positionForFieldNumber == i19) {
                    i4 = i28;
                    i2 = i25;
                    unsafe = unsafe4;
                    i5 = i19;
                    i6 = i29;
                    i7 = 0;
                    messageSchema = messageSchema2;
                    i8 = i24;
                } else {
                    int i31 = messageSchema2.buffer[positionForFieldNumber + 1];
                    int i32 = i19;
                    int type = type(i31);
                    long offset = offset(i31);
                    if (type <= 17) {
                        int i33 = messageSchema2.buffer[positionForFieldNumber + 2];
                        int i34 = 1 << (i33 >>> 20);
                        int i35 = i33 & 1048575;
                        if (i35 != i25) {
                            i6 = i29;
                            j = offset;
                            if (i25 != 1048575) {
                                unsafe4.putInt(t4, i25, i23);
                            }
                            i23 = i35 == 1048575 ? 0 : unsafe4.getInt(t4, i35);
                        } else {
                            j = offset;
                            i6 = i29;
                            i35 = i25;
                        }
                        switch (type) {
                            case 0:
                                registers3 = registers;
                                i10 = positionForFieldNumber;
                                unsafe2 = unsafe4;
                                i11 = i28;
                                long j2 = j;
                                bArr = data;
                                if (i30 == 1) {
                                    UnsafeUtil.putDouble(t4, j2, ArrayDecoders.decodeDouble(bArr, i11));
                                    i12 = i11 + 8;
                                    i23 |= i34;
                                    Unsafe unsafe5 = unsafe2;
                                    i20 = i12;
                                    bArr4 = bArr;
                                    unsafe4 = unsafe5;
                                    i18 = limit;
                                    registers6 = registers3;
                                    i25 = i35;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    break;
                                } else {
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited && endDelimited != 0) {
                                        t = message;
                                        i = limit;
                                        i24 = i8;
                                        i3 = i4;
                                        break;
                                    } else {
                                        if (!messageSchema.hasExtensions && registers2.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                                            decodeUnknownField = ArrayDecoders.decodeExtensionOrUnknownField(i8, data, i4, limit, message, messageSchema.defaultInstance, messageSchema.unknownFieldSchema, registers2);
                                            t2 = message;
                                            i9 = limit;
                                        } else {
                                            t2 = message;
                                            decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                            i9 = limit;
                                        }
                                        i20 = decodeUnknownField;
                                        bArr4 = data;
                                        i24 = i8;
                                        t4 = t2;
                                        i18 = i9;
                                        i22 = i7;
                                        messageSchema2 = messageSchema;
                                        i25 = i2;
                                        i19 = i5;
                                        i21 = i6;
                                        unsafe4 = unsafe;
                                        registers6 = registers;
                                        break;
                                    }
                                }
                                break;
                            case 1:
                                registers3 = registers;
                                i10 = positionForFieldNumber;
                                unsafe2 = unsafe4;
                                i11 = i28;
                                long j3 = j;
                                bArr = data;
                                if (i30 == 5) {
                                    UnsafeUtil.putFloat(t4, j3, ArrayDecoders.decodeFloat(bArr, i11));
                                    i12 = i11 + 4;
                                    i23 |= i34;
                                    Unsafe unsafe52 = unsafe2;
                                    i20 = i12;
                                    bArr4 = bArr;
                                    unsafe4 = unsafe52;
                                    i18 = limit;
                                    registers6 = registers3;
                                    i25 = i35;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    break;
                                } else {
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                        break;
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 2:
                            case 3:
                                registers3 = registers;
                                i10 = positionForFieldNumber;
                                unsafe3 = unsafe4;
                                i11 = i28;
                                long j4 = j;
                                if (i30 == 0) {
                                    int decodeVarint64 = ArrayDecoders.decodeVarint64(data, i11, registers3);
                                    T t5 = t4;
                                    unsafe3.putLong(t5, j4, registers3.long1);
                                    t4 = t5;
                                    i23 |= i34;
                                    i18 = limit;
                                    bArr4 = data;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    unsafe4 = unsafe3;
                                    i20 = decodeVarint64;
                                    registers6 = registers3;
                                    i25 = i35;
                                    break;
                                } else {
                                    unsafe2 = unsafe3;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 4:
                            case 11:
                                registers3 = registers;
                                i10 = positionForFieldNumber;
                                unsafe3 = unsafe4;
                                i11 = i28;
                                long j5 = j;
                                if (i30 == 0) {
                                    int decodeVarint32 = ArrayDecoders.decodeVarint32(data, i11, registers3);
                                    unsafe3.putInt(t4, j5, registers3.int1);
                                    i23 |= i34;
                                    unsafe4 = unsafe3;
                                    bArr4 = data;
                                    i18 = limit;
                                    i20 = decodeVarint32;
                                    registers6 = registers3;
                                    i25 = i35;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    break;
                                } else {
                                    unsafe2 = unsafe3;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 5:
                            case 14:
                                T t6 = t4;
                                i10 = positionForFieldNumber;
                                i11 = i28;
                                long j6 = j;
                                if (i30 == 1) {
                                    Unsafe unsafe6 = unsafe4;
                                    registers3 = registers;
                                    unsafe6.putLong(t6, j6, ArrayDecoders.decodeFixed64(data, i11));
                                    t4 = t6;
                                    i20 = i11 + 8;
                                    i23 |= i34;
                                    unsafe4 = unsafe6;
                                    bArr4 = data;
                                    i18 = limit;
                                    registers6 = registers3;
                                    i25 = i35;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    break;
                                } else {
                                    unsafe3 = unsafe4;
                                    registers3 = registers;
                                    unsafe2 = unsafe3;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 6:
                            case 13:
                                registers4 = registers;
                                t3 = t4;
                                i10 = positionForFieldNumber;
                                i11 = i28;
                                long j7 = j;
                                bArr2 = data;
                                if (i30 == 5) {
                                    unsafe4.putInt(t3, j7, ArrayDecoders.decodeFixed32(bArr2, i11));
                                    i13 = i11 + 4;
                                    i23 |= i34;
                                    i18 = limit;
                                    registers6 = registers4;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    i20 = i13;
                                    bArr4 = bArr2;
                                    t4 = t3;
                                    i25 = i35;
                                    break;
                                } else {
                                    registers3 = registers4;
                                    unsafe2 = unsafe4;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 7:
                                registers4 = registers;
                                t3 = t4;
                                i10 = positionForFieldNumber;
                                i14 = i28;
                                long j8 = j;
                                bArr2 = data;
                                if (i30 == 0) {
                                    i13 = ArrayDecoders.decodeVarint64(bArr2, i14, registers4);
                                    UnsafeUtil.putBoolean(t3, j8, registers4.long1 != 0);
                                    i23 |= i34;
                                    i18 = limit;
                                    registers6 = registers4;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    i20 = i13;
                                    bArr4 = bArr2;
                                    t4 = t3;
                                    i25 = i35;
                                    break;
                                } else {
                                    registers3 = registers4;
                                    unsafe2 = unsafe4;
                                    i11 = i14;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 8:
                                registers4 = registers;
                                t3 = t4;
                                i10 = positionForFieldNumber;
                                i14 = i28;
                                long j9 = j;
                                bArr2 = data;
                                if (i30 == 2) {
                                    if (isEnforceUtf8(i31)) {
                                        i13 = ArrayDecoders.decodeStringRequireUtf8(bArr2, i14, registers4);
                                    } else {
                                        i13 = ArrayDecoders.decodeString(bArr2, i14, registers4);
                                    }
                                    unsafe4.putObject(t3, j9, registers4.object1);
                                    i23 |= i34;
                                    i18 = limit;
                                    registers6 = registers4;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    i20 = i13;
                                    bArr4 = bArr2;
                                    t4 = t3;
                                    i25 = i35;
                                    break;
                                } else {
                                    registers3 = registers4;
                                    unsafe2 = unsafe4;
                                    i11 = i14;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 9:
                                i10 = positionForFieldNumber;
                                Unsafe unsafe7 = unsafe4;
                                if (i30 == 2) {
                                    T t7 = t4;
                                    Object mutableMessageFieldForMerge = messageSchema2.mutableMessageFieldForMerge(t7, i10);
                                    t3 = t7;
                                    unsafe4 = unsafe7;
                                    i13 = ArrayDecoders.mergeMessageField(mutableMessageFieldForMerge, messageSchema2.getMessageFieldSchema(i10), data, i28, limit, registers);
                                    bArr2 = data;
                                    registers4 = registers;
                                    messageSchema2.storeMessageField(t3, i10, mutableMessageFieldForMerge);
                                    i23 |= i34;
                                    i18 = limit;
                                    registers6 = registers4;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    i20 = i13;
                                    bArr4 = bArr2;
                                    t4 = t3;
                                    i25 = i35;
                                    break;
                                } else {
                                    t3 = t4;
                                    bArr2 = data;
                                    unsafe4 = unsafe7;
                                    i14 = i28;
                                    registers4 = registers;
                                    registers3 = registers4;
                                    unsafe2 = unsafe4;
                                    i11 = i14;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 10:
                                bArr3 = data;
                                i10 = positionForFieldNumber;
                                unsafe3 = unsafe4;
                                i15 = i28;
                                long j10 = j;
                                registers5 = registers;
                                if (i30 == 2) {
                                    i20 = ArrayDecoders.decodeBytes(bArr3, i15, registers5);
                                    unsafe3.putObject(t4, j10, registers5.object1);
                                    i23 |= i34;
                                    i18 = limit;
                                    registers6 = registers5;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    unsafe4 = unsafe3;
                                    bArr4 = bArr3;
                                    i25 = i35;
                                    break;
                                } else {
                                    registers3 = registers5;
                                    i11 = i15;
                                    unsafe2 = unsafe3;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 12:
                                bArr3 = data;
                                i10 = positionForFieldNumber;
                                unsafe3 = unsafe4;
                                i15 = i28;
                                long j11 = j;
                                registers5 = registers;
                                if (i30 == 0) {
                                    i20 = ArrayDecoders.decodeVarint32(bArr3, i15, registers5);
                                    int i36 = registers5.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i10);
                                    if (!isLegacyEnumIsClosed(i31) || enumFieldVerifier == null || enumFieldVerifier.isInRange(i36)) {
                                        unsafe3.putInt(t4, j11, i36);
                                        i23 |= i34;
                                        i18 = limit;
                                        registers6 = registers5;
                                        i22 = i10;
                                        i19 = i32;
                                        i21 = i6;
                                        unsafe4 = unsafe3;
                                        bArr4 = bArr3;
                                        i25 = i35;
                                        break;
                                    } else {
                                        getMutableUnknownFields(t4).storeField(i24, Long.valueOf(i36));
                                        i18 = limit;
                                        registers6 = registers5;
                                        i22 = i10;
                                        i19 = i32;
                                        i21 = i6;
                                        unsafe4 = unsafe3;
                                        bArr4 = bArr3;
                                        i25 = i35;
                                    }
                                } else {
                                    registers3 = registers5;
                                    i11 = i15;
                                    unsafe2 = unsafe3;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 15:
                                bArr3 = data;
                                i10 = positionForFieldNumber;
                                unsafe3 = unsafe4;
                                i15 = i28;
                                long j12 = j;
                                registers5 = registers;
                                if (i30 == 0) {
                                    i20 = ArrayDecoders.decodeVarint32(bArr3, i15, registers5);
                                    unsafe3.putInt(t4, j12, CodedInputStream.decodeZigZag32(registers5.int1));
                                    i23 |= i34;
                                    i18 = limit;
                                    registers6 = registers5;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    unsafe4 = unsafe3;
                                    bArr4 = bArr3;
                                    i25 = i35;
                                    break;
                                } else {
                                    registers3 = registers5;
                                    i11 = i15;
                                    unsafe2 = unsafe3;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 16:
                                bArr3 = data;
                                i10 = positionForFieldNumber;
                                i15 = i28;
                                if (i30 == 0) {
                                    int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr3, i15, registers);
                                    T t8 = t4;
                                    Unsafe unsafe8 = unsafe4;
                                    registers5 = registers;
                                    unsafe8.putLong(t8, j, CodedInputStream.decodeZigZag64(registers.long1));
                                    unsafe3 = unsafe8;
                                    t4 = t8;
                                    i23 |= i34;
                                    i18 = limit;
                                    i20 = decodeVarint642;
                                    registers6 = registers5;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    unsafe4 = unsafe3;
                                    bArr4 = bArr3;
                                    i25 = i35;
                                    break;
                                } else {
                                    Unsafe unsafe9 = unsafe4;
                                    registers5 = registers;
                                    unsafe3 = unsafe9;
                                    registers3 = registers5;
                                    i11 = i15;
                                    unsafe2 = unsafe3;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            case 17:
                                if (i30 == 3) {
                                    Object mutableMessageFieldForMerge2 = messageSchema2.mutableMessageFieldForMerge(t4, positionForFieldNumber);
                                    i10 = positionForFieldNumber;
                                    i20 = ArrayDecoders.mergeGroupField(mutableMessageFieldForMerge2, messageSchema2.getMessageFieldSchema(positionForFieldNumber), data, i28, limit, (i6 << 3) | 4, registers);
                                    messageSchema2.storeMessageField(t4, i10, mutableMessageFieldForMerge2);
                                    i23 |= i34;
                                    i18 = limit;
                                    registers6 = registers;
                                    bArr4 = data;
                                    i25 = i35;
                                    i22 = i10;
                                    i19 = i32;
                                    i21 = i6;
                                    break;
                                } else {
                                    i10 = positionForFieldNumber;
                                    registers3 = registers;
                                    unsafe2 = unsafe4;
                                    i11 = i28;
                                    messageSchema = messageSchema2;
                                    unsafe = unsafe2;
                                    i2 = i35;
                                    i8 = i24;
                                    i4 = i11;
                                    i5 = i32;
                                    registers2 = registers3;
                                    i7 = i10;
                                    if (i8 != endDelimited) {
                                    }
                                    if (!messageSchema.hasExtensions) {
                                    }
                                    t2 = message;
                                    decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                    i9 = limit;
                                    i20 = decodeUnknownField;
                                    bArr4 = data;
                                    i24 = i8;
                                    t4 = t2;
                                    i18 = i9;
                                    i22 = i7;
                                    messageSchema2 = messageSchema;
                                    i25 = i2;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                    break;
                                }
                                break;
                            default:
                                registers3 = registers;
                                i10 = positionForFieldNumber;
                                unsafe2 = unsafe4;
                                i11 = i28;
                                messageSchema = messageSchema2;
                                unsafe = unsafe2;
                                i2 = i35;
                                i8 = i24;
                                i4 = i11;
                                i5 = i32;
                                registers2 = registers3;
                                i7 = i10;
                                if (i8 != endDelimited) {
                                }
                                if (!messageSchema.hasExtensions) {
                                }
                                t2 = message;
                                decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                i9 = limit;
                                i20 = decodeUnknownField;
                                bArr4 = data;
                                i24 = i8;
                                t4 = t2;
                                i18 = i9;
                                i22 = i7;
                                messageSchema2 = messageSchema;
                                i25 = i2;
                                i19 = i5;
                                i21 = i6;
                                unsafe4 = unsafe;
                                registers6 = registers;
                                break;
                        }
                    } else {
                        int i37 = i25;
                        i6 = i29;
                        i7 = positionForFieldNumber;
                        Unsafe unsafe10 = unsafe4;
                        if (type != 27) {
                            i16 = i28;
                            if (type <= 49) {
                                unsafe = unsafe10;
                                i2 = i37;
                                i5 = i32;
                                i17 = i23;
                                int parseRepeatedField = messageSchema2.parseRepeatedField(message, data, i16, limit, i24, i6, i30, i7, i31, type, offset, registers);
                                i24 = i24;
                                if (parseRepeatedField != i16) {
                                    messageSchema2 = this;
                                    t4 = message;
                                    bArr4 = data;
                                    i18 = limit;
                                    i20 = parseRepeatedField;
                                    i22 = i7;
                                    i25 = i2;
                                    i23 = i17;
                                    i19 = i5;
                                    i21 = i6;
                                    unsafe4 = unsafe;
                                    registers6 = registers;
                                } else {
                                    messageSchema = this;
                                    i4 = parseRepeatedField;
                                }
                            } else {
                                i24 = i24;
                                unsafe = unsafe10;
                                i2 = i37;
                                i5 = i32;
                                i17 = i23;
                                if (type != 50) {
                                    registers2 = registers;
                                    int parseOneofField = parseOneofField(message, data, i16, limit, i24, i6, i30, i31, type, offset, i7, registers2);
                                    messageSchema = this;
                                    i8 = i24;
                                    i7 = i7;
                                    if (parseOneofField != i16) {
                                        t4 = message;
                                        bArr4 = data;
                                        i18 = limit;
                                        i20 = parseOneofField;
                                        i22 = i7;
                                        registers6 = registers2;
                                        i25 = i2;
                                        i23 = i17;
                                        i19 = i5;
                                        i21 = i6;
                                        i24 = i8;
                                        messageSchema2 = messageSchema;
                                    } else {
                                        i4 = parseOneofField;
                                        i23 = i17;
                                        if (i8 != endDelimited) {
                                        }
                                        if (!messageSchema.hasExtensions) {
                                        }
                                        t2 = message;
                                        decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                                        i9 = limit;
                                        i20 = decodeUnknownField;
                                        bArr4 = data;
                                        i24 = i8;
                                        t4 = t2;
                                        i18 = i9;
                                        i22 = i7;
                                        messageSchema2 = messageSchema;
                                        i25 = i2;
                                        i19 = i5;
                                        i21 = i6;
                                        unsafe4 = unsafe;
                                        registers6 = registers;
                                    }
                                } else if (i30 == 2) {
                                    int parseMapField = parseMapField(message, data, i16, limit, i7, offset, registers);
                                    i7 = i7;
                                    if (parseMapField != i16) {
                                        messageSchema2 = this;
                                        t4 = message;
                                        bArr4 = data;
                                        i18 = limit;
                                        registers6 = registers;
                                        i20 = parseMapField;
                                        i22 = i7;
                                        i25 = i2;
                                        i23 = i17;
                                        i19 = i5;
                                        i21 = i6;
                                    } else {
                                        messageSchema = this;
                                        i4 = parseMapField;
                                    }
                                } else {
                                    messageSchema = this;
                                    i4 = i16;
                                }
                                unsafe4 = unsafe;
                            }
                        } else if (i30 == 2) {
                            Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe10.getObject(t4, offset);
                            if (!protobufList.isModifiable()) {
                                int size = protobufList.size();
                                protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                unsafe10.putObject(t4, offset, protobufList);
                            }
                            unsafe4 = unsafe10;
                            registers6 = registers;
                            i18 = limit;
                            i20 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(i7), i24, data, i28, limit, protobufList, registers6);
                            i22 = i7;
                            i25 = i37;
                            i19 = i32;
                            i21 = i6;
                            t4 = message;
                            bArr4 = data;
                        } else {
                            unsafe = unsafe10;
                            i2 = i37;
                            i16 = i28;
                            i5 = i32;
                            i17 = i23;
                            messageSchema = this;
                            i4 = i16;
                        }
                        i8 = i24;
                        i23 = i17;
                    }
                }
                registers2 = registers;
                if (i8 != endDelimited) {
                }
                if (!messageSchema.hasExtensions) {
                }
                t2 = message;
                decodeUnknownField = ArrayDecoders.decodeUnknownField(i8, data, i4, limit, getMutableUnknownFields(t2), registers);
                i9 = limit;
                i20 = decodeUnknownField;
                bArr4 = data;
                i24 = i8;
                t4 = t2;
                i18 = i9;
                i22 = i7;
                messageSchema2 = messageSchema;
                i25 = i2;
                i19 = i5;
                i21 = i6;
                unsafe4 = unsafe;
                registers6 = registers;
            } else {
                t = t4;
                i = i18;
                i2 = i25;
                unsafe = unsafe4;
                messageSchema = messageSchema2;
                i3 = i20;
            }
        }
        int i38 = i2;
        if (i38 != 1048575) {
            unsafe.putInt(t, i38, i23);
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i39 = messageSchema.checkInitializedCount; i39 < messageSchema.repeatedFieldOffsetStart; i39++) {
            unknownFieldSetLite = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(t, messageSchema.intArray[i39], unknownFieldSetLite, messageSchema.unknownFieldSchema, message);
        }
        T t9 = t;
        MessageSchema<T> messageSchema3 = messageSchema;
        if (unknownFieldSetLite != null) {
            messageSchema3.unknownFieldSchema.setBuilderToMessage(t9, unknownFieldSetLite);
        }
        if (endDelimited == 0) {
            if (i3 != i) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (i3 > i || i24 != endDelimited) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T message, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        long offset = offset(typeAndOffsetAt(pos));
        if (!isFieldPresent(message, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(message, offset);
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeMessageField(T message, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setFieldPresent(message, pos);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T message, int fieldNumber, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isOneofPresent(message, fieldNumber, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(message, offset(typeAndOffsetAt(pos)));
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeOneofMessageField(T message, int fieldNumber, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setOneofPresent(message, fieldNumber, pos);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        parseMessage(message, data, position, limit, 0, registers);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.datastore.preferences.protobuf.Schema
    public void makeImmutable(T message) {
        if (isMutable(message)) {
            if (message instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) message;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int length = this.buffer.length;
            for (int i = 0; i < length; i += 3) {
                int typeAndOffsetAt = typeAndOffsetAt(i);
                long offset = offset(typeAndOffsetAt);
                int type = type(typeAndOffsetAt);
                if (type != 9) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(message, numberAt(i), i)) {
                            getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(message, offset));
                        }
                    } else {
                        switch (type) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                            case 39:
                            case 40:
                            case 41:
                            case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                            case 44:
                            case 45:
                            case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                            case 48:
                            case 49:
                                this.listFieldSchema.makeImmutableListAt(message, offset);
                                break;
                            case 50:
                                Unsafe unsafe = UNSAFE;
                                Object object = unsafe.getObject(message, offset);
                                if (object != null) {
                                    unsafe.putObject(message, offset, this.mapFieldSchema.toImmutable(object));
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
                if (isFieldPresent(message, i)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(message, offset));
                }
            }
            this.unknownFieldSchema.makeImmutable(message);
            if (this.hasExtensions) {
                this.extensionSchema.makeImmutable(message);
            }
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(pos));
        Object object = UnsafeUtil.getObject(message, offset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, offset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            UnsafeUtil.putObject(message, offset, newMapField);
            object = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj2) {
        Internal.EnumVerifier enumFieldVerifier;
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        return (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) ? ub : (UB) filterUnknownEnumMap(i, numberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema, obj2);
    }

    private <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.getBuilderFromMessage(obj);
                }
                ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.getCodedOutput(), forMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, newCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public final boolean isInitialized(T message) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.checkInitializedCount) {
            int i6 = this.intArray[i4];
            int numberAt = numberAt(i6);
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            int i7 = this.buffer[i6 + 2];
            int i8 = i7 & 1048575;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i3) {
                if (i8 != 1048575) {
                    i5 = UNSAFE.getInt(message, i8);
                }
                i2 = i5;
                i = i8;
            } else {
                i = i3;
                i2 = i5;
            }
            T t = message;
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(t, i6, i, i2, i9)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type == 9 || type == 17) {
                if (isFieldPresent(t, i6, i, i2, i9) && !isInitialized(t, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                    return false;
                }
            } else {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(t, numberAt, i6) && !isInitialized(t, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type == 50 && !isMapInitialized(t, typeAndOffsetAt, i6)) {
                            return false;
                        }
                    }
                }
                if (!isListInitialized(t, typeAndOffsetAt, i6)) {
                    return false;
                }
            }
            i4++;
            message = t;
            i3 = i;
            i5 = i2;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(message).isInitialized();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(message, offset(typeAndOffset)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            if (!messageFieldSchema.isInitialized(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [androidx.datastore.preferences.protobuf.Schema] */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? r5 = 0;
        for (Object obj : forMapData.values()) {
            r5 = r5;
            if (r5 == 0) {
                r5 = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            if (!r5.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static boolean isMutable(Object message) {
        if (message == null) {
            return false;
        }
        if (message instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) message).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object message) {
        if (isMutable(message)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + message);
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble(message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat(message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt(message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong(message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean(message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject(message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject(message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject(message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject(message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject(message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceFieldOffset, int presenceField, int presenceMask) {
        if (presenceFieldOffset == 1048575) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        boolean equals;
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j != 1048575) {
            return (UnsafeUtil.getInt(message, j) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0;
        }
        int typeAndOffsetAt = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.getDouble(message, offset)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.getFloat(message, offset)) != 0;
            case 2:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 3:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 4:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 5:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 6:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(message, offset);
            case 8:
                Object object = UnsafeUtil.getObject(message, offset);
                if (object instanceof String) {
                    equals = ((String) object).isEmpty();
                    break;
                } else if (object instanceof ByteString) {
                    equals = ByteString.EMPTY.equals(object);
                    break;
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return UnsafeUtil.getObject(message, offset) != null;
            case 10:
                equals = ByteString.EMPTY.equals(UnsafeUtil.getObject(message, offset));
                break;
            case 11:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 12:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 13:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 14:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 15:
                return UnsafeUtil.getInt(message, offset) != 0;
            case 16:
                return UnsafeUtil.getLong(message, offset) != 0;
            case 17:
                return UnsafeUtil.getObject(message, offset) != null;
            default:
                throw new IllegalArgumentException();
        }
        return !equals;
    }

    private void setFieldPresent(T message, int pos) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.putInt(message, j, (1 << (presenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt(message, j));
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        return UnsafeUtil.getInt(message, (long) (presenceMaskAndOffsetAt(pos) & 1048575)) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        long presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos) & 1048575;
        return UnsafeUtil.getInt(message, presenceMaskAndOffsetAt) == UnsafeUtil.getInt(other, presenceMaskAndOffsetAt);
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        UnsafeUtil.putInt(message, presenceMaskAndOffsetAt(pos) & 1048575, fieldNumber);
    }

    private int positionForFieldNumber(final int number) {
        if (number < this.minFieldNumber || number > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(number, 0);
    }

    private int positionForFieldNumber(final int number, final int min) {
        if (number < this.minFieldNumber || number > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(number, min);
    }

    private int slowPositionForFieldNumber(final int number, int min) {
        int length = (this.buffer.length / 3) - 1;
        while (min <= length) {
            int i = (length + min) >>> 1;
            int i2 = i * 3;
            int numberAt = numberAt(i2);
            if (number == numberAt) {
                return i2;
            }
            if (number < numberAt) {
                length = i - 1;
            } else {
                min = i + 1;
            }
        }
        return -1;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
