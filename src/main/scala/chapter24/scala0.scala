package chapter24

object scala0 {
  /**
   * 第24章 深入集合类
   *
   * Scala自带了一个优雅而强大的集合类库。虽然这些集合API初看上去没什么，但是它们对编程风格的影响可谓巨大。
   * 这就好比你把整个集合而不是集合中的元素当作构建单元来组织上层逻辑。这种新的编程风格需要我们慢慢适应，
   * 不过幸好Scala集合有一些不错的属性，可以帮助我们。Scala集合易用、精简、安全、快速，而且通行。
   */

  /**
   * @易用:
   * 一组由20～50个方法组成的词汇已经足以用少量操作解决大部分集合问题。
   * 我们并不需要理解复杂的循环或递归结构。持久化的集合加上无副作用的操作意味着我们不需要担心会意外地用新数据污染了已有的集合。
   * 迭代器和集合更新之间的相互影响也没有了。
   */

  /**
   * @精简:
   * 可以用一个词来完成之前需要一个或多个循环才能完成的操作。我们可以用轻量的语法表达函数式操作，
   * 并且毫不费力地组合这些操作，就像处理的是某种定制的代数规则一样。
   */

  /**
   * @安全:
   * 这一点需要在实际使用过程中感受。Scala集合的静态类型和函数式本质意味着我们可能会犯的绝大部分错误都能在编译期被发现。
   * 原因在于:
   *      (1）集合操作本身很常用，因此测则得很充分;
   *      (2)集合操作将输入和输出显式地做成函数参数和结果;
   *      (3)这些被显式给出的输入和输出会被静态类型检查。最起码，大部分误用都会呈现为类型错误。长达几百行的程序在首次编写后便能执行的情况并不少见。
   */

  /**
   * @快速:
   * 类库对集合操作做了调整和优化。通常来说，使用集合都很高效。你可能可以通过仔细调整的数据结构和操作做得好一些，
   * 但也完全可能因为过程中某些不够优化的实现而做得更差。不仅如此，Scala的集合对于多核并行执行也做了适配。
   * 并行集合与串行集合一样支持相同的操作，因此不需要学习新的操作，也不需要编写新的代码。你可以简单地通过调用par方法将一个串行集合转换成一个并行集合。
   */

  /**
   * @通行:
   * 集合对任何类型都可以提供相同的操作，只要这个操作对该类型而言是讲得通的。
   * 因此，我们可以用很少的一组操作来实现很多功能。举例来说，字符串从概念上讲是一个由字符组成的序列，
   * 因此，在Scala集合中，字符串支持所有的序列操作。数组也是同理的。
   */

  /**
   * 本章将从用户视角深入介绍Scala集合类的API。
   * 第15章已经快速介绍过集合类库，本章将带你了解更多细节，囊括使用Scala集合需要知道的基础知识。
   * 在《Scala高级编程》中，我们会对类库的架构和扩展性方面进行讲解，供需要实现新的集合类型的人士参考。
   */
}