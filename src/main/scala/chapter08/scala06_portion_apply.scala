package chapter08

object scala06_portion_apply extends App {

  /**
   * @部分应用的函数
   *
   * 在Scala中，当你调用某个函数并传入需要的参数时，实际上是将这个函数应用到(apply to)这些入参上。例如，下面这个函数:
   */

  def sum(a: Int, b: Int, c: Int) = a + b + c

  //可以像这样将函数sum应用到入参1、2、3上:
  println(sum(1, 2, 3))

  /**
   * 当你用占位符语法给方法传参时，实际上是在编写一个
   *
   * @部分应用的函数 (partially applied function)。
   *
   *          部分应用的函数是一个表达式，在这个表达式中，
   *          并不给出函数需要的所有参数，而是给出部分参数或完全不给。
   *          举例来说，要基于sum创建一个部分应用的函数，如果你不想给出3个参数中的任何一个，则可以在sum之后放一个下画线。这将返回一个函数，并且该函数可以被存放到变量中。参考下面的例子:
   */
  val a: (Int, Int, Int) => Int = sum(_, _, _) // a的类型为(Int,Int,Int)=>Int
  println(a(1, 2, 3))


  /**
   * 有了这些代码，Scala编译器将根据部分应用的函数sum(_,_,_)实例化一个接收3个整数参数的函数值，
   * 并将指向这个新的函数值的引用赋值给变量a。当你对3个参数应用这个新的函数值时，它将转而调用sum，传入这3个参数:
   */

  /**
   * 背后发生的事情是:名称为a的变量指向一个函数值对象。
   * 这个函数值是一个由Scala编译器自动从sum(_,_,_)这个部分应用的函数表达式中生成的类的实例。 (生成的类扩展自Function3这个特质，该特质声明了一个三参数的apply方法。)
   * 由编译器生成的这个类有一个接收3个参数的apply方法。
   * 生成的类的apply方法之所以接收3个参数，是因为表达式sum(_,_,_)缺失的参数个数为3。
   * Scala编译器将表达式a(1,2,3)翻译成对函数值的apply方法的调用，
   * 并传入这3个参数——1、2和3。因此，a(1,2,3)可以被看作如下代码的简写形式:
   */

  /**
   * 这个由Scala编译器从表达式sum(_,_,_)自动生成的类中定义的apply方法 只是简单地将3个缺失的参数转发给sum，然后返回结果。
   * 在本例中，apply方法调用了sum(1,2,3)，并返回sum的返回值，即6。
   */

  /**
   * 我们还可以从另一个角度来看待这类用下画线表示整个参数列表的表达式，即这是一种将def变成函数值的方式。
   * 举例来说，如果你有一个局部函数，如sum(a:Int, b: Int,c: Int): Int，则可以将它“包"在一个函数值里，
   * 这个函数值拥有相同的参数列表和结果类型。当你应用这个函数值到某些参数上时，它转而应用sum到同样的参数上，并返回结果。
   *
   * 虽然你不能将方法或嵌套的函数直接赋值给某个变量，或者作为参数传递给另一个函数，但是可以将方法或嵌套函数打包在一个函数值里 来完成这样的操作。
   */

  /**
   * 至此，我们已经知道sum(_,_,_)是一个不折不扣的部分应用的函数，
   *
   * 可能你仍然会感到困惑，为什么我们会这样称呼它。
   * 部分应用的函数之所以叫作部分应用的函数，是因为你并没有把那个函数应用到所有入参上。
   * 拿sum(_,_,_)来说，你没有将其应用到任何入参上。
   * 不过，你完全可以通过给出“一些”必填的参数来表达一个部分应用的函数。参考下面的例子:
   */

  val b = sum(1, _, 3) //b的类型为Int =>Int

  /**
   * 在本例中，提供了第一个和最后一个参数给sum，但没有给出第二个参数。
   * @由于只有一个参数缺失，Scala编译器将生成一个新的函数类，这个类的apply方法接收一个参数。
   * 当我们用那个参数来调用这个新的函数类时，这个新的函数类的apply方法将调用sum，依次传入1、当前函数的入参 和3。参考下面的例子:
   *
   * 第一行里的b.apply调用了sum(1,2,3)，而第二行里的b.apply调用了sum(1,5,3)。
   */
  println(b(2))
  println(b(5))

  /**
   * 如果你想要的部分应用的函数表达式并不给出任何参数，如sum(_,_,_)，则可以在需要这样一个函数的地方更加精简地表示，甚至整个参数列表都不用写。例如:
   */
  val c = sum //c的类型为(Int,Int,Int)=>Int

  /**
   * 由于sum是方法名，而不是指向某个值的变量，
   *
   * 编译器会创建一个与包装了该方法调用的方法相同签名的函数值，这个过程被称为"eta延展”( eta expansion)。
   * 换句话说，sum是sum(_,_,_)的更精简写法。如下是一个调用c指向函数的例子:
   */

  println(c(10,20,30))


}
