package com.lovnx.web;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());
    
	//单位时间内的调用次数
	private int flag = 10;
    //单位时间1000ms * 60 = 1min
    private static int timeRound = 1000*60;
    //用来标记调用次数
    private static AtomicInteger num = new AtomicInteger(0);

    @Autowired
    private DiscoveryClient client;
    
    static{
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
          @Override
          public void run() {
        	  num.set(0);;
          }
        }, 0, timeRound);
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
    	
        num.incrementAndGet();
        
        if (num.get() <= flag) {
	        ServiceInstance instance = client.getLocalServiceInstance();
	        Integer r = a + b;
	        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
	        return "From Service-B, Result is " + r+"\nPort:"+instance.getPort();
        }
        return "调用次数超限，一分钟内最多只能调用10次！";
    }

}