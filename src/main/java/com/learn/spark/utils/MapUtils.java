package com.learn.spark.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils<K,V> {
	 
	public  String mapToString(Map<K,V>  map, String splitIn ,String splitOut){
		StringBuffer buff = new StringBuffer();
		for(Map.Entry<K,V>  entry : map.entrySet()){
			if(entry == null) continue;
			buff.append(entry.getKey().toString()).append(splitIn).append(entry.getValue().toString()).append(splitOut);
		}
		int len = buff.length();
		int slen = splitOut.length();
		if(len > slen){
			buff.delete(len - slen, len);
		}
		return  buff.toString();
	}
	public Map<K,V> stringToMap(String mapStr,String splitIn ,String splitOut){
		Map<K,V> map = new HashMap<K,V>();
		String[] array = mapStr.trim().split(splitOut);
		for(String str : array){
			String[] strArray = str.split(splitOut);
			if(strArray.length < 2) continue;
			map.put((K)strArray[0], (V)strArray[1]);
		}
		return map;
	}
}
