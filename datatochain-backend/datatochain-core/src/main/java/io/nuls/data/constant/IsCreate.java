package io.nuls.data.constant;

import io.nuls.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【是否创建】
 *
 * @author Long
 * @date 2020/03/16
 */
public enum IsCreate {

    /**
     * 未创建
     */
    NO(0,"未创建"),
    /**
     * 已创建
     */
    YES(1,"已创建");


    private final Integer value;
    private final String desc;

    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "0,1";

    private static final Map<Integer, IsCreate> LOOKUP = new HashMap<>();

    static {
        for (IsCreate e : IsCreate.values()) {
            LOOKUP.put(e.value, e);
        }
    }


    IsCreate(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static IsCreate find(Integer value) {
        return LOOKUP.get(value);
    }

    public static IsCreate findByDesc(String desc){
        for (IsCreate e : IsCreate.values()) {
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
        IsCreate theEnum = findByDesc(desc);
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
        IsCreate theEnum = find(value);
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
        IsCreate theEnum = find(value);
        return theEnum!=null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

