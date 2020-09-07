
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}


object CopyHdfsFileToLocalFolder extends App{
  val spark = SparkSession.builder()
    .appName("Read HDFS File")
    .config("spark.master", "local")
    .getOrCreate()

  val df:DataFrame = spark.read.text("hdfs://localhost:9000/sample/EmployeeDetails.txt")
  df.show()

  df.write
    .format("csv")
    .option("header","true")
    .mode(SaveMode.Overwrite)
    .save("file:///C:/Users/Home/Downloads/HadoopDemo/EmployeeTable.csv")

}
