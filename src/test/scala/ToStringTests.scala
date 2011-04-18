package com.yuvimasory.tostring

import org.scalatest.FunSuite

import ToString._

class ToStringTests extends FunSuite {

  val nameField = "name"
  val name = "John Doe"
  val ageField = "age"
  val age = 30

  test("plane old case class") {
    expect("Person1(" + name + "," + age + ")") {
      Person1(name, age).toString
    }
  }

  test("simple use of LabelledToString") {
    val expected =
      "Person2(" + nameField + "=" + name + "," + ageField + "=" + age + ")"
    expect(expected){Person2(name, age).toString}
  }

  test("zero params") {
    val expected = "Person3()"
    expect(expected){Person3().toString}
  }

  test("MutableLabelledToString checks args at each toString call") {pending}

  test("null args") {
    pending
    assert(Person1(null, age).toString === Person2(null, age).toString)
  }

  test("boolean args") {
    expect("BooleanClass1(true)")(BooleanClass1(true).toString)
    expect("BooleanClass2(bool=true)")(BooleanClass2(true).toString)
  }

  test("char args") {
    expect("CharClass1(c)")(CharClass1('c').toString)
    expect("CharClass2(char=c)")(CharClass2('c').toString)
  }

  test("byte args") {
    expect("ByteClass1(-5)")(ByteClass1(-5).toString)
    expect("ByteClass2(byte=-5)")(ByteClass2(-5).toString)
  }

  test("short args") {
    expect("ShortClass1(-9)")(ShortClass1(-9).toString)
    expect("ShortClass2(short=-9)")(ShortClass2(-9).toString)
  }

  test("int args") {
    expect("IntClass1(-9)")(IntClass1(-9).toString)
    expect("IntClass2(int=-9)")(IntClass2(-9).toString)
  }

  test("long args") {
    expect("LongClass1(-9)")(LongClass1(-9).toString)
    expect("LongClass2(long=-9)")(LongClass2(-9).toString)
  }

  test("float args") {
    expect("FloatClass1(-0.77)")(FloatClass1(-0.77f).toString)
    expect("FloatClass2(float=-0.77)")(FloatClass2(-0.77f).toString)
  }

  test("double args") {
    expect("DoubleClass1(-0.77)")(DoubleClass1(-0.77).toString)
    expect("DoubleClass2(double=-0.77)")(DoubleClass2(-0.77).toString)
  }

  test("array args") {
    pending
    val arr = Array(1,2)
    def testAt(obj: AnyRef) = {
      println(obj.toString)
      assert(obj.toString contains "@")
    }

    testAt(new ArrayClass1(arr))
    testAt(ArrayClass2(arr))
  }
  test("object args") {pending}
}

case class FloatClass1(float: Float)
case class FloatClass2(float: Float) extends LabelledToString
case class DoubleClass1(double: Double)
case class DoubleClass2(double: Double) extends LabelledToString
case class ByteClass1(byte: Byte)
case class ByteClass2(byte: Byte) extends LabelledToString
case class CharClass1(char: Char)
case class CharClass2(char: Char) extends LabelledToString
case class ShortClass1(short: Short)
case class ShortClass2(short: Short) extends LabelledToString
case class IntClass1(int: Int)
case class IntClass2(int: Int) extends LabelledToString
case class LongClass1(long: Long)
case class LongClass2(long: Long) extends LabelledToString
case class BooleanClass1(bool: Boolean)
case class BooleanClass2(bool: Boolean) extends LabelledToString
case class ArrayClass1(array: Array[Int])
case class ArrayClass2(array: Array[Int]) extends LabelledToString

case class Person1(name: String, age: Int)
case class Person2(name: String, age: Int) extends LabelledToString
case class Person3() extends LabelledToString
