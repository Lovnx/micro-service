package com.hystrix.normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {
	
  @Autowired
  private HystrixService ribbonHystrixService;

  @RequestMapping("/hystrix")
  public String findById(@RequestParam Integer a,@RequestParam Integer b) {
    return this.ribbonHystrixService.findById(a,b);
  }
}