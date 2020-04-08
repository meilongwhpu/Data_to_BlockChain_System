package io.nuls.data;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

@Configuration
public class AdminServerSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AdminServerProperties adminServer;


    public AdminServerSecurityConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        final String adminServerContextPath = this.adminServer.getContextPath();
        successHandler.setDefaultTargetUrl(adminServerContextPath + "/");

        http.authorizeRequests()
                .antMatchers(adminServerContextPath + "/assets/**").permitAll() // <1>
                .antMatchers(adminServerContextPath + "/login").permitAll()
                .anyRequest().authenticated() // <2>
                .and()
                .formLogin().loginPage(adminServerContextPath + "/login").successHandler(successHandler).and() // <3>
                .logout().logoutUrl(adminServerContextPath + "/logout").and()
                .httpBasic().and() // <4>
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // <5>
                .ignoringRequestMatchers(
                        new AntPathRequestMatcher(adminServerContextPath + "/instances", HttpMethod.POST.toString()),  // <6>
                        new AntPathRequestMatcher(adminServerContextPath + "/instances/*", HttpMethod.DELETE.toString()),  // <6>
                        new AntPathRequestMatcher(adminServerContextPath + "/actuator/**")  // <7>
                )
                .and()
                .rememberMe().key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600);

    }

}