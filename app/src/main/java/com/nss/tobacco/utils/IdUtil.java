package com.nss.tobacco.utils;


import java.util.UUID;


public class IdUtil {
	public static String getId(){
		String id=UUID.randomUUID().toString().replaceAll("-","");
		return id;
	}
}