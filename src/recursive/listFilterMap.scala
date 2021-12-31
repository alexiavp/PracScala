package recursive

import java.util
import scala.jdk.CollectionConverters._

class listFilterMap {


  def stack[A](condition: A => Boolean, operation: A => A, collection: java.util.List[A]): List[A] = {
    collection.asScala.toList match {
      case Nil => Nil
      case x :: xs => if (condition(x)) operation(x) :: stack(condition, operation, xs.asJava) else x :: stack(condition, operation, xs.asJava)
    }
  }

  def tail[A](condition: A => Boolean, operation: A => A, collection: java.util.List[A], result: List[A]): List[A] = {
    collection.asScala.toList match {
      case Nil => result.reverse
      case x :: xs => tail(condition, operation, xs.asJava, if (condition(x)) operation(x) :: result else x :: result)
    }
  }

  def tail[A]: (A => Boolean, A => A, util.List[A]) => List[A] = tail(_: A => Boolean, _: A => A, _: java.util.List[A], Nil)

}
