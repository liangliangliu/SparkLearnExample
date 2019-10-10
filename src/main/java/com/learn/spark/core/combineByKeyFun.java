package com.learn.spark.core;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

/**
 * 一、函数定义
 *  def combineByKey[C](  
     createCombiner: V => C,  
     mergeValue: (C, V) => C,  
     mergeCombiners: (C, C) => C,  
     partitioner: Partitioner,  
     mapSideCombine: Boolean = true,  
     serializer: Serializer = null)
     其中：aggregateByKey,groupByKey,reduceByKey等底层都是通过此函数实现
	二、参数说明
	1、createCombiner：V => C ，分区内创建组合函数。这个函数把当前的值作为参数，此时我们可以对其做些附加操作(类型转换)并把它返回 (这一步类似于初始化操作)
	                           (同一分区内，同一个key,只有第一次出现的value才会运行此函数)
	2、mergeValue: (C, V) => C，分区内合并值函数。该函数把元素V合并到之前的元素C(createCombiner)上 (这个操作在每个分区内进行)
	                            （同一分区内，同一个key，非第一次出现value运行此函数，其中C为上次的结果，V为本次值）
	3、mergeCombiners: (C, C) => C，多分区合并组合器函数。该函数把2个元素C合并 (这个操作在不同分区间进行)。
	4、partitioner：自定义分区数，默认为HashPartitioner
	5、mapSideCombine：是否在map端进行Combine操作，默认为true
	 *  
 * @modificationHistory. 
 * @author liull 2019年10月10日下午5:49:50 TODO
 */
public class combineByKeyFun {

	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder()
	    		.appName("ReadFileFun")
	    		.master("local[2]")
	    		//.config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
	    		//.config("spark.kryo.registrationRequired", true)
	    		//.config("spark.kryo.classesToRegister",Book.class.getName())
	    		//.enableHiveSupport()
	    		.getOrCreate();//当前有SparkSession则获取，没有(包括被关闭的情况)则新建
		
		SparkContext sc = spark.sparkContext();
	    JavaSparkContext  jsc = new JavaSparkContext(sc);
         JavaRDD<Tuple2<Integer, Integer>> tupRdd = jsc.parallelize(Arrays.asList(
        		     new Tuple2<Integer, Integer>(1, 1), new Tuple2<Integer, Integer>(1, 2)
                , new Tuple2<Integer, Integer>(2, 3), new Tuple2<Integer, Integer>(2, 4)
                , new Tuple2<Integer, Integer>(3, 5), new Tuple2<Integer, Integer>(3, 6)
                , new Tuple2<Integer, Integer>(4, 7), new Tuple2<Integer, Integer>(4, 8)
                , new Tuple2<Integer, Integer>(5, 9), new Tuple2<Integer, Integer>(5, 10)
        ), 2);
        JavaPairRDD<Integer, Integer> pairRDD = JavaPairRDD.fromJavaRDD(tupRdd);
        List<Tuple2<Integer, String>> collect = pairRDD
                .combineByKey(x ->{
        			 return x + "##0";
        		}, (c,v) -> {
        			return c + "," + v + "##0";
        		}, (c1,c2) -> {
        			return c1 + "," + c2;
        		}).collect();
                
         System.err.println(collect);
         jsc.close();
          
         /*
          * [(3,5##0,6##0), (4,7##0,8##0), (1,1##0,2##0), (5,9##0,10##0), (2,3##0,4##0)]
          */
	}

}
