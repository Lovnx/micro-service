package com.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SERVICE-B")
public interface TestFeignClient {
	
  @RequestMapping("/add")
  public String add(@RequestParam("a") Integer a,@RequestParam("b") Integer b);
}