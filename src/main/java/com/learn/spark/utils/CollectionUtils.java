package com.learn.spark.utils;

import java.util.Collection;

public class CollectionUtils<E> {
	 
	public  String toStringList(Collection<E> set, String split){
		StringBuffer buff = new StringBuffer();
		for(E str : set){
			if(str == null) continue;
			buff.append(str.toString()).append(split);
		}
		int len = buff.length();
		int slen = split.length();
		if(len > slen){
			buff.delete(len - slen, len);
		}
		return  buff.toString();
	}
}
