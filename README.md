# Labeled ToString #
## Overview ##
The [Labeled ToString project](https://github.com/ymasory/labeled-tostring) provides several traits you can mix into case classes in order to get `toString` representations that include parameter labels. That means you get <strong><tt>Person(name=John Doe,age=30)</tt></strong> instead of <strong><tt>Person(John Doe,30)</tt></strong>.

## Example ##
Here's a normal case class:

    case class Person(name: String, age: Int)
    Person("John Doe", 30).toString
    //result is "Person(John Doe,30)"

Here's our labeled case class:

    import com.yuvimasory.tostring._
    case class Person(name: String, age: Int) extends LabeledToStringDef
    Person("John Doe", 30).toString
    //result is  "Person(name=John Doe,age=30)"

## Installation ##
Add this dependency to your sbt `Project.scala` file:

    val labeledToString = "com.yuvimasory.tostring" %% "labeled-tostring" % "0.5.0"

## Choosing a trait ##
The `com.yuvimasory.tostring` package provides three traits: `LabeledToStringDef`, `LabeledToStringVal`, and `LabeledToStringLazyVal`. They override the default case class's `toString` method with a `def`, `val`, and `lazy val`, respectively.

* If you're not sure which to use, start with `LabeledToStringDef`, which works in all cases.
* Consider `LabeledToStringVal` if you know the case class's parameters are either immutable (e.g., primitive types, immutable collections), or have string representations that never change (e.g., arrays).
* Try `LabeledToStringLazyVal` if you meet the criteria for `LabeledToStringVal` and want lazy initialization.
* Both of the `*Val` traits may run slightly faster (since they don't have to recompute `toString` every time it's needed) at the cost of more memory usage.
* You *must* use `LabeledToStringDef` if your case classes are Squeryl tables. If you don't Squeryl will generate bogus SQL.

Some [benchmarks](https://github.com/ymasory/labeled-tostring-benchmarks) on object creation:

    [info]                          benchmark      ns linear runtime
    [info]              CreateCaseClassPerson    7.52 =
    [info]     CreateLabeledToStringValPerson 1423.98 ==============================
    [info] CreateLabeledToStringLazyValPerson    9.69 =
    [info]     CreateLabeledToStringDefPerson    7.43 =

Some benchmarks on `toString` calls after object creation:
Coming Soon.

## Warning ##
* These traits produce unexpected strings in the REPL due to the way the REPL wraps code and mangles names.
* These traits do not work if you add bodies to your case classes (i.e., additional methods or fields).
