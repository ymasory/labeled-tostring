# Labelled `toString`#
## Overview ##
This project provides several traits you can mix into case classes in order to get `toString` representations that include parameter labels.

## Example ##
Here's a normal case class:

    scala> case class Person(name: String, age: Int)
    defined class Person

    scala> Person("John Doe", 30).toString
    res0: String = Person(John Doe,30)

Here's our labelled case class:

    scala> import com.yuvimasory.tostring._
    import com.yuvimasory.tostring._

    scala> case class Person(name: String, age: Int) extends LabelledToStringDef
    defined class Person

    scala> Person("John Doe", 30).toString
    res0: String = Person(name=John Doe,age=30)

## Choosing a trait ##
The `com.yuvimasory.tostring` package provides three traits: `LabelledToStringDef`, `LabelledToStringVal`, and `LabelledToStringLazyVal`. They override the default case class's `toString` method with a `def`, `val`, and `lazy val`, respectively.

* If you're not sure which to use, start with `LabelledToStringDef`, which works in all cases.
* Consider `LabelledToStringVal` if you know the case class's parameters are either immutable (e.g., primitive types, immutable collections), or have string representations that never change (e.g., arrays).
* Try `LabelledToStringLazyVal` if you want lazy initialization.
* Both of the `val` traits may run slightly faster (since they don't have to recompute `toString` every time it's needed) at the cost of more memory usage.

## Performance ##
The `ToString` traits use Apache Commons Lang under the hood, which uses reflection to find the parameter lables. Surprisingly, there does not seem to be any performance cost for this. In fact, using these traits actually results in code *faster* than the default case class `toString`. Try the tests yourself by running `sbt run`.
