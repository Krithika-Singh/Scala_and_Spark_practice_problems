
import java.io.File
import scala.collection.mutable

class StoringFileInHashMap(dir: String){

  def findTimeAndName(dir : String):mutable.Map[String, Long]= {
    val path = new File(dir)
    var filesMap = scala.collection.mutable.Map[String, Long]()
    if (path.exists && path.isDirectory) {
      for (x <- path.listFiles()) {
        filesMap += (x.getName -> x.lastModified())
      }
      return filesMap
    }
    return filesMap
  }
}

object StoreFileInHashMapObject extends App{
  val folderPath = "/Users/HOME/OneDrive/Desktop/ExampleFilesForMajor"
  var filesMap = mutable.Map[String, Long]()
  val obj = new StoringFileInHashMap(folderPath)
  filesMap =obj.findTimeAndName(folderPath)
  for((name,time) <- filesMap) {
    println(s"Name of File = $name    Last modified day = $time")
  }

}