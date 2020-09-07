import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object ReadJsonFile extends App{
  //Creating a spark session
  val spark = SparkSession.builder()
    .appName("Read JSON File")
    .config("spark.master", "local")
    .getOrCreate()

  //Creating a schema
  val personsSchema = StructType(Array(
    StructField("eventName",StringType),
    StructField("processName",StringType),
    StructField("hostName",StringType),
    StructField("filePath",StringType),
    StructField("dataFileName",StringType)
  ))

  /*val personsDF = spark.read.format("json")
    .schema(personsSchema)
    .load("src/main/resources/persons_data.json")*/

  val personsDF = spark.read
    .option("multiLine", true)
    .option("mode", "PERMISSIVE")
    .json("src/main/resources/persons_data.json")

  //showing a Df of file1
  personsDF.show()

}
