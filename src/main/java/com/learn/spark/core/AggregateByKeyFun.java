package com.learn.spark.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;
/**
 * 第一个参数是， 每个key的初始值
   第二个是个函数， Seq Function， 这个函数就是用来先对同一分区内的数据按照key分别进行定义进行函数定义的操作
                （对于同一个key,第一次出现的value会和”第一个参数“一起进入此函数，往后出现的value会和上一次的结果一起进入函数）
   第三个是个函数， Combiner Function， 对经过 Seq Function 处理过的不同分区数据按照key分别进行进行函数定义的操作
                 （如果一个key只在一个分区，不运行此函数）
 
 * @description.  
 *  
 * @modificationHistory. 
 * @author liull 2019年10月10日下午5:00:27 TODO
 */
public class AggregateByKeyFun {
	public static void main(String[] args) {
	SparkSession spark = SparkSession.builder()
    		.appName("ReadFileFun")
    		.master("local[2]")
    		//.config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    		//.config("spark.kryo.registrationRequired", true)
    		//.config("spark.kryo.classesToRegister",Book.class.getName())
    		 
    		//.enableHiveSupport()
    		.getOrCreate();
	
	SparkContext sc = spark.sparkContext();
    JavaSparkContext  jsc = new JavaSparkContext(sc);
    
    List<Tuple2<String, Integer>> abk = Arrays.asList(
            new Tuple2<String, Integer>("class1", 1),
            new Tuple2<String, Integer>("class1", 2),
            new Tuple2<String, Integer>("class1", 4),
            new Tuple2<String, Integer>("class2", 3),
            new Tuple2<String, Integer>("class2", 5),
            new Tuple2<String, Integer>("class2", 6),
            new Tuple2<String, Integer>("class2", 8),
            new Tuple2<String, Integer>("class2", 7));
    JavaPairRDD<String, Integer> abkrdd = jsc.parallelizePairs(abk, 2);
    abkrdd.mapPartitionsWithIndex(
            new Function2<Integer, Iterator<Tuple2<String, Integer>>, Iterator<String>>() {
                @Override
                public Iterator<String> call(Integer s,
                        Iterator<Tuple2<String, Integer>> v)
                        throws Exception {
                    List<String> li = new ArrayList<>();
                    while (v.hasNext()) {
                        li.add("data：" + v.next() + " in " + (s + 1) + " " + " partition");
                    }
                    return li.iterator();
                }
            }, true).foreach(m -> System.out.println(m));
    JavaPairRDD<String, Integer> abkrdd2 = abkrdd.aggregateByKey(0,
            new Function2<Integer, Integer, Integer>() {
                @Override
                public Integer call(Integer s, Integer v) throws Exception {
                    System.out.println("seq:" + s + "," + v);
                    return Math.max(s, v);
                }
            }, new Function2<Integer, Integer, Integer>() {
                @Override
                public Integer call(Integer s, Integer v) throws Exception {
                    System.out.println("com:" + s + "," + v);
                    return s + v;
                }
            });
     
    abkrdd2.foreach(new VoidFunction<Tuple2<String, Integer>>() {
        @Override
        public void call(Tuple2<String, Integer> s) throws Exception {
            System.out.println("c:" + s._1 + ",v:" + s._2);
        }
    });
     /*result:
      * c:class1,v:4
        c:class2,v:11
      */
    
	}
}
