package SpringDB.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final ObjectMapper objectMapper;
    private final RestAuthFailureHandler failureHandler;
    private final RestAuthSuccesHandler succesHandler;

    public SecurityConfig(DataSource dataSource, ObjectMapper objectMapper, RestAuthFailureHandler failureHandler, RestAuthSuccesHandler succesHandler) {
        this.dataSource = dataSource;
        this.objectMapper = objectMapper;
        this.failureHandler = failureHandler;
        this.succesHandler = succesHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().withDefaultSchema().dataSource(dataSource).withUser("admin")
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode("admin"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated().and().formLogin().permitAll()
                .and()
                .addFilter(autoticationFilter())
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }
    public JsonAuthenticationFilter autoticationFilter() throws Exception{
        JsonAuthenticationFilter autoticationFilter = new JsonAuthenticationFilter(objectMapper);

        autoticationFilter.setAuthenticationSuccessHandler(succesHandler);
        autoticationFilter.setAuthenticationFailureHandler(failureHandler);
        autoticationFilter.setAuthenticationManager(super.authenticationManager());
        return autoticationFilter;
    }
}
