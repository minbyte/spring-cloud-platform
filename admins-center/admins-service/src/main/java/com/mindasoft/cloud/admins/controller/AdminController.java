package com.mindasoft.cloud.admins.controller;

import com.mindasoft.cloud.admins.param.LoginParam;
import com.mindasoft.cloud.commons.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 管理员接口
 * @author: min
 * @date: 2018/12/17 13:55
 * @version: 1.0.0
 */
@RestController
@RequestMapping("admin")
public class AdminController {


    @GetMapping("info")
    public R info(String token){
        HashMap map = new HashMap();
        map.put("name","admin");
        map.put("avatar","https://img.mgtv.com/imgotv-member/user/avt.jpg");
        map.put("roles", new ArrayList<String>(){{
            add("admin");
        }});
        return R.ok().put(map);
    }

    @GetMapping("infoByAdminId")
    public R infoById(String adminId){
        HashMap map = new HashMap();
        map.put("name","user");
        map.put("avatar","https://img.mgtv.com/imgotv-member/user/avt.jpg");
        map.put("roles", new ArrayList<String>(){{
            add("user");
        }});
        return R.ok().put(map);
    }

    @PostMapping("login")
    public R login(@RequestBody LoginParam param){
        if("admin".equals(param.getUsername()) && "admin".equals(param.getPassword())){
            return R.ok().put(new HashMap<String,String>(){{put("token","admin");}});
        }else{
            return R.fail("登录失败");
        }
    }

    @PostMapping("logout")
    public R logout(String token){
        return R.ok();
    }

}
