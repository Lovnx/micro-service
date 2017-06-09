package com.lovnx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

public class RedisUtils {

    private static ReentrantLock lockPool = new ReentrantLock();

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    /** Jedis连接池 */
    private static JedisPool jedisPool = null;

    /** Redis服务器IP */
    private static String host="172.16.16.72";
    /** Redis的端口号 */
    private static int port=6379;
    /** 访问密码 */
    private static String password="lemonkz9*l";
    /** 超时时间 */
    private static int timeout=10000;
    /**
     * 可用连接实例的最大数目，默认值为8
     * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
     */
    private static int maxTotal = 1024;
    /** 控制一个pool最多有多少个状态为idle(空闲的)的Jedis实例，默认值为8 */
    private static int maxIdle = 200;
    /**
     * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时
     * 如果超过等待时间，则直接抛出JedisConnectionException
     */
    private static int maxWaitMillis = 10000;
    /**
     * 在borrow一个Jedis实例时，是否提前进行Validate操作
     * 如果为true，则得到的Jedis实例均是可用的
     */
    private static boolean testOnBorrow = false;

    static {
        try {
//            String redisPropertiesPath = new StringBuffer("classpath:/redis.properties").toString();
//
//            InputStream redisIS = RedisUtils.class.getClassLoader().getResourceAsStream(redisPropertiesPath);
//            Properties redisProperties = new Properties();
//                redisProperties.load(redisIS);
//            host = redisProperties.getProperty("redis.host");
//            port = Integer.parseInt(redisProperties.getProperty("redis.port"));
//            password = redisProperties.getProperty("redis.password");
//
//            maxTotal = Integer.parseInt(redisProperties.getProperty("redis.maxTotal"));
//            maxIdle = Integer.parseInt(redisProperties.getProperty("redis.maxIdle"));
//            timeout = Integer.parseInt(redisProperties.getProperty("redis.timeout"));
//            maxWaitMillis = Integer.parseInt(redisProperties.getProperty("redis.maxWaitMillis"));
//            testOnBorrow = Boolean.parseBoolean(redisProperties.getProperty("redis.testOnBorrow"));
            LOGGER.info("初始化Redis配置参数成功.");
        } catch (Exception ex) {
            LOGGER.error("初始化Redis配置参数失败.", ex);
        }
        initialPool();
        setShutdownWork();
    }

    private RedisUtils() {
        throw new RuntimeException("禁止实例化Redis访问工具类.");
    }

    /**
     * 初始化Redis连接池
     */
    private static void initialPool(){
        lockPool.lock();
        try {
            if(jedisPool == null) {
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(maxTotal);
                config.setMaxIdle(maxIdle);
                config.setMaxWaitMillis(maxWaitMillis);
                config.setTestOnBorrow(testOnBorrow);
                jedisPool = new JedisPool(config, host, port, timeout, password);
                LOGGER.info("创建Redis Pool成功.");
            }
        } catch (Exception ex) {
            LOGGER.error("创建Redis Pool失败.", ex);
        } finally {
            lockPool.unlock();
        }
    }

    /**
     * 设置系统停止时需执行的任务
     */
    private static void setShutdownWork() {
        try{
            Runtime runtime = Runtime.getRuntime();
            runtime.addShutdownHook(new Thread(){
                @Override
                public void run() {
                    try {
                        if(jedisPool != null) {
                            jedisPool.destroy();
                            jedisPool = null;
                            LOGGER.info("关闭Redis Pool成功.");
                        }
                    } catch (Exception ex) {
                        LOGGER.error("关闭Redis Pool失败.", ex);
                    }
                }
            });
            LOGGER.info("设置系统停止时关闭Redis Pool的任务成功.");
        } catch (Exception ex) {
            LOGGER.error("设置系统停止时关闭Redis Pool的任务失败.");
        }
    }

    /**
     * 从连接池中获取Jedis实例
     * @return Jedis Jedis实例
     */
    public static Jedis getJedis() {
        if (jedisPool == null) {
            initialPool();
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception ex) {
            LOGGER.error("获取Jedis失败.", ex);
        }
        return jedis;
    }
}
