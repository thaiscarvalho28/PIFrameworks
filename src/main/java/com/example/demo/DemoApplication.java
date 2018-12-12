package com.example.demo;

import com.example.demo.ConfiguracaoFiltros.ClienteFiltro;
import configuração.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class})
public class DemoApplication {

    @Bean
    public FilterRegistrationBean filtroJwt(){
        FilterRegistrationBean fr = new FilterRegistrationBean();
        fr.setFilter(new ClienteFiltro());
        fr.addUrlPatterns("/auth/*");
        return fr;
    }
    
    
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
        
        
}
