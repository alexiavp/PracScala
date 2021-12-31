package visitor

import composite.Directory
import dataFrame.DataFrame

abstract class Visitor {

  def visit(dir: Directory): Unit = {
    for (child <- dir.children) {
      child.accept(this)
    }
  }

  def visit(d: DataFrame): Unit
}
