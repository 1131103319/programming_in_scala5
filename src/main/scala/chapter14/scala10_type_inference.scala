package chapter14

object scala10_type_inference extends App {

  val abcde = "abcde"

  /**
   * 14.10 理解Scala的类型推断算法
   *
   * 之前用到的sortWith方法和msort函数的区别在于它们可接收的比较函数语法。(归并排序)
   */
  //msort((x:Char, y:Char)=> x > y)
  abcde.sortWith(_ > _)

  /**
   * 这两个表达式是等效的，不过前者采用的函数字面量更长，用到了带名称的参数和显式的类型声明。后者采用了更精简的写法（_>_)，其中带名称的参数由下画线替换了。
   * 当然，我们也可以在sortWth方法调用中使用前一种较长的写法来给出比较函数。
   *
   * @然而，这个较短的版本并不适用于msort函数。
   *
   * 要弄清楚为什么会这样，需要知道Scala的类型推断算法的一些细节。
   * Scala的类型推断是基于程序流（flow based)的。
   * 对于方法调用m(args)，类型推断算法首先检查m的类型是否已知。
   * 如果m的类型已知，则这个类型信息会被用于推断入参的预期类型。
   * 例如，在abcde.sortWith(_>_)中,abcde的类型为List[Char]。因此，类型推断算法知道sortWth是一个接收类型为(Char, Char) => Boolean的入参且生成一个类型为List[Char]的结果的方法。
   * 由于该方法入参的参数类型是已知的，因此并不需要显式地写出来。基于类型推断算法所了解的关于sortWith方法的信息，可以推导出(_>_)应该被展开成((x:Char, y: Char) => x > y)，其中，x和y是任意没有被用过的新名称。
   *
   * 现在我们来看第二个case，即msort(_>_)(abcde)。
   * msort函数的类型是一个经过柯里化的、多态的方法类型。它接收一个类型为(T,T) => Boolean的入参，生成一个从List[T]到List[T]的函数，其中，T是某个当前未知的类型。msort函数需要先用一个类型参数实例化以后才能被应用到它的入参上。
   * （译者注:这里指的是参数多态，不是面向对象编程里常见的子类型多态。）
   *
   * 由于msort函数的实例类型暂时未知，因此类型推断算法不能用这个信息来推断它的首个入参的类型。
   * 对于这种情况，类型推断算法会改变策略:它会先检查方法入参来决定方法的实例类型。然而，当它对(_>_)这个简写的函数字面量做类型检查时，由于我们没有提供任何关于用下画线表示的函数参数类型的信息，因此类型检查是失败的。
   * 解决这个问题的一种方式是给msort函数传入一个显式的类型参数，如:
   */
  //msort[Char](_ > _)(abcde)

  /**
   * 由于msort函数的实例类型现在是已知的了，因此类型推断算法可以用它来推断入参的类型。另一个可能的解决方案是重写msort函数，让它的两个参数交换位置:
   */
  //msortSwapped(abcde)(_ > _)

  /**
   * 怎么做到的呢?类型推断算法使用了首个参数abcde的已知类型来判定msortSwapped的类型参数。一旦msortSwapped的类型已知，就能被用于推断第二个入参(_>_)的类型。
   *
   * @一般来说，当类型推断算法需要推断一个多态方法的类型参数时，它会考虑第一个参数列表里的所有入参的类型，但也就到此为止了。
   * 由于msortSwapped是一个柯里化的方法，它有两个参数列表，但第二个入参（即函数值)并不会被用来判定方法的类型参数。
   *
   * @这样的类型推断机制引导出如下的类库设计原则:当我们设计一个接收某些非函数的入参和一个函数入参时，可以将函数入参单独放在最后一个参数列表中。
   * @这样一来，方法的实例类型可以通过那些非函数入参推断出来，而这个类型又能被继续用于对函数入参做类型检查。
   * @这样做的净收益是方法的使用者需要给出的类型信息更少，因此在编写函数字面量时可以更精简。
   */

  /**
   * 接下来再看看折叠这个更复杂的操作。为什么我们需要像309页的flattenRight方法的方法体内的那段表达式那样显式地给出类型参数呢?
   */
  def flattenRight[T](xss: List[List[T]]) =
    xss.foldRight(List[T]())(_ ::: _)

  /**
   * 右折叠操作的类型以两个类型变量的形式呈现出多态。比如，下面这个表达式:
   *
   * xs.foldRight(z)(op)
   */
  /**
   * xs的类型一定是某个任意类型A的列表，如xs: List[A]。起始值z可以是某个不一样的类型B。
   * 这样一来，操作op一定接收类型分别为A和B的两个入参，并返回类型为B的结果，
   * 即op: (A,B)=> B。由于z的类型与列表xs的类型不相关，因此类型推断算法没有任何关于z的上下文信息。
   *
   * 这个折叠操作中的起始值 z 是一个空列表List()，当没有任何其他额外信息的情况下，它的类型被推断为List[Nothing]。
   * 因此，类型推断算法会推断出本次折叠操作的类型B为List[Nothing]。这样一来，折叠操作中的(_:::_)预期应该满足如下类型:
   *
   * (List[T], List[Nothing]) = > List[Nothing]
   *
   * @这的确是本次操作的一个可能的类型，但并不是一个十分有用的版本。它表达的意思是这个操作永远接收一个空列表作为第二个入参，同时永远生成一个空列表作为结果。
   *
   * 换句话说，这里的类型推断算法过早地判定了List()的类型，它应该等看到操作op的类型以后再做决定。
   * @因此，在用于判定方法类型的柯里化的方法调用中，只考虑第一个参数列表的这个（本可以很有用的）规则，是核心问题所在。
   * 另一方面，即使我们可以放宽这个规则，类型推断算法也依然无法推算出操作op的类型，因为它的参数类型没有给出。
   * 因此，一个编译器无法自行决断的情况出现了，只能由程序员加上显式的类型注解来（帮编译器）解决。
   *
   * 这个例子暴露出局部的、基于程序流的Scala类型推断机制的局限性。
   * 在函数式编程语言ML或Haskell中使用的全局的Hindley-Milner风格的类型推断中，并没有这些限制。
   * 不过，Scala的局部类型推断对于面向对象的子类型处理比Hindley-Milner风格优雅得多。
   * 幸运的是，这些局限性只在某些边界case中出现，且通常很容易通过显式添加类型注解来解决。
   *
   * 当我们对多态方法相关的错误提示感到困感时，添加类型注解也是一个有用的调试技巧。如果不确定某个特定的类型错误是什么引起的，
   * 则尽管添加你认为正确的类型参数或其他类型注解就好。这样你应该很快就能看到真正的问题所在。
   */


  /**
   * 14.1 结语
   *
   * 我们见识了很多处理列表的方法。例如，最基本的方法有head和tail，初阶方法有reverse，高阶方法有map，以及List对象中的工具方法。
   * 在这个过程中，我们也了解了Scala的类型推断算法的原理。
   *
   * 列表是Scala程序中的“真正干活儿”的工具，所以知道如何使用它是有好处的。
   * 正因为如此，本章花费大量篇幅深入介绍了列表的用法。不过，列表只是Scala支持的集合类型中的一种。
   * 下一章范围更宽，但相对来说知识稍微浅一些，会向你展示如何使用各种Scala集合类型。
   */



}
