package com.zb.util;

/**
 * 数据传输对象（后端输出对象）
 * @param <T>
 */
public class Dto{
	private String success; //判断系统是否出错做出相应的true或者false的返回，与业务无关，出现的各种异常
	private String errorCode;//该错误码为自定义，一般0表示无错
	private String msg;//对应的提示信息
	private Object data;//具体返回数据内容(pojo、自定义VO、其他)

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}