package com.learn.spark.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Book implements Serializable{//要可序列化,否则不能再集群间传输
			/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 2671946353540993579L;
			String bookId;
			String content;
			String contentStartPos;
			public String getBookId() {
				return bookId;
			}
			public void setBookId(String bookId) {
				this.bookId = bookId;
			}
			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content;
			}
			public String getContentStartPos() {
				return contentStartPos;
			}
			public void setContentStartPos(String contentStartPos) {
				this.contentStartPos = contentStartPos;
			}	
			public Book() {
				
			}
			public static void main(String[] args) {
				Set<String> set = new HashSet<String>();
				set.add("ww");
				set.add("w3e");
				set.add("wde");
				set.add("45");
				set.add("pp");
				set.add("1");
				for(String str : set) {
					System.out.println(str);
				}
				System.out.println("---------------");
				for(String str : set) {
					System.out.println(str);
				}
				System.out.println("---------------");
				for(String str : set) {
					System.out.println(str);
				}
			}
		
}
