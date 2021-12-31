package composite

import visitor.Visitor
import scala.collection.mutable.ListBuffer

/**
 * Class directory extends the class Composite
 * @param name of the directory
 */
class Directory(val name: String) extends Composite {

  /**
   * Variables used in the class
   */
  var children = new ListBuffer[Composite]()
  var dad: Composite = _

  /**
   * Method adds children to the list
   * @param child to add
   */
  def add(child: Composite): Unit = {
    children += child
    child.setParent(this)
  }

  /**
   * Method removes children from the list
   * @param child to remove
   */
  def remove(child: Composite): Unit = {
    children -= child
  }

 /**
   * Method returns the value of a single item (row) and column label (name).
   * @param row of the info
   * @param label of the info
   * @return info
   */
  override def at(row: Int, label: String): String = {
    var result: String = ""
    for (child <- children) {
      result = result.concat(child.at(row, label) + " ")
    }
    result
  }

  /**
   * Method returns number of labels
   * @return number of labels
   */
  override def columns(): Int = {
    var suma: Int = 0
    for (child <- children) {
      suma = suma + child.columns()
    }
    suma
  }

  /**
   * Method returns number of items (rows)
   * @return number of rows
   */
  override def size(): Int = {
    val result = for (child <- children; c = child.size()) yield c
    result.sum
  }

  /**
   * Method sets the parent
   * @param parent to set
   */
  override def setParent(parent: Composite): Unit = dad = parent: Composite

  /**
   * Method accept for the visitor implementation
   * @param v visitor to accept
   */
  override def accept(v: Visitor): Unit = v.visit(this)
}
