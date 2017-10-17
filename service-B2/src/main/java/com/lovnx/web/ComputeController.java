package com.lovnx.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/**" ,method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b,HttpServletRequest request) {
    	System.out.println(request.getRequestURL());
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return "From Service-B, Result is " + r+"\nPort:"+instance.getPort();
    }

    //B服务调用A服务
    @RequestMapping(value="testServiceA",method=RequestMethod.GET)
    public String testServiceB(@RequestParam Integer a,@RequestParam Integer b){
    	RestTemplate restTemplate=new RestTemplate();
    	return restTemplate.getForObject("http://localhost:7074/add?a="+a+"&b="+b, String.class);
    }
}