package com.mindasoft.cloud.commons.util;

import com.mindasoft.cloud.commons.enums.CommonStateEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author huangmin
 * @email huangmin@sina.com
 * @date 2016年10月27日 下午9:59:27
 */
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 0;
	public static final int FAIL = 500; // 服务器内部错误
	public static final int NO_PERMISSION = 401; // 未授权

	private String msg = "success";
	private int code = SUCCESS;
	private T data;

	public R() {
	}

	public R(T data) {
		this.data = data;
	}

	public R(T data, String msg) {
		this.data = data;
		this.msg = msg;
	}

	public R(Throwable e) {
		this.msg = e.getMessage();
		this.code = FAIL;
	}

	public static R fail() {
		R r = new R();
		r.setCode(FAIL);
		r.setMsg("请求失败");
		return r;
	}

	public static R fail(String msg) {
		R R = new R();
		R.setCode(FAIL);
		R.setMsg(msg);
		return R;
	}

	public static R fail(int code,String msg) {
		R R = new R();
		R.setCode(code);
		R.setMsg(msg);
		return R;
	}

	public static R fail(CommonStateEnum commonStateEnum) {
		R R = new R();
		R.setCode(commonStateEnum.getCode());
		R.setMsg(commonStateEnum.getMessage());
		return R;
	}

	public static R ok() {
		return new R();
	}

	public static R ok(Object data) {
		return new R(data);
	}

	public static R ok(Object data, String msg) {
		return new R(data, msg);
	}

	public R put(T data){
		this.data = data;
		return this;
	}

	public boolean isOk() {
		return SUCCESS == this.getCode();
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
