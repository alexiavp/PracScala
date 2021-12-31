package visitor

import composite.Directory
import dataFrame.DataFrame

class CounterVisitor extends Visitor {
  var files: Int = 0
  var dirs: Int = 0

  override def visit(dir: Directory): Unit = {
    dirs += 1
    super.visit(dir)
  }

  override def visit(d: DataFrame): Unit = files += 1
}
