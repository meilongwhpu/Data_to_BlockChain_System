package io.nuls.data.constant;

import java.util.HashMap;
import java.util.Map;

public enum MySqlTypeConstant {

    INT(1,"int"),
    VARCHAR(1,"varchar"),
    TEXT(0,"text"),
    BLOB(0,"blob"),
    DATETIME(0,"datetime"),
    TIMESTAMP(0,"timestamp"),
    DECIMAL(2,"decimal"),
    DOUBLE(2,"double"),
    FLOAT(2,"float"),
    CHAR(1,"char"),
    BIGINT(1,"bigint"),
    TINYINT(1,"tinyint");

    private final Integer lengthCount;
    private final String typeDesc;

    private static final Map<Integer, MySqlTypeConstant> LOOKUP = new HashMap<>();

    static {
        for (MySqlTypeConstant e : MySqlTypeConstant.values()) {
            LOOKUP.put(e.lengthCount, e);
        }
    }


    MySqlTypeConstant(Integer lengthCount, String typeDesc) {
        this.lengthCount = lengthCount;
        this.typeDesc = typeDesc;
    }

    /**
     * typeDesc映射lengthCount
     *
     * @param typeDesc
     * @return
     */
    public static Integer typeDescToLength(String typeDesc) {
        MySqlTypeConstant theEnum = findByDesc(typeDesc);
        if (theEnum != null) {
            return theEnum.getLengthCount();
        }
        return null;
    }

    /**
     * lengthCount映射typeDesc
     *
     * @param lengthCount
     * @return
     */
    public static String lengthToTypeDesc(Integer lengthCount) {
        MySqlTypeConstant theEnum = find(lengthCount);
        if (theEnum != null) {
            return theEnum.getTypeDesc();
        }
        return null;
    }

    public static MySqlTypeConstant find(Integer value) {
        return LOOKUP.get(value);
    }

    public static MySqlTypeConstant findByDesc(String typeDesc){
        for (MySqlTypeConstant e : MySqlTypeConstant.values()) {
            if(e.getTypeDesc().equalsIgnoreCase(typeDesc)){
                return e;
            }
        }
        return null;
    }

    public Integer getLengthCount() {
        return lengthCount;
    }

    public String getTypeDesc() {
        return typeDesc;
    }
}
