package com.mgtv.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: huangmin
 * @email: huangmin@mgtv.com
 * @date: 2018/8/17 9:08
 * @version: 1.0.0
 */
@Component
public class TicketFilter extends ZuulFilter {

    @Value("${app.ticket.filter.enabled:false}")
    private boolean enabled;

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println(String.format("%s TicketFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
        String ticket = request.getParameter("ticket");// 获取请求的参数
        if(StringUtils.isBlank(ticket)){
            ticket = request.getHeader("ticket"); // 请求头里面参数
        }

        if(!StringUtils.isBlank(ticket)){
//            ticketService.isValid(ticket,null);
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            return null;
        }else{
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(200);// 返回错误码
            ctx.setResponseBody("{\"code\":\"401\",\"msg\":\"Unauthorized\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }

    }

    @Override
    public String filterType() {
        return "pre";// 前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0; // 优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return this.enabled;  // 是否执行该过滤器，此处为true，说明需要过滤
    }


}
