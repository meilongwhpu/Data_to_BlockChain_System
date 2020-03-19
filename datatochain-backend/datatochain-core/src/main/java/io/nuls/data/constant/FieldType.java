package io.nuls.data.constant;

import io.nuls.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【字段类型】
 *
 * @author Long
 * @date 2020/03/16
 */
public enum FieldType {

    /**
     * BLOB
     */
    BLOB(1,"BLOB"),
    /**
     * CHAR
     */
    CHAR(2,"CHAR"),
    /**
     * DATETIME
     */
    DATETIME(3,"DATETIME"),
    /**
     * DECIMAL
     */
    DECIMAL(4,"DECIMAL"),
    /**
     * FLOAT
     */
    FLOAT(5,"FLOAT"),
    /**
     * TEXT
     */
    TEXT(6,"TEXT"),
    /**
     * TIMESTAMP
     */
    TIMESTAMP(7,"TIMESTAMP"),
    /**
     * TINYINT
     */
    TINYINT(8,"TINYINT"),
    /**
     * VARCHAR
     */
    VARCHAR(9,"VARCHAR");


    private final Integer value;
    private final String desc;

    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "1,2,3,4,5,6,7,8,9";

    private static final Map<Integer, FieldType> LOOKUP = new HashMap<>();

    static {
        for (FieldType e : FieldType.values()) {
            LOOKUP.put(e.value, e);
        }
    }


    FieldType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static FieldType find(Integer value) {
        return LOOKUP.get(value);
    }

    public static FieldType findByDesc(String desc){
        for (FieldType e : FieldType.values()) {
            if(e.getDesc().equals(desc)){
                return e;
            }
        }
        return null;
    }


    /**
     * desc映射value
     *
     * @param desc
     * @return
     */
    public static Integer descToValue(String desc) {
        FieldType theEnum = findByDesc(desc);
        if (theEnum != null) {
            return theEnum.getValue();
        }
        return null;
    }

    /**
     * value映射desc
     *
     * @param value
     * @return
     */
    public static String valueToDesc(Integer value) {
        FieldType theEnum = find(value);
        if (theEnum != null) {
            return theEnum.getDesc();
        }
        return null;
    }

    /**
     * 校验有效性
     */
    @Check
    public static final boolean validate(Integer value){
        FieldType theEnum = find(value);
        return theEnum!=null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

