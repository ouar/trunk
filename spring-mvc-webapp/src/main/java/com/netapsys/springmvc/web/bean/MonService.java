package com.netapsys.springmvc.web.bean;

public class MonService {
	public String hello(String msg) {
		String s = "Hello " + msg;
		System.out.println(s);
		return s;
	}
}
