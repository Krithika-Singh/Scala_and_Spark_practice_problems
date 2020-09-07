import org.apache.spark.sql.SparkSession
import java.util.Properties

object AddCSVtoMySql extends App{
  val spark = SparkSession.builder()
    .appName("Add CSV values to mysql table")
    .config("spark.master", "local")
    .getOrCreate()

  val dbStr = "jdbc:mysql://localhost:3306/SparkPracticeSession?autoReconnect=true&useSSL=false"

  val properties = new Properties()
  properties.put("user","root")
  properties.put("password","root123")
  Class.forName("com.mysql.jdbc.Driver")

  spark
    .read
    .format("csv")
    .option("inferSchema","true")
    .option("header","true")
    .option("sep",",")
    .load("src/main/resources/all_employees_data.csv")
    .write
    .mode("overwrite")
    .jdbc(dbStr, "employee", properties)

}
