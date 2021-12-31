package composite

import visitor.Visitor

import scala.collection.mutable.ListBuffer

class Directory(val name: String) extends Composite {
  var children = new ListBuffer[Composite]()
  var dad: Composite = _

  def add(child: Composite): Unit = {
    children += child
    child.setParent(this)
  }

  def remove(child: Composite): Unit = {
    children -= child
  }


  override def at(row: Int, label: String): String = {
    var result: String = ""
    for (child <- children) {
      result = result.concat(child.at(row, label) + " ")
    }
    result
  }

  override def columns(): Int = {
    var suma: Int = 0
    for (child <- children) {
      suma = suma + child.columns()
    }
    suma
  }

  override def size(): Int = {
    val result = for (child <- children; c = child.size()) yield c
    result.foldLeft(0)(_ + _)
  }

  override def setParent(parent: Composite): Unit = dad = parent: Composite


  override def accept(v: Visitor): Unit = v.visit(this)
}
