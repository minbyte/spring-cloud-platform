package com.mindasoft.cloud.security.controller;

import com.mindasoft.cloud.admins.feign.AdminFeign;
import com.mindasoft.cloud.commons.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @author: min
 * @date: 2018/12/20 9:56
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/principal")
public class PrincipalController {

    @Resource
    private AdminFeign adminFeign;

    @GetMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }

    @GetMapping("/test")
    public String test(){
        R r = adminFeign.info("111");

        if(r.isOk()){
            System.out.println(r.getData());
        }
        return "haha,test;";
    }

}
