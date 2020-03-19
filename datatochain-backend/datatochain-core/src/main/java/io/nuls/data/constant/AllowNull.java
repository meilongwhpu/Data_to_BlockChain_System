package io.nuls.data.constant;

import io.nuls.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【是否允许为空】
 *
 * @author Long
 * @date 2020/03/16
 */
public enum AllowNull {

    /**
     * 允许
     */
    ALLOW(0,"允许"),
    /**
     * 不允许
     */
    UNALLOW(1,"不允许");


    private final Integer value;
    private final String desc;

    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "0,1";

    private static final Map<Integer, AllowNull> LOOKUP = new HashMap<>();

    static {
        for (AllowNull e : AllowNull.values()) {
            LOOKUP.put(e.value, e);
        }
    }


    AllowNull(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AllowNull find(Integer value) {
        return LOOKUP.get(value);
    }

    public static AllowNull findByDesc(String desc){
        for (AllowNull e : AllowNull.values()) {
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
        AllowNull theEnum = findByDesc(desc);
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
        AllowNull theEnum = find(value);
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
        AllowNull theEnum = find(value);
        return theEnum!=null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

