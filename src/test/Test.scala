package test

import composite.{DataFrameComposite, Directory}
import factory._
import recursive._
import visitor._
import scala.jdk.CollectionConverters._

/**
 * Test shows how all the implemented features work
 */
object Test {
  def main(args: Array[String]): Unit = {
    println("Reading and creating files...")
    val file1: DataFrameComposite = new DataFrameComposite(new CSVFile().readFile("cities.csv"))
    val file2: DataFrameComposite = new DataFrameComposite(new JSONFile().readFile("cities.json"))
    val fileSales: DataFrameComposite = new DataFrameComposite(new CSVFile().readFile("sales.csv"))

    println("Creating directory...")
    val dir1: Directory = new Directory("dir1")
    dir1.add(file1)
    dir1.add(file2)
    dir1.add(file1)
    println("The rows of the first file: "+file1.size().toString)
    println("The columns of the first file: "+file1.columns().toString)
    println("The information on the second row of the label LatD of al the files in the directory: "+dir1.at(2, "LatD"))

    val v: FilterVisitor = new FilterVisitor("LatD", x => x.toInt > 40)
    file1.accept(v)
    println("\n\nFirst file filtrated by LatD greater than 40: \n" + v.elements)
    val vc: CounterVisitor = new CounterVisitor()
    dir1.accept(vc)
    println("\nThere is " + vc.dirs + " directories and " + vc.files+" files")

    val lfm = new listFilterMap()
    println("\n\nUsing recursive methods:\n")

    val list = new java.util.LinkedList[Float]()
    for (elem: String <- fileSales.data.get("Unit Price").asScala.toList) {
      list.add(elem.toFloat)
    }
    println("\nTail recursion -> Unit Price greater than 100\n" + lfm.tail[Float]((x: Float) => x >= 100, (x: Float) => x.round.toFloat, list))
    println("\nStack recursion -> Unit Price greater than 100\n" + lfm.stack[Float]((x: Float) => x >= 100, (x: Float) => x.round.toFloat, list))
    println("\nWithout changes:\n" + fileSales.data.get("Region"))
    println("\nTail recursion -> Replacing North for South on column Region\n" + lfm.tail[String]((x: String) => x.contains("North"), (x: String) => x.replace("North", "South"), fileSales.data.get("Region")))
    println("\nStack recursion -> Replacing Europe for Africa on column Region\n"+ lfm.stack[String]((x: String) => x.contains("Europe"), (x: String) => x.replace("Europe", "Africa"), fileSales.data.get("Region")))
  }
}
