package com.learn.spark.streaming;

import org.apache.spark.streaming.kafka010.CanCommitOffsets;
import org.apache.spark.streaming.kafka010.HasOffsetRanges;
import org.apache.spark.streaming.kafka010.OffsetRange;

public class SparkStreamingWithKafka {

	public static void main(String[] args) {
		
		//获取保存offset信息
//		OffsetRange[] offsetRanges = ((HasOffsetRanges) rdd.rdd()).offsetRanges();
//		((CanCommitOffsets) stream.inputDStream()).commitAsync(offsetRanges);

	}

}
