package chapter05

object scala05_rich_wrapper extends App {

  /**
   * 富包装类
   *
   * 相比前面几节讲到的，还可以对Scala的基础类型调用更多的方法。表5.4给出了—些例子。
   * 就Scala 3而言，这些方法是通过隐式转换实现的,而隐式转换作为一个过时的技巧，最终将被替换为扩展方法。
   * 关于扩展方法的技巧，会在第22章做详细介绍。
   * 你目前需要知道的是，本章提到的每个基础类型,都有一个对应的“富包装类”，提供了额外的方法。
   */

  println(0 max 5) //5

  println(0 min 5) //0

  println(-2.7.abs) //2.7

  println(-2.7.round) //-3
  //是否无穷大
  println(1.5.isInfinity) //false

  println((1.0/0).isInfinity) //true

  println(4 to 6) //Range 4 to 6
  //首字母大写
  println("bob".capitalize) //Bob

  println("robert".drop(2)) //bert
}
