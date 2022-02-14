package com.TechPro.SpringBootStudy.BasicAuthentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
 public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {//tanimli oldugu classta  form basic security yerine  (basic authentication ) configure(ayarlama) eder


    private final PasswordConfig passEncode; //final obce bir deger almali bu degeri alacagi construcktor crate edilmeli
      @Autowired //PC classtan create edilen objeye deger atayan cons.
      public ApplicationSecurityConfig(PasswordConfig passEncode){
          this.passEncode=passEncode;
      }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(). //spring boot sana güveniyorum delete put patch calistir sorumluluk bende.Bu methodlarin blockun(default) kaldir
                authorizeRequests(). //Requestler icin yetki sorgula (get put patch delete post)
                antMatchers("/","index","/css/*","/js/*").permitAll().  //antMatchers method parametresindeki url'lere izin ver
                anyRequest().//her request icin
                authenticated().//kullanici sorgula
                and().//neye göre
                httpBasic();//httpBasic'e göre
        //gelen kim
        //neden geldi
        // gelen kisi gelme sebebine yetkili mi
        // bunlari http temelli sorgula
        //her request te APP username ve pass(security) control edilmeli logout yapmaya gerek kalmayacak
    //Benim istedigim kisiler apartmana girdikten sonra herkesi yetki sahibi olsun




    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
    //    User.builder().username("Yildiz").password("1234").roles("Yaparsin aslansin sen").build();
        UserDetails student =User.builder().username("celil").password(passEncode.passSifrele().encode("1234")).authorities(ApplicationUserRoles.STUDENT.izinOnayla()).build();
        UserDetails hanimaga =User.builder().username("ipek").password(passEncode.passSifrele().encode("1212")).roles("hanim AGA hukumet gadin").build();
        UserDetails admin = User.
                builder().
                username("admin").password(passEncode.passSifrele().encode("nimda")).
                //roles("ADMIN").
                        authorities(ApplicationUserRoles.ADMIN.izinOnayla()).
                build();//convension tanım
        // UserDetails admin = User.builder().username("admin").password("nimda").roles("ADMIN").build();//convension tanım
        // return student;//returm uyumsuzluk hatası


        return new InMemoryUserDetailsManager(student,admin,hanimaga);

    }
}
