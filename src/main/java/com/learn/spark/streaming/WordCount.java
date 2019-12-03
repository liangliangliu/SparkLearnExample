package com.learn.spark.streaming;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.spark.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.*;
import scala.Tuple2;
import org.apache.hadoop.io.compress.GzipCodec;
public class WordCount {
	
	public static void main(String[] args) throws InterruptedException {
	 
 	// Create a local StreamingContext with two working thread and batch interval of 1 second
	SparkConf conf = new SparkConf()
			.setMaster("local[2]")
			.setAppName("NetworkWordCount");
	JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));
	// Create a DStream that will connect to hostname:port, like localhost:9999
	JavaReceiverInputDStream<String> lines = jssc.socketTextStream("localhost", 9999);
	// Split each line into words
	JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(x.split(" ")).iterator());
	JavaPairDStream<String, Integer> pairs = words.mapToPair(s -> new Tuple2<>(s, 1));
	JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey((i1, i2) -> i1 + i2);
	// Print the first ten elements of each RDD generated in this DStream to the console
	Map<String,Integer> result = new HashMap<String,Integer>();
	System.out.println("----1---");
	wordCounts.print();
	System.out.println("----2---");
	wordCounts.foreachRDD(x ->{
		Map<String,Integer> map = x.collectAsMap();
		map.forEach((k,v) ->{
			Integer ff = result.get(k);
			if(ff == null) {
				result.put(k, v);
			}else {
				result.put(k, v + ff);
			}
		});
		System.out.println(result);
	});
	System.out.println("---3----");
	System.out.println(result);
	jssc.start();          // Start the computation
	jssc.awaitTermination();   // Wait for the computation to terminate
  }
}
