package io.nuls.data.constant;

import io.nuls.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【解除关联】
 *
 * @author Long
 * @date 2020/03/16
 */
public enum RelationStatus {

    /**
     * 未解除
     */
    REALATION(0,"未解除"),
    /**
     * 解除
     */
    UNREALATION(1,"解除");


    private final Integer value;
    private final String desc;

    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "0,1";

    private static final Map<Integer, RelationStatus> LOOKUP = new HashMap<>();

    static {
        for (RelationStatus e : RelationStatus.values()) {
            LOOKUP.put(e.value, e);
        }
    }


    RelationStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static RelationStatus find(Integer value) {
        return LOOKUP.get(value);
    }

    public static RelationStatus findByDesc(String desc){
        for (RelationStatus e : RelationStatus.values()) {
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
        RelationStatus theEnum = findByDesc(desc);
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
        RelationStatus theEnum = find(value);
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
        RelationStatus theEnum = find(value);
        return theEnum!=null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

