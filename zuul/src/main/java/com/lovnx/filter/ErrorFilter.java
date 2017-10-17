package com.lovnx.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.reactivex.netty.protocol.http.server.HttpServerResponse;

import org.bouncycastle.jcajce.provider.digest.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorFilter extends ZuulFilter  {

    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

	@Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //HttpServletResponse response = ctx.getResponse();
        
        log.info("进入错误异常的过滤器！");
        
        log.info("===============");
        
//        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
//        System.out.println(request.getRequestURL());
        
//        Object accessToken = request.getParameter("accessToken");
//        if(accessToken == null) {
//            log.warn("access token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        log.info("access token ok");
        return null;
    }

}
