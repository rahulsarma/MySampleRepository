package com.rahul.sparktutorial;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

/**
 * Demonstrates reading of a JSON file using Spark SQL.
 * 
 * @author Rahul
 */
public class SparkSQLPatientQueries {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create Spark Session to create connection to Spark
		final SparkSession sparkSession = SparkSession
																		.builder()
																		.appName("Spark SQL Demo")
																		.master("local[5]")
																		.config("spark.sql.warehouse.dir", "file:///C:/Users/rasarma/Desktop/Cloudera%20VM/shared_folder/workspace/spark-basics/spark-warehouse")
																		.getOrCreate();
		
		// Load JSON file data into DataFrame using SparkSession
		final Dataset<Row> csv = sparkSession
																			.read()
																			.format("com.databricks.spark.csv")
																			.option("header","false")
																			.option("delimiter","|")
																			//.option("multiLine", "true")
																			.schema(buildSchema())
																			.csv("src/main/resources/ENCTYPE.csv");
		//print records
		//csv.show();
		// Print Schema to see column names, types and other metadata
		csv.printSchema();
		
				
		// Create a view on DataFrame and execute the query on created view using SparkSession
		System.out.println("SQL Query:");
		csv.createOrReplaceTempView("enctype");
		sparkSession.sql("SELECT * FROM enctype where code_name ='SNOMED-CT' ").show();
		
		
		//create table:
		//sparkSession.sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) USING hive");
	}
	
	/**
	 * Build and return a schema to use for the sample data.
	 * 
	 * enctype_id	BIGINT
enccat_id	BIGINT
code_id	BIGINT
code 	VARCHAR(100)
code_name	VARCHAR(100)
is_active	BOOLEAN/String
name	VARCHAR(1000)

	 */
	private static StructType buildSchema() {
	    StructType schema = new StructType(
	        new StructField[] {
	            DataTypes.createStructField("enctype_id", DataTypes.IntegerType, true),
	            DataTypes.createStructField("enccat_id", DataTypes.IntegerType, true),
	            DataTypes.createStructField("code_id", DataTypes.IntegerType, true),
	            DataTypes.createStructField("code", DataTypes.StringType, true),
	            DataTypes.createStructField("code_name", DataTypes.StringType, true),
	           // DataTypes.createStructField("preferences", 
	                //DataTypes.createMapType(DataTypes.StringType, DataTypes.StringType, true), true),
	            DataTypes.createStructField("is_active", DataTypes.StringType, true),
	            DataTypes.createStructField("name",DataTypes.StringType,  true) });
	    return (schema);
	}
}