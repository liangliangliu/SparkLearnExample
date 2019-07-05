package com.learn.spark.jdk8;


public class Lambda {

	public static void main(String[] args) {
		FunInterface fi = (x) ->   {  //多条语句要用{}括起来，且若有返回值必须用return关键字返回，若单条返回不用return
			System.out.println("thing...");
			return "do " + x;
			};
		System.out.println(fi.run("写字"));
		
//		new FunInterface(
//			(x) ->   {  //多条语句要用{}括起来，且若有返回值必须用return关键字返回，若单条返回不用return
//				System.out.println("thing...");
//				return "do " + x;
//				}
//			);
		
		Object o = (FunInterface) (x) ->   {  //多条语句要用{}括起来，且若有返回值必须用return关键字返回，若单条返回不用return
			System.out.println("thing...");
			return "do " + x;
			};
			
			
			 Runnable rb = () -> System.out.println("ff");
			 Thread ds = new Thread(rb);
			 
			 Thread ds2 = new Thread( () -> System.out.println("ff"));
			 
		 
			 
		
		
	}
   public void getff() {
	
  }
   
}
