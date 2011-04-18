package com.yuvimasory.tostring

import org.scalatest.FunSuite

import ToString._

class ToStringTests extends FunSuite {

  def sameString(a1: Any)(a2: Any) = expect(a1)(a2.toString)

  test("zero params") {
    sameString("ZeroParam1()")(ZeroParam1())
    sameString("ZeroParam2()")(ZeroParam2())
  }

  test("null args") {
    sameString("Null1(null)")(Null1(null))
    sameString("Null2(n=null)")(Null2(null))
  }

  test("boolean args") {
    sameString("Boolean1(true)")(Boolean1(true))
    sameString("Boolean2(bool=true)")(Boolean2(true))
  }

  test("char args") {
    sameString("Char1(c)")(Char1('c'))
    sameString("Char2(char=c)")(Char2('c'))
  }

  test("byte args") {
    sameString("Byte1(-5)")(Byte1(-5))
    sameString("Byte2(byte=-5)")(Byte2(-5))
  }

  test("short args") {
    sameString("Short1(-9)")(Short1(-9))
    sameString("Short2(short=-9)")(Short2(-9))
  }

  test("int args") {
    sameString("Int1(-9)")(Int1(-9))
    sameString("Int2(int=-9)")(Int2(-9))
  }

  test("long args") {
    sameString("Long1(-9)")(Long1(-9))
    sameString("Long2(long=-9)")(Long2(-9))
  }

  test("float args") {
    sameString("Float1(-0.77)")(Float1(-0.77f))
    sameString("Float2(float=-0.77)")(Float2(-0.77f))
  }

  test("double args") {
    sameString("Double1(-0.77)")(Double1(-0.77))
    sameString("Double2(double=-0.77)")(Double2(-0.77))
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
    sameString("Object1(List(1, 2))")(Object1(lst))
    sameString("Object2(obj=List(1, 2))")(Object2(lst))

    val obj = new AnyRef
    val objStr = obj
    sameString("Object1(" + obj + ")") {
      Object1(obj)
    }
    sameString("Object2(obj=" + obj + ")") {
      Object2(obj)
    }
  }

  test("var parameters") {
    val c1 = VarParameter1(0)
    sameString("VarParameter1(0)")(c1)
    c1.int = 1
    sameString("VarParameter1(1)")(c1)

    val c2 = VarParameter2(2)
    sameString("VarParameter2(int=2)")(c2)
    c2.int = 3
    sameString("VarParameter2(int=3)")(c2)
  }

  test("val parameters") {
    sameString("ValParameter1(0)")(ValParameter1(0))
    sameString("ValParameter2(int=0)")(ValParameter2(0))
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
