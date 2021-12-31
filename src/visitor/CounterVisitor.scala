package visitor

import composite.Directory
import dataFrame.DataFrame

/**
 * Class with CounterVisitor implemented
 */
class CounterVisitor extends Visitor {
  /**
   * Variables used in this class
   */
  var files: Int = 0
  var dirs: Int = 0

  /**
   * Method visit for the directories visited
   * @param dir directory visited
   */
  override def visit(dir: Directory): Unit = {
    dirs += 1
    super.visit(dir)
  }

  /**
   * Method visit for the files visited
   * @param d  file visited
   */
  override def visit(d: DataFrame): Unit = files += 1
}
