package com.example.lojaSpring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

      @Autowired
      private UsuarioDetailsConfig usuarioDetailsConfig ;

      @Override
      protected void configure(HttpSecurity http) throws Exception {
            http.
                  authorizeRequests() //define com as requisições HTTP devem ser tratadas com relação à segurança.
                    .antMatchers("/webjars/**").permitAll() //liberar executar bootstrap no /login
                    .antMatchers("/vendas/list").hasAnyRole("ADMIN")
                    .anyRequest() //define que a configuração é válida para qualquer requisição.
                        .authenticated() //define que o usuário precisa estar autenticado.
                        .and()
                  .formLogin() //define que a autenticação pode ser feita via formulário de login.
                              .loginPage("/login") // passamos como parâmetro a URL para acesso à página de login que criamos
                    .permitAll() //define que essa página pode ser acessada por todos, independentemente do usuário estar autenticado ou não.
                              .and()
                    .logout() //habilitando o recurso de logout do framework via configuração Java.
                    .permitAll() ;//indicamos que tudo que estiver relacionado ao logout() pode ser acessado por usuários autenticados ou não.
      }
      @Autowired
      public void configureGlobal(AuthenticationManagerBuilder builder)
              throws Exception {
            builder.userDetailsService(usuarioDetailsConfig).passwordEncoder(new BCryptPasswordEncoder());
//                    .inMemoryAuthentication()
//                    .withUser("kas").password("$2a$10$46WUJrOI7OwUWnoRsVkIx.5fY2PtXaaOb4HGzqrJ7qwZuF/tWZJte").roles("ADMIN")
//                    .and()
//                    .withUser("teste").password("$2a$10$46WUJrOI7OwUWnoRsVkIx.5fY2PtXaaOb4HGzqrJ7qwZuF/tWZJte").roles("USER");

      }

      @Bean
      public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
      }
}
