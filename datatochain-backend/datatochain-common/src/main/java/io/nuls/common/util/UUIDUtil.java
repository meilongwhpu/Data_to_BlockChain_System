package io.nuls.common.util;

import java.util.UUID;

/**
 * 获取UUID
 *
 * @author Long
 * @date 2020/03/16
 */
public class UUIDUtil {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static String getUUID16() {
        return getUUID().substring(8,24);
    }

}

