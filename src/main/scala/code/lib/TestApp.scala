package code.lib

import scala.reflect.ClassTag

object TestApp {
  trait Action

  class AAA extends Action
  class BBB extends Action

  val list = List(new AAA, new BBB, new AAA)

  //def find[T <: Action](implicit m: scala.reflect.Manifest[T]) = list.filter { x: Action => m.erasure.isInstance(x) }.asInstanceOf[List[T]]
  //def find2[T <: Action: ClassTag] = list.filter { implicitly[ClassTag[T]].runtimeClass.isInstance }
  def find3(b: Action => Boolean) = {
    list.filter(b(_))
  }

  def main(args: Array[String]): Unit = {
    val ls = find3((b: Action) => {
      b.isInstanceOf[BBB]
    })
    println(ls)
  }

}