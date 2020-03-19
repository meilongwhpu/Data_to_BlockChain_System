package io.nuls.data.constant;

import io.nuls.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【加密模式】
 *
 * @author Long
 * @date 2020/03/16
 */
public enum EncryptionMode {

    /**
     * NO
     */
    NO(0,"NO"),
    /**
     * AES
     */
    AES(1,"AES"),
    /**
     * BASE64
     */
    BASE64(2,"BASE64"),
    /**
     * DES
     */
    DES(3,"DES"),
    /**
     * HMAC
     */
    HMAC(4,"HMAC"),
    /**
     * MD5
     */
    MD5(5,"MD5"),
    /**
     * PBE
     */
    PBE(6,"PBE"),
    /**
     * RSA
     */
    RSA(7,"RSA"),
    /**
     * SHA
     */
    SHA(8,"SHA");


    private final Integer value;
    private final String desc;

    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "0,1,2,3,4,5,6,7,8";

    private static final Map<Integer, EncryptionMode> LOOKUP = new HashMap<>();

    static {
        for (EncryptionMode e : EncryptionMode.values()) {
            LOOKUP.put(e.value, e);
        }
    }


    EncryptionMode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static EncryptionMode find(Integer value) {
        return LOOKUP.get(value);
    }

    public static EncryptionMode findByDesc(String desc){
        for (EncryptionMode e : EncryptionMode.values()) {
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
        EncryptionMode theEnum = findByDesc(desc);
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
        EncryptionMode theEnum = find(value);
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
        EncryptionMode theEnum = find(value);
        return theEnum!=null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

