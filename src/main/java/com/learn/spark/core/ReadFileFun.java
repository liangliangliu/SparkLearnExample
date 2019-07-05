package com.learn.spark.core;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;

 
import scala.Tuple2;
//import scala.collection.Iterator;
import scala.reflect.ClassTag;

 
import java.util.Set;
import java.util.Iterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.spark.rdd.NewHadoopRDD;

public class ReadFileFun {

	public static void main(String[] args) {
		String path = "";
        SparkSession spark = SparkSession.builder().appName("ReadFileFun").master("local[2]").enableHiveSupport().getOrCreate();
        SparkContext sc = spark.sparkContext();
        RDD<Tuple2<LongWritable, Text>> newAPIHadoopFile = sc.newAPIHadoopFile(path, TextInputFormat.class,LongWritable.class, Text.class, sc.hadoopConfiguration());
        //scala.reflect.ClassTag$.MODULE$.apply(Integer.class);
        ClassTag<String> tag = scala.reflect.ClassTag$.MODULE$.apply(String.class);
        
        
         
        
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
