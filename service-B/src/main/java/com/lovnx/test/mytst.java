package com.lovnx.test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class mytst {
	//单位时间内的调用次数
	private int flag = 10;
	//线程数量
    private int threadNum = 10;
    //单位时间1000ms * 60 = 1min
    private int timeRound = 1000*60;
    //用来标记调用次数
    private AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {

        new mytst().call();
    }

    private void call() {
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
          @Override
          public void run() {
        	  num.set(0);;
          }
        }, 0, timeRound);
        
        for (int i = 0; i < 1000; i++) {
	        executor.execute(new Runnable() {
	        	
	        	@Override
				public void run() {
	        		try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	        		System.out.println(Thread.currentThread().getName()+"   进来了!");
	        		num.incrementAndGet();
	        		if (num.get() <= flag) {
						System.out.println(Thread.currentThread().getName()+"   执行任务!");
					} else {
						System.out.println(Thread.currentThread().getName()+"   执行任务失败!调用超限！");
					}
				}
			});
        }
        
        executor.shutdown();
    }
}
