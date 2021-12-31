package recursive

import java.util
import scala.jdk.CollectionConverters._

/**
 * Class that that filters the elements in a list based on a given condition
 * and applies an operation to those that fulfill it.
 */
class listFilterMap {

  /**
   * Method implemented with stack recursion
   * @param condition to apply
   * @param operation to apply
   * @param collection to apply
   * @tparam A generic type
   * @return info filtered
   */
  def stack[A](condition: A => Boolean, operation: A => A, collection: java.util.List[A]): List[A] = {
    collection.asScala.toList match {
      case Nil => Nil
      case x :: xs => if (condition(x)) operation(x) :: stack(condition, operation, xs.asJava) else x :: stack(condition, operation, xs.asJava)
    }
  }

  /**
   * Method implemented with tail recursion
   * @param condition to apply
   * @param operation to apply
   * @param collection to apply
   * @tparam A generic type
   * @return info filtered
   */
  def tail[A](condition: A => Boolean, operation: A => A, collection: java.util.List[A], result: List[A]): List[A] = {
    collection.asScala.toList match {
      case Nil => result.reverse
      case x :: xs => tail(condition, operation, xs.asJava, if (condition(x)) operation(x) :: result else x :: result)
    }
  }

  def tail[A]: (A => Boolean, A => A, util.List[A]) => List[A] = tail(_: A => Boolean, _: A => A, _: java.util.List[A], Nil)

}
