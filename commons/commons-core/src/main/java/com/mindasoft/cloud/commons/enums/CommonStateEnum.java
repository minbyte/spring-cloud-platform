package com.mindasoft.cloud.commons.enums;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: zhu.qi
 * @Date: 2018/9/13 15:23
 * @Description: http业务请求状态枚举:  code= 端口号前三位+四位业务码
 * 按模块分开:
 * 公用的code从1开始好了,自行定义, 0和500已经被框架使用
 * game模块: 708xxxx
 * user模块: 704xxxx
 */
public enum CommonStateEnum {
    TV_ERROR(-3, ""),   //端上请求返回错误
    SYSTEM_ERROR(-2, "系统繁忙"),
    NO_LOGIN(-1, "用户未登录"),
    SUCCESS(0, "SUCCESS"),
    FAIL(1, "FAIL"),
    NO_PERMISSION(2, "NO_PERMISSION"),
    FEIGN_TIMEOUT(3, "FEIGN TIMEOUT"), //内部请求超时
    PARAM_ERROR(4, ""), //参数错误
    USER_NOT_EXIST(5, "用户信息不存在")

    ;


    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static CommonStateEnum tvError(String message) {
        TV_ERROR.setMessage(message);
        return TV_ERROR;
    }

    public static CommonStateEnum paramError(String msg){
        PARAM_ERROR.setMessage("param error: " + msg);
        return PARAM_ERROR;
    }

    CommonStateEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getJSONString() {
        return getJson().toString();
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        obj.put("code", this.getCode());
        obj.put("message", this.getMessage());
        return obj;
    }
}
