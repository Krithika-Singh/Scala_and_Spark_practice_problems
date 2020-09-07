import java.io.File

object FindLastSevenDaysModifiedFile extends App{
  var allFiles = List[File]()
    val folder= new File("/Users/HOME/OneDrive/Desktop/ExampleFilesForMajor")
    val timeConstraint = System.currentTimeMillis - (7 * 24 * 60 * 60 * 1000)
    if (folder.exists() && folder.isDirectory) {
      allFiles = folder.listFiles.filter(_.getName.endsWith(".txt")).filter(_.lastModified()<timeConstraint).toList
      println(allFiles)
    }
    else {
      println("No such files exist")
    }
}
