package com.lovnx.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.RateLimiter;

public class ApiCallDemo {

    private int permitsPerSecond = 1; //每秒1个许可
    private int threadNum = 10;

    public static void main(String[] args) {

        new ApiCallDemo().call();
    }

    private void call() {
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        
        final RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond); 
        
        for (int i=0; i<threadNum; i++) {
            executor.execute(new ApiCallTask(rateLimiter));
        }
        
        executor.shutdown();
    }
}

class ApiCallTask implements Runnable{
	
    private RateLimiter rateLimiter;
    
    private boolean runing = true;
    
    public ApiCallTask(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void run() {
        while(runing){
        	rateLimiter.acquire(); // may wait
            getData();
        }
    }

    /**模拟调用合作伙伴API接口*/
    private void getData(){
        System.out.println(Thread.currentThread().getName()+" runing!");
//        try {
//            TimeUnit.MILLISECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}