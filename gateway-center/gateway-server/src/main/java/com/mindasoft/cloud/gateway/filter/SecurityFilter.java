//package com.mindasoft.cloud.gateway.filter;
//
//import com.mindasoft.cloud.gateway.util.FilterIgnoreProperties;
//import com.mindasoft.cloud.gateway.util.FilterSecretProperties;
//import com.mindasoft.cloud.gateway.util.MD5Utils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.TreeMap;
//
///**
// * @author: huangmin
// * @email: huangmin@mgtv.com
// * @date: 2018/8/1 17:58
// * @version: 1.0.0
// */
//@Component
//public class SecurityFilter implements GlobalFilter, Ordered {
//
//    @Value("${app.ticket.filter.enabled:false}")
//    private boolean enabled;
//    @Autowired
//    FilterIgnoreProperties filterIgnoreConfig;
//    @Autowired
//    FilterSecretProperties filterSecretConfig;
//
//    public static final String SPECIAL_TICKET = "asdf0987";
//
//    private boolean checkIgnore(String url){
//        boolean isIgnore = false;
//        List<String> urls = filterIgnoreConfig.getUrls();
//        for(String pattern : urls){
//            if(url.matches(pattern.replace("*","(.*)"))){
//                isIgnore = true;
//                break;
//            }
//        }
//        return isIgnore;
//    }
//
//    private boolean checkSecret(String url){
//        boolean isIgnore = false;
//        List<String>  urls = filterSecretConfig.getUrls();
//        for(String pattern : urls){
//            if(url.matches(pattern.replace("*","(.*)"))){
//                isIgnore = true;
//                break;
//            }
//        }
//        return isIgnore;
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request =  exchange.getRequest();
//
//        System.out.println(String.format("%s TicketFilter request to %s", request.getMethod(), request.getURI().toString()));
//
//        // 获取请求的参数
//        String ticket = request.getQueryParams().getFirst("ticket");
//        if(StringUtils.isBlank(ticket)){
//            // 请求头里面参数
//            ticket = request.getHeaders().getFirst("ticket");
//        }
//
//        if(checkIgnore(request.getURI().getPath())){
//            // 不需要检验权限
//            return chain.filter(exchange);
//        }else{
//            // 需要检验权限
//            if(StringUtils.isNotBlank(ticket)){
//                // 1、检测ticket
//                if(!SPECIAL_TICKET.equals(ticket)){
//                    // TODO 如果不是特定的ticket，则需要检测
////                ticketService.isValid(ticket,null);
//                    boolean valid = false;
//                    if(!valid){
//                        exchange.getResponse().setStatusCode(HttpStatus.OK);
//                        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
//                        byte[] bytes = "{\"code\":\"403\",\"msg\":\"Ticket invalid\"}".getBytes(StandardCharsets.UTF_8);
//                        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//                        return exchange.getResponse().writeWith(Flux.just(buffer));
//                    }
//                }
//
//                return chain.filter(exchange);
//            }else if(checkSecret(request.getURI().getPath())){
//                // 2、没有ticket检测sign
//                String sign = request.getQueryParams().getFirst("sign");
//                if(StringUtils.isBlank(sign)){
//                    sign = request.getHeaders().getFirst("sign");
//                }
//
//                TreeMap<String,Object> param = new TreeMap<>();
//                request.getQueryParams().forEach((k,v)->{
//                    if(!"sign".equals(k)){
//                        param.put(k,v.get(0));
//                    }
//                });
//                if(!MD5Utils.isValid(sign, param, filterSecretConfig.getKey())){
//                    exchange.getResponse().setStatusCode(HttpStatus.OK);
//                    exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
//                    byte[] bytes = "{\"code\":\"402\",\"msg\":\"Signature error\"}".getBytes(StandardCharsets.UTF_8);
//                    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//                    return exchange.getResponse().writeWith(Flux.just(buffer));
//                }
//                return chain.filter(exchange);
//            }else {
//                // 都没有传的没有权限
//                exchange.getResponse().setStatusCode(HttpStatus.OK);
//                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
//                byte[] bytes = "{\"code\":\"401\",\"msg\":\"Unauthorized\"}".getBytes(StandardCharsets.UTF_8);
//                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//                return exchange.getResponse().writeWith(Flux.just(buffer));
//            }
//        }
//    }
//
//    @Override
//    public int getOrder() {
//        return -200;
//    }
//}
