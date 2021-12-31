package main

import composite.Directory
import dataFrame.DataFrame
import factory._
import recursive._
import visitor._

import scala.jdk.CollectionConverters._

object Tests {
  def main(args: Array[String]): Unit = {
    val file1: DataFrame = new CSVFile().readFile("C:\\Users\\jllat\\IdeaProjects\\PracPOOScala\\src\\cities.csv")
    val file2: DataFrame = new JSONFile().readFile("C:\\Users\\jllat\\IdeaProjects\\PracPOOScala\\src\\cities.json")
    val fileSales: DataFrame = new CSVFile().readFile("C:\\Users\\jllat\\IdeaProjects\\PracPOOScala\\src\\sales.csv")


    val dir1: Directory = new Directory("dir1")
    dir1.add(file1)
    dir1.add(file2)
    dir1.add(file1)
    println(file1.size().toString)
    println(file1.columns().toString)
    printf(dir1.at(2, "LatD"))

    val v: FilterVisitor = new FilterVisitor("LatD", x => x.toInt > 40)
    file1.accept(v)
    printf("\n" + v.elements)
    val vc: CounterVisitor = new CounterVisitor()
    dir1.accept(vc)
    printf("\nN Dir:" + vc.dirs + " N Files:" + vc.files)

    val lfm = new listFilterMap()
    printf("\n" + fileSales.data.get("Unit Price"))

    val list = new java.util.LinkedList[Float]()
    for (elem: String <- fileSales.data.get("Unit Price").asScala.toList) {
      list.add(elem.toFloat)
    }
    printf("\n" + lfm.tail[Float]((x: Float) => x >= 100, (x: Float) => x.round.toFloat, list))
    printf("\n" + lfm.stack[Float]((x: Float) => x >= 100, (x: Float) => x.round.toFloat, list))
    printf("\n" + fileSales.data.get("Region"))
    printf("\n" + lfm.tail[String]((x: String) => x.contains("North"), (x: String) => x.replace("North", "Nord"), fileSales.data.get("Region")))
    printf("\n" + lfm.stack[String]((x: String) => x.contains("North"), (x: String) => x.replace("North", "Nord"), fileSales.data.get("Region")))

  }

}
