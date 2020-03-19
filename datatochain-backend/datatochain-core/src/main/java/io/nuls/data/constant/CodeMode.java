package io.nuls.data.constant;

import io.nuls.common.validator.Check;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举【编码格式】
 *
 * @author Long
 * @date 2020/03/16
 */
public enum CodeMode {

    /**
     * ASCII
     */
    ASCII(1,"ASCII"),
    /**
     * GB18030
     */
    GB18030(2,"GB18030"),
    /**
     * GB2312
     */
    GB2312(3,"GB2312"),
    /**
     * GBK
     */
    GBK(4,"GBK"),
    /**
     * ISO-8859-1
     */
    ISO(5,"ISO-8859-1"),
    /**
     * UTF-16
     */
    UTF16(6,"UTF-16"),
    /**
     * UTF-8
     */
    UTF8(7,"UTF-8");


    private final Integer value;
    private final String desc;

    /**
     * 枚举值罗列，给swagger接口文档展示用
     */
    public static final String VALUES_STR = "1,2,3,4,5,6,7";

    private static final Map<Integer, CodeMode> LOOKUP = new HashMap<>();

    static {
        for (CodeMode e : CodeMode.values()) {
            LOOKUP.put(e.value, e);
        }
    }


    CodeMode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static CodeMode find(Integer value) {
        return LOOKUP.get(value);
    }

    public static CodeMode findByDesc(String desc){
        for (CodeMode e : CodeMode.values()) {
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
        CodeMode theEnum = findByDesc(desc);
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
        CodeMode theEnum = find(value);
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
        CodeMode theEnum = find(value);
        return theEnum!=null;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


}

