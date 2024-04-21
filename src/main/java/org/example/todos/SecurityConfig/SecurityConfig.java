package org.example.todos.SecurityConfig;

import lombok.RequiredArgsConstructor;
import org.example.todos.Service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig { //   1 تكوينات
// اعاده تغيير الايث الاثنين
   private final MyUserDetailService myUserDetailService;


    // ميثود الداو مسوؤله عن الايثونتيكيشن

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService); // 6 مسوؤله عن الاسم اليوزرنيم يعني اسويه انا يروح الداتا ياخذ الاسم
   daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());// هاش الباسوورد

        return daoAuthenticationProvider;
    }


    // مسووؤله عن االايثورايزيشن
     @Bean // chain سلسله
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()// ثغره هجمه عمليات معينه ع حساب المستخدم
                .sessionManagement()      // طالما مسوي لوقن فيه بيانات محفوظه يعني لو رحت للمنتجات يطلع حسابي فوق هذي سيشين
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // عند الحاجه تحفظ
                .and()
                .authenticationProvider(daoAuthenticationProvider()) // الميثود الي فوق
                .authorizeHttpRequests() // api
                .requestMatchers("/api/v1/auth/register").permitAll()//        كل اapi له ماتشير
                .requestMatchers("/api/v1/auth/get-all").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated() // الباقي للمسجلين دخول
                .and()
                .logout().logoutUrl("/api/v1/auth/logout").permitAll()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
         return http.build();

// permitAll للكل نفس صفحه الهوم
// hasAuthority مثل حذف يوزر ف احدد ادمن لان هو يقدر
// hasAnyAuthority احدد اكثر من رول شخص
//  نحط نجمتين يحدد كلشي اذا عندي ملف auth كامل لشخص "/api/v1/auth/**"


    }

}
