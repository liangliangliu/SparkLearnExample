package com.learn.spark.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import scala.Tuple2;
import scala.collection.JavaConverters;
import scala.collection.Seq;

/**
 * 
 *  joinType:
    "inner", => INNER JOIN
    "outer", "full", "fullouter", "full_outer" => FULL OUTER JOIN
    "leftouter", "left", "left_outer" => LEFT OUTER JOIN
    "rightouter", "right", "right_outer" => RIGHT OUTER JOIN
    "leftsemi", "left_semi" => LEFT SEMI JOIN
    "leftanti", "left_anti" => LSFT ANTI JOIN
    "cross" => CROSS JOIN //笛卡尔积
       
 *  
 * @modificationHistory. 
 * @author liull 2019年12月2日下午6:11:05 TODO
 */
public class joinExample {

	public static void main(String[] args) {
		SparkSession sparkSession = SparkSession.builder()
	    		.appName("joinExample")
	    		.master("local[2]")
	    		.enableHiveSupport()
	    		.getOrCreate();//当前有SparkSession则获取，没有(包括被关闭的情况)则新建
		
		SparkContext sc = sparkSession.sparkContext();
	    JavaSparkContext  jsc = new JavaSparkContext(sc);
	    //SQLContext sqlContext = new SQLContext(jsc);
	     
      JavaRDD<String> rdd1 = jsc.parallelize(Arrays.asList("1|张三","2|李四","3|王五","4|麻子"), 3);
      JavaRDD<String> rdd2 = jsc.parallelize(Arrays.asList("1|18|10","2|20|29","3|32|36","5|52|56"), 3);
      List<StructField>  fields1 = new ArrayList<StructField>();
      List<StructField>  fields2 = new ArrayList<StructField>();
		StructField structField1 = DataTypes.createStructField("id", DataTypes.StringType, true);
		StructField structField2 = DataTypes.createStructField("name", DataTypes.StringType, true);
		StructField structField3 = DataTypes.createStructField("语文", DataTypes.StringType, true);
		StructField structField4 = DataTypes.createStructField("数学", DataTypes.StringType, true);
 
		fields1.add(structField1);
		fields1.add(structField2);
		
		fields2.add(structField1);
		fields2.add(structField3);
		fields2.add(structField4);
		
		StructType schema1 = DataTypes.createStructType(fields1);
		StructType schema2 = DataTypes.createStructType(fields2);
		JavaRDD<Row> data1 = rdd1.map(x ->{
			String[] split = x.split("\\|",-1);
			return RowFactory.create(split[0], split[1]);	
		});
		Dataset<Row> createDataFrame1 = sparkSession.createDataFrame(data1, schema1);
		JavaRDD<Row> data2 = rdd2.map(x ->{
			String[] split = x.split("\\|",-1);
			return RowFactory.create(split[0], split[1],split[2]);	
		});
		Dataset<Row> createDataFrame2 = sparkSession.createDataFrame(data2, schema2);
		createDataFrame1.show();
		createDataFrame2.show();
 //		@param joinType Type of join to perform. Default `inner`. Must be one of:
//			   *                 `inner`, `cross`, `outer`, `full`, `full_outer`, `left`, `left_outer`,
//			   *                 `right`, `right_outer`, `left_semi`, `left_anti`.
		String joinType = "left_anti";
		//这种方式有重复的列出现，可以drop掉指定df的列，可以select指定的列
		//Dataset<Row> join = createDataFrame1.join(createDataFrame2, createDataFrame1.col("id").equalTo(createDataFrame2.col("id")),joinType);
		//join.drop(createDataFrame2.col("id"));
		//join.select(createDataFrame1.col("id"));
		List<String> tmpList = new ArrayList<>();
		tmpList.add("id");
		Seq<String> tmpSeq = JavaConverters.asScalaIteratorConverter(tmpList.iterator()).asScala().toSeq();
		//这种方式不会出现重复的列
		Dataset<Row> join = createDataFrame1.join(createDataFrame2, tmpSeq, joinType);
		
		System.out.println("----------+++++++--------");
		System.out.println("joinType:" + joinType);
		join.printSchema();
		join.sort("id").show();
        jsc.close();
	}
	/*
	 表1：createDataFrame1
+---+----+
| id|name|
+---+----+
|  1|张三|
|  2|李四|
|  3|王五|
|  4|麻子|
+---+----+
表2：createDataFrame2
+---+----+----+
| id|语文|数学|
+---+----+----+
|  1|  18|  10|
|  2|  20|  29|
|  3|  32|  36|
|  5|  52|  56|
+---+----+----+
 
joinType ="inner":
+---+----+---+----+----+
| id|name| id|语文|数学|
+---+----+---+----+----+
|  1|张三|  1|  18|  10|
|  2|李四|  2|  20|  29|
|  3|王五|  3|  32|  36|
+---+----+---+----+----+

joinType = `outer`, `full`, `full_outer`
+---+----+----+----+
| id|name|语文|数学|
+---+----+----+----+
|  1|张三|  18|  10|
|  2|李四|  20|  29|
|  3|王五|  32|  36|
|  4|麻子|null|null|
|  5|null|  52|  56|
+---+----+----+----+

joinType =`left`, `left_outer`
+---+----+----+----+
| id|name|语文|数学|
+---+----+----+----+
|  1|张三|  18|  10|
|  2|李四|  20|  29|
|  3|王五|  32|  36|
|  4|麻子|null|null|
+---+----+----+----+

joinType =`right`, `right_outer`
+---+----+----+----+
| id|name|语文|数学|
+---+----+----+----+
|  1|张三|  18|  10|
|  2|李四|  20|  29|
|  3|王五|  32|  36|
|  5|null|  52|  56|
+---+----+----+----+

joinType =`left_semi`
+---+----+
| id|name|
+---+----+
|  1|张三|
|  2|李四|
|  3|王五|
+---+----+

joinType = `left_anti`
+---+----+
| id|name|
+---+----+
|  4|麻子|
+---+----+
	 
*/

}
