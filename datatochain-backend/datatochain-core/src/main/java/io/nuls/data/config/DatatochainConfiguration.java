package io.nuls.data.config;

import io.nuls.common.util.SpringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * 配置类
 *
 * @author Long
 * @date 2020/03/16
 */
@Configuration
@PropertySources(value = @PropertySource("classpath:/config/datatochain-default.properties"))
public class DatatochainConfiguration {


    /**
     * 在这配置bean以后会把applicationContext注入到该类
     * @return
     */
    @Bean
    public SpringUtil springUtil(){
        return new SpringUtil();
    }

    /**
     * 使用自己配置的参数校验器，支持国际化
     * 如果不自定义的话，会由ValidationAutoConfiguration自动注册，不支持国际化
     * @param messageSource 由MessageSourceAutoConfiguration自动注册
     * @return
     */
    @Bean
    @ConditionalOnClass(name="javax.el.ELContext")
    public Validator validator(MessageSource messageSource) {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource);
        return factory;
    }


}

