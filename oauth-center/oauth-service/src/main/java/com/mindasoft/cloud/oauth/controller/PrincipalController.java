package com.mindasoft.cloud.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author: min
 * @date: 2018/12/20 9:56
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/principal")
public class PrincipalController {

    @GetMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }

    @GetMapping("/test")
    public String test(){
        return "haha,test;";
    }

}
