package com.lovnx.web;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Class RateLimiter 实现基于令牌桶算法,有两个参数：
 * 
 * @para  burstSize - 允许作为突发事件进入系统的最大请求数
 * @para  averageRate - 预期的每秒请求数（ratelimiters使用分钟也支持）
 * 
 * @author yezhiyuan
 */
public class RateLimiter {
	//限流时间单位
    private final long rateToMsConversion;
    //当前可供消费的令牌数量
    private final AtomicInteger consumedTokens = new AtomicInteger();
    //上一次填充令牌的时间戳
    private final AtomicLong lastRefillTime = new AtomicLong(0);

 	//限流时间单位可设置为TimeUnit.SECONDS,已废弃
    @Deprecated
    public RateLimiter() {
        this(TimeUnit.SECONDS);
    }
    
    //限流时间单位可设置为TimeUnit.SECONDS或TimeUnit.MINUTES
    public RateLimiter(TimeUnit averageRateUnit) {
        switch (averageRateUnit) {
            case SECONDS:
                rateToMsConversion = 1000;
                break;
            case MINUTES:
                rateToMsConversion = 60 * 1000;
                break;
            default:
                throw new IllegalArgumentException("TimeUnit of " + averageRateUnit + " is not supported");
        }
    }
    
    //这个方法默认传的当前系统时间戳
    public boolean acquire(int burstSize, long averageRate) {
        return acquire(burstSize, averageRate, System.currentTimeMillis());
    }

    public boolean acquire(int burstSize, long averageRate, long currentTimeMillis) {
    	//这里为了避免傻白甜将burstSize和averageRate设为负值而抛出异常
        if (burstSize <= 0 || averageRate <= 0) {
            return true;
        }
        //填充令牌
        refillToken(burstSize, averageRate, currentTimeMillis);
        //消费令牌成功与否
        return consumeToken(burstSize);
    }

    private void refillToken(int burstSize, long averageRate, long currentTimeMillis) {
    	//得到上一次填充令牌的时间戳
        long refillTime = lastRefillTime.get();
        //时间间隔timeDelta = 传进来的时间戳currentTimeMillis - 上一次填充令牌的时间戳refillTime
        long timeDelta = currentTimeMillis - refillTime;
        //计算出新的令牌数量newTokens = 时间间隔 * 平均速率 / 限流时间单位
        long newTokens = timeDelta * averageRate / rateToMsConversion;
        //如果新的令牌数量大于0个
        if (newTokens > 0) {
        	//设置新的填充令牌时间戳newRefillTime，如果上一次填充令牌的时间戳==0就取传进来的currentTimeMillis，如果！=0，
        	//就等于上一次填充令牌的时间戳 + 新的令牌数量 * 限流时间单位 / 平均速率
            long newRefillTime = refillTime == 0
                    ? currentTimeMillis
                    : refillTime + newTokens * rateToMsConversion / averageRate;
            //如果lastRefillTime内存偏移量值==上一次填充令牌的时间戳refillTime，则将lastRefillTime内存值设置为新的填充令牌时间戳newRefillTime
            //成功时进入条件体放令牌
            if (lastRefillTime.compareAndSet(refillTime, newRefillTime)) {
            	//放令牌（核心代码）
                while (true) {
                	//得到当前已消费的令牌数量currentLevel
                    int currentLevel = consumedTokens.get();
                    //获取校正令牌数量adjustedLevel，从当前已消费的令牌数量currentLevel和允许最大请求数burstSize间取小者，以防允许最大请求数burstSize变小
                    //这一步和下一步叫做“流量削峰”
                    int adjustedLevel = Math.min(currentLevel, burstSize);
                    //获取新的令牌数量newLevel，0 与 （校正值 - 计算值）之间取大者
                    int newLevel = (int) Math.max(0, adjustedLevel - newTokens);
                    //如果当前已消费的令牌内存偏移量等于consumedTokens等于currentLevel，则将已消费的令牌量consumedTokens设置为新的令牌数量newLevel
                    //终止放令牌,在已消费偏移量不等于currentLevel时循环计算，直到它们相等
                    if (consumedTokens.compareAndSet(currentLevel, newLevel)) {
                        return;
                    }
                }
            }
        }
    }
    
    //消费令牌，传入突发量
    private boolean consumeToken(int burstSize) {
    	//取令牌
        while (true) {
        	//得到当前已消费的令牌数量currentLevel
            int currentLevel = consumedTokens.get();
            //如果已消费令牌量大于等于突发量，则不能消费令牌
            if (currentLevel >= burstSize) {
                return false;
            }
            //消费令牌，已消费令牌量+1
            if (consumedTokens.compareAndSet(currentLevel, currentLevel + 1)) {
                return true;
            }
        }
    }

    //重置令牌桶
    public void reset() {
        consumedTokens.set(0);
        lastRefillTime.set(0);
    }
}