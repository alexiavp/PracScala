package composite

import visitor.Visitor

trait Composite {
  def at(row: Int, label: String): String

  def columns(): Int

  def size(): Int

  def setParent(parent: Composite): Unit

  def accept(v: Visitor): Unit
}
