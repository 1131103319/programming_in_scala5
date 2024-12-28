package chapter10

object scala01 extends App {

  /**
   * 第10章 组合和继承
   *
   * 第6章介绍了Scala面向对象的一些基础概念。本章将接着第6章，更详细地介绍Scala对于面向对象编程的支持。
   *
   * 我们将对比类之间的两个最基本的关系:组合和继承。
   * 组合的意思是一个类可以包含对另一个类的引用，并利用这个被引用的类来帮助它完成任务。
   * 而继承是 超类/子类 的关系。
   *
   * 除此之外，我们还会探讨抽象类、无参方法、类的扩展、
   * 重写方法和字段、参数化字段、调用超类的构造方法、
   * 多态和动态绑定、不可重写(final)的成员和类， 以及工厂对象和方法。
   */

  /**
   * 10.1  一个二维的布局类库
   *
   * 我们将创建一个用于构建和渲染二维布局元素的类库，以此作为本章的示例。
   * 每个元素表示一个用文本填充的长方形。为方便起见，类库将提供名称为“elem”的工厂方法，根据传入的数据构造新的元素。
   * 例如，可以用下面这个签名的工厂方法创建一个包含字符串的布局元素:
   */
  // elem(s:String):Element

  /**
   * 我们用一个名称为Element的类型对元素建模。
   * 可以对一个元素调用above或beside，传入另一个元素，以获取一个将两个元素结合在一起的新元素。
   * 例如，下面这个表达式将创建一个由两列组成的更大的元素，每一列的高度都为2:
   */

  /**
   * 布局元素很好地展示了这样一个系统:在这个系统中，对象可以通过组合操作符的帮助由简单的部件构建出来。
   * 本章将定义那些可以根据向量、线和矩形构造出元素对象的类。
   * 这些基础的元素对象就是我们说的简单的部件。我们还会定义组合操作符above和beside。
   * 这样的组合操作符通常也被称作组合子(combinator)，因为它们会将某个领域内的元素组合成新的元素。
   */

  /**
   * 用组合子来思考通常是一个设计类库的好办法。
   * 例如，对于某个特定应用领域中的对象，它有哪些基本的构造方式，这样的思考是很有意义的。
   * 简单的对象如何构造出更有趣的对象?如何将组合子有机地结合在一起?
   * 最通用的组合有哪些?它们是否满足某种有趣的法则?如果针对这些问题你都有很好的答案，那么你的类库设计就走在正轨上。
   */
  
}