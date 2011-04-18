package com.yuvimasory.tostring

import org.scalatest.FunSuite

import ToString._

class ToStringTests extends FunSuite {

  implicit def string2SameString(str : String) = new SameString(str)
  class SameString(str: String) {
    def sameString(any: Any) = expect(str)(any.toString)
  }

  test("zero params") {
    "ZeroParam1()" sameString ZeroParam1()
    "ZeroParam2()" sameString ZeroParam2()
  }

  test("null args") {
    "Null1(null)" sameString Null1(null)
    "Null2(n=null)" sameString Null2(null)
  }

  test("boolean args") {
    "Boolean1(true)" sameString Boolean1(true)
    "Boolean2(bool=true)" sameString Boolean2(true)
  }

  test("char args") {
    "Char1(c)" sameString Char1('c')
    "Char2(char=c)" sameString Char2('c')
  }

  test("byte args") {
    "Byte1(-5)" sameString Byte1(-5)
    "Byte2(byte=-5)" sameString Byte2(-5)
  }

  test("short args") {
    "Short1(-9)" sameString Short1(-9)
    "Short2(short=-9)" sameString Short2(-9)
  }

  test("int args") {
    "Int1(-9)" sameString Int1(-9)
    "Int2(int=-9)" sameString Int2(-9)
  }

  test("long args") {
    "Long1(-9)" sameString Long1(-9)
     "Long2(long=-9)" sameString Long2(-9)
  }

  test("float args") {
    "Float1(-0.77)" sameString Float1(-0.77f)
    "Float2(float=-0.77)" sameString Float2(-0.77f)
  }

  test("double args") {
    "Double1(-0.77)" sameString Double1(-0.77)
    "Double2(double=-0.77)" sameString Double2(-0.77)
  }

  test("array args") {
    pending
    val arr = Array(1,2)
    def testAt(obj: AnyRef) = {
      println(obj)
      assert(obj.toString contains "@")
    }

    testAt(new Array1(arr))
    testAt(Array2(arr))
  }

  test("object args") {
    val lst = List(1,2)
    "Object1(List(1, 2))" sameString Object1(lst)
    "Object2(obj=List(1, 2))" sameString Object2(lst)

    val obj = new AnyRef
    val objStr = obj
    ("Object1(" + obj + ")") sameString Object1(obj)
    ("Object2(obj=" + obj + ")") sameString Object2(obj)
  }

  test("var parameters") {
    val c1 = VarParameter1(0)
    "VarParameter1(0)" sameString c1
    c1.int = 1
    "VarParameter1(1)" sameString c1

    val c2 = VarParameter2(2)
    "VarParameter2(int=2)" sameString c2
    c2.int = 3
    "VarParameter2(int=3)" sameString c2
  }

  test("val parameters") {
    "ValParameter1(0)" sameString ValParameter1(0)
    "ValParameter2(int=0)" sameString ValParameter2(0)
  }

  test("mixed val and var parameters") {pending}
  test("varargs") {pending}
  test("multiple arguments") {pending}
}

case class ZeroParam1()
case class ZeroParam2() extends LabelledToString
case class Object1(obj: AnyRef)
case class Object2(obj: AnyRef) extends LabelledToString
case class VarParameter1(var int: Int)
case class VarParameter2(var int: Int) extends MutableLabelledToString
case class ValParameter1(val int: Int)
case class ValParameter2(val int: Int) extends LabelledToString
case class Float1(float: Float)
case class Float2(float: Float) extends LabelledToString
case class Double1(double: Double)
case class Double2(double: Double) extends LabelledToString
case class Byte1(byte: Byte)
case class Byte2(byte: Byte) extends LabelledToString
case class Char1(char: Char)
case class Char2(char: Char) extends LabelledToString
case class Short1(short: Short)
case class Short2(short: Short) extends LabelledToString
case class Int1(int: Int)
case class Int2(int: Int) extends LabelledToString
case class Long1(long: Long)
case class Long2(long: Long) extends LabelledToString
case class Boolean1(bool: Boolean)
case class Boolean2(bool: Boolean) extends LabelledToString
case class Array1(array: Array[Int])
case class Array2(array: Array[Int]) extends LabelledToString
case class Null1(n: Null)
case class Null2(n: Null) extends LabelledToString
