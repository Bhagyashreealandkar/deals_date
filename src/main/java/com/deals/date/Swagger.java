package com.deals.date;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {

	// The select() method called on Docket bean returns an "ApiSelectorBuilder". This provides "apis()" and "paths()" methods to filter the controllers and methods being documented using string predicates.
		@Bean
		public Docket cartApi() {
			
			final String groupName1="Cart";
			return new Docket(DocumentationType.SWAGGER_2).groupName(groupName1).apiInfo(metadata()).select().paths(regex("/cart.*")).build();
		}
		
		@Bean
		public Docket adminApi() {
			final String groupName2="Admin";
			return new Docket(DocumentationType.SWAGGER_2).groupName(groupName2).apiInfo(metadata()).select().paths(regex("/admin.*")).build();
		}

		@Bean
		public Docket customertApi() {
			final String groupName3="Customer";
			return new Docket(DocumentationType.SWAGGER_2).groupName(groupName3).apiInfo(metadata()).select().paths(regex("/customer.*")).build();
		}
		
		@Bean
		public Docket feedbackApi() {
			final String groupName4="Feedback";
			return new Docket(DocumentationType.SWAGGER_2).groupName(groupName4).apiInfo(metadata()).select().paths(regex("/Feedback.*")).build();
		}
		
		
		
		@Bean
		public Docket orderApi() {
			final String groupName5="Order";
			return new Docket(DocumentationType.SWAGGER_2).groupName(groupName5).apiInfo(metadata()).select().paths(regex("/order.*")).build();
		}
		
		
		@Bean
		public Docket productsApi() {
			final String groupName6="Products";
			return new Docket(DocumentationType.SWAGGER_2).groupName(groupName6).apiInfo(metadata()).select().paths(regex("/products.*")).build();
		}
		
		@Bean
		public Docket eventApi() {
			final String groupName7="Calendar";
			return new Docket(DocumentationType.SWAGGER_2).groupName(groupName7).apiInfo(metadata()).select().paths(regex("/calendar.*")).build();
		}
		
		@Bean
		public Docket paymentApi() {
			final String groupName8="Payment";
			return new Docket(DocumentationType.SWAGGER_2).groupName(groupName8).apiInfo(metadata()).select().paths(regex("/payment.*")).build();
		}
		
		private ApiInfo metadata() {
			return new ApiInfoBuilder().title("DealsDate").description("API reference guide for developers").termsOfServiceUrl("https://www.abccg.com/").version("1.0").build();	
		}
}