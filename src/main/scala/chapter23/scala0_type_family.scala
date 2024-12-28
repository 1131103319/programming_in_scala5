package chapter23

object scala0_type_family extends App {

  /**
   * 第23章 类型族
   *
   * 假设你需要编写一个实现对某些类型适用而对其他类型不适用的行为的函数，而Scala提供了几个选项。
   * 其中，一个选项是定义重载的方法;
   * 第二个选项是要求所有传入这个函数的实例的类都混入某个特定的特质;
   *
   * 而第三个（同时也是更灵活的）选项是 定义一个类型族，然后以按照传入的类型能够找到该类型族的上下文参数实例为前提来编写你的函数。
   */

  /**
   * 本章将会比较和对比这些不同的方式，然后深入介绍类型族。
   * 我们将介绍用于类型族的上下文界定语法并从标准类库中给出若干个类型族的示例，
   * 包括数值字面量、跨界相等性、隐式转换和主方法等。作为最后的收尾，本章还会给出一个用类型族实现JSON序列化的例子。
   */
}
