package one.digitalinnovation.personapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("one.digitalinnovation.personapi"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("REST API Example")
    			.description("REST API - A small project for Bootcamp Code Anywhere.")
    			.version("1.0.0")
    			.contact(new Contact("Douglas Reis", "https://www.linkedin.com/in/douglas-reis-fullstack", "dreis.gti@gmail.com"))
    			.license("Apache License Version 2.0")
    			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
    			.build();
    }
}
