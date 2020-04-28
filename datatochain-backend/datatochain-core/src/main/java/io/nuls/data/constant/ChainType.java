package io.nuls.data.constant;

import io.nuls.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【上链位置】
 *
 * @author Long
 * @date 2020/03/16
 */
public enum ChainType {

    /**
     * NO-TO-CHAINBLOCK
     */
    NOTOCHAINBLOCK(0,"NO-TO-CHAINBLOCK"),
    /**
     * NULS-MAIN-NET
     */
    NULSMAINNET(1,"NULS-MAIN-NET"),
    /**
     * NULS-TEST-NET
     */
    NULSTESTNET(2,"NULS-TEST-NET");


    private final Integer value;
    private final String desc;

    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "0,1,2";

    private static final Map<Integer, ChainType> LOOKUP = new HashMap<>();

    static {
        for (ChainType e : ChainType.values()) {
            LOOKUP.put(e.value, e);
        }
    }


    ChainType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ChainType find(Integer value) {
        return LOOKUP.get(value);
    }

    public static ChainType findByDesc(String desc){
        for (ChainType e : ChainType.values()) {
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
        ChainType theEnum = findByDesc(desc);
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
        ChainType theEnum = find(value);
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
        ChainType theEnum = find(value);
        return theEnum!=null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

