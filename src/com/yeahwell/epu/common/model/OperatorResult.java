package com.yeahwell.epu.common.model;

public class OperatorResult {

	/**
	 * 操作返回标志,true代表成功,false代表失败,其他可根据具体业务定义
	 */
	private String flag = "false";
	
	/**
	 * 操作返回结果
	 */
	private String message = "操作失败";
	
	/**
	 * 操作结果返回数据
	 */
	private Object data;
	
	public OperatorResult(){}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String toString(){
		return String.format("操作结果-%s, 返回消息-%s", flag, message);
	}
	
}
