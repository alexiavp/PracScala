package composite

import visitor.Visitor

/**
 * Composite trait
 */
trait Composite {
  /**
   * Method returns the value of a single item (row) and column label (name).
   * @param row of the info
   * @param label of the info
   * @return info
   */
  def at(row: Int, label: String): String

  /**
   * Method returns number of labels
   * @return number of labels
   */
  def columns(): Int

  /**
   * Method returns number of items (rows)
   * @return number of rows
   */
  def size(): Int

  /**
   * Method sets the parent
   * @param parent to set
   */
  def setParent(parent: Composite): Unit

  /**
   * Method accept for the visitor implementation
   * @param v visitor to accept
   */
  def accept(v: Visitor): Unit
}
