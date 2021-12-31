package visitor

import dataFrame.DataFrame

import scala.jdk.CollectionConverters._
import scala.collection.mutable.ListBuffer

class FilterVisitor(val label: String, val condition: String => Boolean) extends Visitor {

  var elements = new ListBuffer[String]()

  override def visit(d: DataFrame): Unit = {
    elements.appendAll(for (elem <- d.data.get(label).asScala.toList; if condition(elem)) yield elem)
    //elements.appendAll(d.data.get(label) filter condition)
  }
}
