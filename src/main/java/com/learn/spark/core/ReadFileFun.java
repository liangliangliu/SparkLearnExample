package com.learn.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;

//import scala.Function2;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;

 
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.spark.rdd.NewHadoopRDD;

public class ReadFileFun {

	public static void main(String[] args) throws Exception {
		String path = "";
	 	
		SparkConf ss = new SparkConf();
		//ss.registerKryoClasses(Array(classOf[Book],classOf[Book]));
		Class[] css = new Class[1];
		//css[0] = String.class;
		css[0] = scala.collection.mutable.WrappedArray.ofRef[].class;
		ss.registerKryoClasses(css);
        SparkSession spark = SparkSession.builder()
        		.appName("ReadFileFun")
        		.master("local[2]")
        		//.config(ss)
        		//.config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        		//.config("spark.kryo.registrationRequired", true)
        		//.config("spark.kryo.classesToRegister",Book.class.getName())
        		 
        		//.enableHiveSupport()
        		.getOrCreate();
        System.out.println(Book.class.getName());
        SparkContext sc = spark.sparkContext();
        JavaSparkContext  jsc = new JavaSparkContext(sc);
        List<String> list = Arrays.asList("66","ww","op","dshjr","dsetvaas","dsfefvva","135sdfgghhhjrrr");
        JavaRDD<Tuple2<Integer, Integer>> tupRdd = jsc.parallelize(Arrays.asList(new Tuple2<Integer, Integer>(1, 1), new Tuple2<Integer, Integer>(1, 2)
                , new Tuple2<Integer, Integer>(2, 3), new Tuple2<Integer, Integer>(2, 4)
                , new Tuple2<Integer, Integer>(3, 5), new Tuple2<Integer, Integer>(3, 6)
                , new Tuple2<Integer, Integer>(4, 7), new Tuple2<Integer, Integer>(4, 8)
                , new Tuple2<Integer, Integer>(5, 9), new Tuple2<Integer, Integer>(5, 10)
        ), 3);
        JavaPairRDD<Integer, Integer> pairRDD = JavaPairRDD.fromJavaRDD(tupRdd);
//        JavaRDD<String> rdd = pairRDD.map(x ->{
//        	return x._1 + "|" + x._2;
//        });
//        rdd.cache();
//        System.out.println(rdd.collect());

        //jsc.newAPIHadoopFile(arg0, arg1, arg2, arg3, arg4)
        
        List<Tuple2<Integer, String>> collect = pairRDD
        .combineByKey(x ->{
			 return x + "##0";
		}, (c,v) -> {
			return c + "," + v + "##0";
		}, (c1,c2) -> {
			return c1 + "," + c2;
		}).collect();
        
        System.err.println(collect);
        
        JavaRDD<Book> test = jsc.parallelize(list).map(x -> {
        	  Book book = new Book();
        	  book.setBookId(x);
        	  book.setContent("66asdlmakluu5mll333aa-;l;kjasdjfjej");
        	  return book;
        });
        test.persist(StorageLevel.MEMORY_AND_DISK_SER());
        test.collect().forEach(x -> System.out.println(x.toString() + "iii"));
        Thread.sleep(10000);
        System.out.println(test.collect());
        
//        JavaPairRDD<LongWritable, Text> newAPIHadoopFile2 = jsc.newAPIHadoopFile(path, TextInputFormat.class,LongWritable.class, Text.class, sc.hadoopConfiguration());
//        ((NewHadoopRDD)newAPIHadoopFile2).map
        
        
        ClassTag<Tuple2> tag = scala.reflect.ClassTag$.MODULE$.apply(Tuple2.class);
        RDD<Tuple2<LongWritable, Text>> newAPIHadoopFile = sc.newAPIHadoopFile(path, TextInputFormat.class,LongWritable.class, Text.class, sc.hadoopConfiguration());
//       ((JavaNewHadoopRDD)newAPIHadoopFile).mapPartitionsWithInputSplit(new Function2<InputSplit, Iterator<Tuple2<LongWritable, Text>>, Iterator<Tuple2<String,String>> >(){
//
//		@Override
//		public Iterator<Tuple2<String, String>> call(InputSplit v1, Iterator<Tuple2<LongWritable, Text>> v2)
//				throws Exception {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//			 
//    	},true);
        //scala.reflect.ClassTag$.MODULE$.apply(Integer.class);
//        ClassTag<String> tag = scala.reflect.ClassTag$.MODULE$.apply(String.class);
        
        
         
        
//         ((NewHadoopRDD)newAPIHadoopFile).mapPartitionsWithInputSplit(new scala.Function2<InputSplit,scala.collection.Iterator<Tuple2<LongWritable,Text>>,scala.collection.Iterator<String>>() {
//
//			@Override
//			public Iterator<String> apply(InputSplit arg0, Iterator<Tuple2<LongWritable, Text>> arg1) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public double apply$mcDDD$sp(double arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public double apply$mcDDI$sp(double arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public double apply$mcDDJ$sp(double arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public double apply$mcDID$sp(int arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public double apply$mcDII$sp(int arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public double apply$mcDIJ$sp(int arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public double apply$mcDJD$sp(long arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public double apply$mcDJI$sp(long arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public double apply$mcDJJ$sp(long arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFDD$sp(double arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFDI$sp(double arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFDJ$sp(double arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFID$sp(int arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFII$sp(int arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFIJ$sp(int arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFJD$sp(long arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFJI$sp(long arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public float apply$mcFJJ$sp(long arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIDD$sp(double arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIDI$sp(double arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIDJ$sp(double arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIID$sp(int arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIII$sp(int arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIIJ$sp(int arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIJD$sp(long arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIJI$sp(long arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public int apply$mcIJJ$sp(long arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJDD$sp(double arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJDI$sp(double arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJDJ$sp(double arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJID$sp(int arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJII$sp(int arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJIJ$sp(int arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJJD$sp(long arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJJI$sp(long arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public long apply$mcJJJ$sp(long arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public void apply$mcVDD$sp(double arg0, double arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void apply$mcVDI$sp(double arg0, int arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void apply$mcVDJ$sp(double arg0, long arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void apply$mcVID$sp(int arg0, double arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void apply$mcVII$sp(int arg0, int arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void apply$mcVIJ$sp(int arg0, long arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void apply$mcVJD$sp(long arg0, double arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void apply$mcVJI$sp(long arg0, int arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void apply$mcVJJ$sp(long arg0, long arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public boolean apply$mcZDD$sp(double arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean apply$mcZDI$sp(double arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean apply$mcZDJ$sp(double arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean apply$mcZID$sp(int arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean apply$mcZII$sp(int arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean apply$mcZIJ$sp(int arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean apply$mcZJD$sp(long arg0, double arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean apply$mcZJI$sp(long arg0, int arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean apply$mcZJJ$sp(long arg0, long arg1) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public Function1<InputSplit, Function1<Iterator<Tuple2<LongWritable, Text>>, Iterator<String>>> curried() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public Function1<Tuple2<InputSplit, Iterator<Tuple2<LongWritable, Text>>>, Iterator<String>> tupled() {
//				// TODO Auto-generated method stub
//				return null;
//			}		 
//  }, false, tag);
//
	}

}
