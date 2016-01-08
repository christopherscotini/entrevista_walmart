package com.walmart.rest.json;

public class ErrorJSON {

	private int code;
	private String msg;
	
	public ErrorJSON(int i, String msg) {
		super();
		this.code = i;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
