package io.nuls.data.constant;

import io.nuls.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【主键】
 *
 * @author Long
 * @date 2020/03/16
 */
public enum IsKey {

    /**
     * 否
     */
    NO(0,"否"),
    /**
     * 是
     */
    YES(1,"是");


    private final Integer value;
    private final String desc;

    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "0,1";

    private static final Map<Integer, IsKey> LOOKUP = new HashMap<>();

    static {
        for (IsKey e : IsKey.values()) {
            LOOKUP.put(e.value, e);
        }
    }


    IsKey(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static IsKey find(Integer value) {
        return LOOKUP.get(value);
    }

    public static IsKey findByDesc(String desc){
        for (IsKey e : IsKey.values()) {
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
        IsKey theEnum = findByDesc(desc);
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
        IsKey theEnum = find(value);
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
        IsKey theEnum = find(value);
        return theEnum!=null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

