package com.learn.spark.core;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.json.JSONObject;

import scala.collection.Seq;

public class SparkSession2 {
 
	
	public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("Spark2").master("local[2]").enableHiveSupport().getOrCreate();
        spark.read().schema("").load();
        StructField ff =  new StructField();
       
        Encoder<Book> personEncoder = Encoders.bean(Book.class);
        Encoder<Integer> int1 = Encoders.INT();
        spark.createDataset(Collections.singletonList(new Book()), personEncoder) ;
 
        
        JavaRDD<String> input = spark.sparkContext().textFile("/data/json/part-r-00000.txt",
                1).toJavaRDD().map(x -> x.split("____")[1]);
        
        JavaRDD<Book> books = input.map(new Function<String, Book>() {
            
			private static final long serialVersionUID = 5219260272236368662L;

			public Book call(String s) throws Exception {
                JSONObject jsons = new JSONObject(s);
                 
                Book book = new Book();

                book.setBookId(jsons.get("bookId").toString());
                book.setContent(jsons.get("content").toString());
                book.setContentStartPos(jsons.get("contentStartPos").toString());
                 
                return book;
            }
        });
        
        Dataset<Row> bookdf = spark.createDataFrame(books,Book.class);
        bookdf.select(new Column(""));
        bookdf.createOrReplaceTempView("book");
        bookdf.show();
        Dataset<Row> bIddf = spark.sql("select bookid from book");
        bIddf.show();
        spark.close();
    }
	/**
	 * rdd转换成dataformate(dataset<Row>) 方法一
	 * @author liull
	 * @creationDate. 2018年11月2日 下午3:28:23 
	 * @description.  
	 * 
	 * @param spark
	 */
	public void rddToDF1(SparkSession spark) {
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
		JavaRDD<String> textRDD = sc.textFile("hdfs://user/liull/test");
		List<StructField>  fields = new ArrayList<StructField>();
		StructField structField1 = DataTypes.createStructField("name", DataTypes.StringType, true);
		StructField structField2 = DataTypes.createStructField("age", DataTypes.IntegerType, true);
//		DataType dd = structField1.dataType();
//		dd.
		fields.add(structField1);
		fields.add(structField2);
		StructType schema = DataTypes.createStructType(fields);
		 
		JavaRDD<Row> rowRDD = textRDD.map(new Function<String, Row>(){
			public Row call(String v) {
				String[] arr = v.split(",",-1);
				return RowFactory.create(arr[0],Integer.valueOf(arr[1]));	
			}
		}) ;
		rowRDD.map(new Function<Row, String>() {

			@Override
			public String call(Row arg0) throws Exception {
				// TODO Auto-generated method stub
				//arg0.fieldIndex(arg0)
				return null;
			}
		});
		Dataset<Row> df =  spark.createDataFrame(rowRDD, schema);
		
	}
	/**
	 * rdd转换成dataformate(dataset<Row>) 方法二
	 * @author liull
	 * @creationDate. 2018年11月2日 下午3:57:46 
	 * @description.  
	 * 
	 * @param spark
	 */
	public void rddToDF2(SparkSession spark) {
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
		JavaRDD<String> textRDD = sc.textFile("hdfs://user/liull/test");
		JavaRDD<Book> bookRDD = textRDD.map(new Function<String, Book>(){
			public Book call(String v) {
				String[] arr = v.split(",",-1);
				Book book = new Book();
				book.setBookId(arr[0]);
				book.setContent(arr[1]);
				return book;
			}
		});
		Dataset<Row> df = spark.createDataFrame(bookRDD, Book.class);
	}
	/**
	 * rdd转化成dataset
	 * @author liull
	 * @creationDate. 2018年11月2日 下午4:32:54 
	 * @description.  
	 * 
	 * @param spark
	 */
	public void rddToDS(SparkSession spark) {
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
		JavaRDD<String> textRDD = sc.textFile("hdfs://user/liull/test");
		JavaRDD<Book> bookRDD = textRDD.map(new Function<String, Book>(){
			public Book call(String v) {
				String[] arr = v.split(",",-1);
				Book book = new Book();
				book.setBookId(arr[0]);
				book.setContent(arr[1]);
				return book;
			}
		});
		
		Encoder<Book> encoder = Encoders.bean(Book.class);
		Dataset<Book> dataset = spark.createDataset(bookRDD.rdd(), encoder);
	}

}
