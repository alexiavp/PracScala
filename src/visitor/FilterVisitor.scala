package visitor

import dataFrame.DataFrame
import scala.jdk.CollectionConverters._
import scala.collection.mutable.ListBuffer

/**
 * Class with FilterVisitor implemented
 * @param label to filter
 * @param condition to help filter
 */
class FilterVisitor(val label: String, val condition: String => Boolean) extends Visitor {

  /**
   * Variable used in the class, elements once filtered
   */
  var elements = new ListBuffer[String]()

  /**
   * Method collects all elements (rows) that fulfill the condition.
   * @param d information
   */
  override def visit(d: DataFrame): Unit = {
    elements.appendAll(for (elem <- d.data.get(label).asScala.toList; if condition(elem)) yield elem)
    //elements.appendAll(d.data.get(label) filter condition)
  }
}
