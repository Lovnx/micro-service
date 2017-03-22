package com.feign_hystrix.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feign_hystrix.feign.TestFeignClient.HystrixClientFallback;


@FeignClient(name = "service-B",fallback = HystrixClientFallback.class)
public interface TestFeignClient {
	
  @RequestMapping("/add")
  public String add(@RequestParam("a") Integer a,@RequestParam("b") Integer b);

  /**
   * 这边采取了和Spring Cloud官方文档相同的做法，将fallback类作为内部类放入Feign的接口中，当然也可以单独写一个fallback类。
   * @author eacdy
   */
  @Component
  static class HystrixClientFallback implements TestFeignClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

	@Override
	public String add(Integer a, Integer b) {
		HystrixClientFallback.LOGGER.info("异常发生，进入fallback方法，接收的参数： {},{}",a,b);
		return "feign-hystrix";
	}
  }
}