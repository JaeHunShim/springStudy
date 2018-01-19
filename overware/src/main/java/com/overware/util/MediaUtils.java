package com.overware.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	
	private static Map<String,MediaType> mediaMap;
	
	static {
		
		mediaMap=new HashMap<String,MediaType>();
		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		
	}
	public static MediaType getMediaType(String type) {
		
		return mediaMap.get(type.toUpperCase()); //확장자명이 소문자로 되어있을수도 있으니까 대분자로 치환해서 리턴한다. 
	}
}
