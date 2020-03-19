package io.nuls.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 测试配置类
 *
 * @author Long
 * @date 2020/03/16
 */
@Configuration
public class TestConfiguration {

    @Bean
    public H2Flusher h2Flusher(JdbcTemplate jdbcTemplate){
        return new H2Flusher(jdbcTemplate,"DB/datatochain.sql");
    }

}

