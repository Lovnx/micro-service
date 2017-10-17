package com.lovnx;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.lovnx.filter.AccessFilter;
import com.lovnx.filter.ErrorFilter;
import com.lovnx.filter.RateLimitFilter;
import com.lovnx.filter.ResultFilter;
import com.lovnx.filter.UuidFilter;
import com.lovnx.filter.ValidateFilter;

@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	
	@Bean
	public RateLimitFilter rateLimiterFilter() {
		return new RateLimitFilter();
	}
	
	@Bean
	public ResultFilter resultFilter() {
		return new ResultFilter();
	}
	
	@Bean
	public UuidFilter uuidFilter() {
		return new UuidFilter();
	}
	
	@Bean
	public ValidateFilter validateFilter() {
		return new ValidateFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

}
