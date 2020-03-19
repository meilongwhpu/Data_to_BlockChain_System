package io.nuls.data;

import io.nuls.data.util.H2Util;
import org.h2.engine.Mode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * H2数据库刷新器
 *
 * @author Long
 * @date 2020/03/16
 */
public class H2Flusher implements InitializingBean {

    /**
     * 数据库脚本文件保存路径
     */
    private final Map<String,String> scriptFilePath;

    private final JdbcTemplate jdbcTemplate;

    public H2Flusher(JdbcTemplate jdbcTemplate,String... scriptFiles) {
        this.jdbcTemplate = jdbcTemplate;
        this.scriptFilePath = new HashMap<>();
        for (String scriptFile : scriptFiles) {
            scriptFilePath.put(scriptFile, H2Util.getH2Script(scriptFile));
        }
    }

    /**
     * 刷新H2数据库
     */
    public void flushDB(){
        jdbcTemplate.execute("drop all objects;");
        for (String key : scriptFilePath.keySet()) {
            jdbcTemplate.execute("runscript from '"+ scriptFilePath.get(key)+"'");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 修改H2数据库配置
        Mode mode = Mode.getInstance("MYSQL");
    }

}

