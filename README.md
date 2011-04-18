# Labeled `toString`#
## Overview ##
This project provides several traits you can mix into case classes in order to get `toString` representations that include parameter labels. That means you get <strong><tt>Person(name=John Doe,age=30)</tt></strong> instead of <strong><tt>Person(John Doe,30)</tt></strong>.

## Example ##
Here's a normal case class:

    scala> case class Person(name: String, age: Int)
    defined class Person

    scala> Person("John Doe", 30).toString
    res0: String = Person(John Doe,30)

Here's our labeled case class:

    scala> import com.yuvimasory.tostring._
    import com.yuvimasory.tostring._

    scala> case class Person(name: String, age: Int) extends LabeledToStringDef
    defined class Person

    scala> Person("John Doe", 30).toString
    res0: String = Person(name=John Doe,age=30)

## Choosing a trait ##
The `com.yuvimasory.tostring` package provides three traits: `LabeledToStringDef`, `LabeledToStringVal`, and `LabeledToStringLazyVal`. They override the default case class's `toString` method with a `def`, `val`, and `lazy val`, respectively.

* If you're not sure which to use, start with `LabeledToStringDef`, which works in all cases.
* Consider `LabeledToStringVal` if you know the case class's parameters are either immutable (e.g., primitive types, immutable collections), or have string representations that never change (e.g., arrays).
* Try `LabeledToStringLazyVal` if you meet the criteria for `LabeledToStringVal` and want lazy initialization.
* Both of the `*Val` traits may run slightly faster (since they don't have to recompute `toString` every time it's needed) at the cost of more memory usage.
* You *must* use `LabeledToStringDef` if your case classes are Squeryl tables. If you don't Squeryl will generate bogus SQL.

## Performance ##
The `ToString` traits use Apache Commons Lang under the hood, which uses reflection to find the parameter lables. Surprisingly, there does not seem to be any performance cost for this. In fact, using these traits actually results in code *faster* than the default case class `toString`. Try the tests yourself by running `sbt run`.
