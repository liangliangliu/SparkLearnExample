package com.learn.spark;

public class ConstantsDef {
	public static final String ENV_APP_STARTUP_TIMESTAMP 	    				= "conf.app.startup.timestamp";
	public static final String ENV_LOCAL 	    				= "env.local";
	public static final String ENV_LOG_PRINT 	    			= "env.log.print";

	//HBase Setting
	public static final String HBASE_SOURCE_ZK_QUORUM 	    = "hbase.source.zk.quorum";
	public static final String HBASE_SOURCE_NAMESERVER_ADDR = "hbase.source.nameserver.address";
	public static final String HBASE_TARGET_ZK_QUORUM 	 	= "hbase.target.zk.quorum";

	
	public static final String HBASE_SOURCE_TABLE 	 		= "hbase.source.table";
	public static final String HBASE_TARGET_TABLE 	 		= "hbase.target.table";
	
	public static final String HBASE_SOURCE_TABLE_START_ROW 	 	= "hbase.source.table.start.row";
	public static final String HBASE_SOURCE_TABLE_STOP_ROW 	 		= "hbase.source.table.stop.row";
	
	public static final String HBASE_TARGET_TABLE_INDEX 	 		= "hbase.target.table.index";
	public static final String HBASE_WRITE_BATCHSIZE 	 		= "hbase.write.batchsize";
	public static final String HBASE_READ_BATCHSIZE 	 		= "hbase.read.batchsize";
	
	//kafka Setting
	
	public static final String KAFKA_NumPartitions 		 	= "kafka.numPartitions";
	public static final String KAFKA_ZK_QUORUM 		 	= "kafka.zk.quorum";
	public static final String KAFKA_CONSUMER_GROUP 	= "kafka.consumer.group";
	public static final String KAFKA_TOPICS 		 	= "kafka.topics";
	public static final String KAFKA_THREADNUM 		 	= "kafka.threadnum";
	public static final String KAFKA_ZK_CONNECTION_TIMEOUT_MS 		 	= "kafka.zk.connection.timeout.ms";
	public static final String KAFKA_FETCH_SIZE 		 			= "kafka.fetch.size";
	public static final String KAFKA_FETCH_MAX_BYTES_SIZE 		 	= "fetch.message.max.bytes";
	
	//Spark Setting
	public static  final String SPARK_STREAMING_DURATION 	 = "spark.streaming.duration";
	public static  final String SPARK_CHECKPOINT_DIR 	 	 = "spark.checkpoint.dir";
	
	public static  final String SPARK_HDFS_INPUT 	 			 = "spark.hdfs.input";
	public static  final String SPARK_HDFS_OUTPUT 	 			 = "spark.hdfs.output";
	public static  final String SPARK_PARAMS_JSON 	 			 = "spark.params.json";
	public static  final String SPARK_HDFS_OUTPUT_BATCHSIZE 	 = "spark.hdfs.output.batchsize";
	
	//email subscriber
	public static  final String EMAIL_SERVER_AUTH_USER 	 = "email.server.authenticator.user";
	public static  final String EMAIL_SERVER_AUTH_PWD 	 = "email.server.authenticator.pwd";
	public static  final String EMAIL_FROM_ADDRESS 	 	 = "email.from.address";
	public static  final String EMAIL_SERVER_HOST 	 	 = "email.server.host";
	public static  final String EMAIL_SERVER_PORT 		 = "email.server.port";
	public static  final String EMAIL_SUBSCRIBER 	 	 = "email.subscriber";
	
	//mapreduce setting
	public static  final String MR_HDFS_INPUT 	 			 = "mapreduce.hdfs.input";
	public static  final String MR_HDFS_OUTPUT 	 			 = "mapreduce.hdfs.output";
	public static  final String MR_HBASE_INPUT                ="mapreduce.hbase.input";
	public static  final String MR_JOB_RESOURCE 	 		 = "mapreduce.resource";
	public static  final String MR_JOB_REDUCER_NUM 	 		 = "mapreduce.reducer.num";
	
	public static  final String MR_JOB_CUSTOM_CONF_PREFIX	 		 = "conf.";
	
	//log  setting
	public static  final String LOG_DATE 	 		 		 			 = "conf.log.date";
	public static  final String LOG_DATE_START 	 		 		 			 = "conf.log.date_start";
	public static  final String LOG_DATE_END 	 		 		 			 = "conf.log.date_end";
	public static  final String LOG_DAYS 	 		 		 			 = "conf.days";
	public static  final String LOG_FILTER_DATE 	 		 		 	 = "conf.log.filter.date";
	public static  final String LOG_FULL_DATEFORMAT 	 		 		 = "yyyy-MM-dd HH:mm:ss";
	public static  final String LOG_YYYYMMDD_DATEFORMAT 	 		 		 = "yyyyMMdd";
	
	//log len
	public static  final int LOG_FULL_DATEFORMAT_LEN 	 		 		 = LOG_FULL_DATEFORMAT.length();
	public static  final int LOG_MD5_LEN 	 		 		 			 = "4dc89b975c717aab4bd4ebdc01726e83".length();
	
	//persona
	
	public static  final String PERSONA_CONF 	 		 		 	 			 = "conf.persona";
	public static  final String PERSONA_CALENDAR 	 		 		 			 = "conf.persona.calendar";
	public static  final String PERSONA_GEOHASH_CELL_MAX_SIZE 	 		 		 = "conf.persona.geohash.cell.maxsize";
	public static  final String PERSONA_GEOHASH_CELL_MIN_SIZE 	 		 		 = "conf.persona.geohash.cell.minsize";
	public static  final String PERSONA_LBS_HAUNT_HOUR_COUNT 	 		 		 = "conf.persona.lbs.haunt.hour.count";
	public static  final String PERSONA_LBS_HAUNT_CELL_SIZE 	 		 		 = "conf.persona.lbs.haunt.cell.size";
	
	
	public static final String INSTALL_FLAG 	 		= "i";
	public static final String UNINSTALL_FLAG 	 		= "un";
	public static final String UPGRADE_FLAG 	 		= "up";
	public static final String ROWKEY_SPLIT_STR 	 	= ",";
	
	public final static int     GEOHASH_PARTITION_LEN 		= 4;
	public final static String  TAG_LBS_TYPE11 		= "type11";
	public final static String  TAG_REFION_POI      ="lbs_region_poi";
	public final static String  TAG_LBS_POI         ="lbs_poi";
	public final static String  TAG_ADDR_POI         ="addr_poi";
	public final static String  TAG_RESIDENT_REDION         ="resident_did_region";
	public final static String  TAG_LBS_TYPE11_BS 	= "type11_bs";
	public final static String  TAG_ADDR_CITY 	= "addr_city";
	public final static String  TAG_LBS_REPORT 	= "bi-report";
	public final static String  TAG_LBS_NOWIFI 		= "nowifi";
	public final static String  TAG_LBS_WIFILOC 	= "wifilocation";
	public final static String  TAG_LBS_BSLOC 		= "bs_location";
	public final static String  TAG_ACTIVE_CID_LASTLOGIN = "dw_user_lastlogin";
	public final static String  TAG_SPLIT_CHAR 		= "\t";
	
	public final static String LOG_TO_PROCESS_DAYTIME = "LOG_TO_PROCESS_DAYTIME";
	 
	public final static String  TAG_COUNTER_GROUPID 	= "mr_counter_group_id";
	public final static String  TAG_COUNTER_GROUPID_OTHER 	= "mr_counter_group_id_other";
			
}
