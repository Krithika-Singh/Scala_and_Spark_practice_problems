
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object JoinCSVFiles extends App{
  //Creating a spark session
  val spark = SparkSession.builder()
    .appName("Read CSV File")
    .config("spark.master", "local")
    .getOrCreate()

  //Creating a schema
  val allEmployeesSchema = StructType(Array(
    StructField("EmployeeID",StringType),
    StructField("EmpName",StringType),
    StructField("NoOfWorkingDays",IntegerType)
  ))
  //Read csv file1
  val totalEmployeesDF = spark.read.format("csv")
    .schema(allEmployeesSchema)
    .option("header","true")
    .option("sep",",")
    .load("src/main/resources/all_employees_data.csv")

  //showing a Df of file1
  //totalEmployeesDF.show()

  // Schema of file2
  val employeeWithOfferSchema = StructType(Array(
    StructField("EmployeeID",StringType),
    StructField("CouponType",StringType),
    StructField("CouponAmount",IntegerType)
  ))
  val employeeWithOfferDF = spark.read.format("csv")
    .schema(employeeWithOfferSchema)
    .option("header","true")
    .option("sep",",")
    .load("src/main/resources/special_offer_emp_data.csv")

  //showing a Df of file2
  //employeeWithOfferDF.show()

  //join file
  val specialList = totalEmployeesDF.join(employeeWithOfferDF,totalEmployeesDF.col("EmployeeID") === employeeWithOfferDF.col("EmployeeID"),"inner")
  specialList.show()
}
