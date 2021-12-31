package visitor

import composite.Directory
import dataFrame.DataFrame

/**
 * Abstract class for visitor
 */
abstract class Visitor {
  /**
   * Method visit for directories
   * @param dir directory visited
   */
  def visit(dir: Directory): Unit = {
    for (child <- dir.children) {
      child.accept(this)
    }
  }

  /**
   * Method visit for files
   * @param d file visited
   */
  def visit(d: DataFrame): Unit
}
