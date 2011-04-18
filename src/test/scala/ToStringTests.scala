package com.yuvimasory.tostring

import org.scalatest.FunSuite

import ToString._

class ToStringTests extends FunSuite {

  implicit def string2IsStringOf(str : String) = new IsStringOf(str)
  class IsStringOf(str: String) {
    def isStringOf(any: Any) = expect(str)(any.toString)
  }

  test("zero params") {
    "ZeroParam1()" isStringOf ZeroParam1()
    "ZeroParam2()" isStringOf ZeroParam2()
  }

  test("null args") {
    "Null1(null)" isStringOf Null1(null)
    "Null2(n=null)" isStringOf Null2(null)
  }

  test("boolean args") {
    "Boolean1(true)" isStringOf Boolean1(true)
    "Boolean2(bool=true)" isStringOf Boolean2(true)
  }

  test("char args") {
    "Char1(c)" isStringOf Char1('c')
    "Char2(char=c)" isStringOf Char2('c')
  }

  test("byte args") {
    "Byte1(-5)" isStringOf Byte1(-5)
    "Byte2(byte=-5)" isStringOf Byte2(-5)
  }

  test("short args") {
    "Short1(-9)" isStringOf Short1(-9)
    "Short2(short=-9)" isStringOf Short2(-9)
  }

  test("int args") {
    "Int1(-9)" isStringOf Int1(-9)
    "Int2(int=-9)" isStringOf Int2(-9)
  }

  test("long args") {
    "Long1(-9)" isStringOf Long1(-9)
     "Long2(long=-9)" isStringOf Long2(-9)
  }

  test("float args") {
    "Float1(-0.77)" isStringOf Float1(-0.77f)
    "Float2(float=-0.77)" isStringOf Float2(-0.77f)
  }

  test("double args") {
    "Double1(-0.77)" isStringOf Double1(-0.77)
    "Double2(double=-0.77)" isStringOf Double2(-0.77)
  }

  test("object args") {
    val lst = List(1,2)
    "Object1(List(1, 2))" isStringOf Object1(lst)
    "Object2(obj=List(1, 2))" isStringOf Object2(lst)

    val obj = new AnyRef
    val objStr = obj
    ("Object1(" + obj + ")") isStringOf Object1(obj)
    ("Object2(obj=" + obj + ")") isStringOf Object2(obj)
  }

  test("var parameters") {
    val c1 = VarParameter1(0)
    "VarParameter1(0)" isStringOf c1
    c1.int = 1
    "VarParameter1(1)" isStringOf c1

    val c2 = VarParameter2(2)
    "VarParameter2(int=2)" isStringOf c2
    c2.int = 3
    "VarParameter2(int=3)" isStringOf c2
  }

  test("val parameters") {
    "ValParameter1(0)" isStringOf ValParameter1(0)
    "ValParameter2(int=0)" isStringOf ValParameter2(0)
  }

  test("array args") {
    val arr = Array(1,2)
    println(Array2(arr))
    println("Array2(array=" + arr + ")")
    pending //label isn't printing
    ("Array1(" + arr + ")") isStringOf Array1(arr)
    ("Array2(array=" + arr + ")") isStringOf Array2(arr)
  }

  test("mixed val, var, and neither parameters") {
    "MixedFieldDeclarations1(1,2,3)" isStringOf MixedFieldDeclarations1(1,2,3)
    ("MixedFieldDeclarations2(i=1,j=2,k=3)" isStringOf
       MixedFieldDeclarations2(1,2,3))
  }

  test("varargs") {
    "VarArgs1(WrappedArray(ab, cd))" isStringOf VarArgs1("ab", "cd")
    "VarArgs2(str=WrappedArray(ab, cd))" isStringOf VarArgs2("ab", "cd")
  }

  test("multiple argument types") {
    val o = new AnyRef
    val a = Array(1,2)
    pending
    println(MixedArgTypes2(-1, o, a))
    (("MixedArgTypes1(-1," + o + "," + a + ")") isStringOf
       MixedArgTypes1(-1, o, a))
    (("MixedArgTypes2(b=-1,o=" + o + ",a=" + a + ")") isStringOf
       MixedArgTypes2(-1, o, a))
  }
}

case class VarArgs1(str: String*)
case class VarArgs2(str: String*) extends LabelledToStringVal
case class ZeroParam1()
case class ZeroParam2() extends LabelledToStringVal
case class Object1(obj: AnyRef)
case class Object2(obj: AnyRef) extends LabelledToStringVal
case class VarParameter1(var int: Int)
case class VarParameter2(var int: Int) extends LabelledToStringDef
case class ValParameter1(val int: Int)
case class ValParameter2(val int: Int) extends LabelledToStringVal
case class Float1(float: Float)
case class Float2(float: Float) extends LabelledToStringVal
case class Double1(double: Double)
case class Double2(double: Double) extends LabelledToStringVal
case class Byte1(byte: Byte)
case class Byte2(byte: Byte) extends LabelledToStringVal
case class Char1(char: Char)
case class Char2(char: Char) extends LabelledToStringVal
case class Short1(short: Short)
case class Short2(short: Short) extends LabelledToStringVal
case class Int1(int: Int)
case class Int2(int: Int) extends LabelledToStringVal
case class Long1(long: Long)
case class Long2(long: Long) extends LabelledToStringVal
case class Boolean1(bool: Boolean)
case class Boolean2(bool: Boolean) extends LabelledToStringVal
case class Array1(array: Array[Int])
case class Array2(array: Array[Int]) extends LabelledToStringVal
case class Null1(n: Null)
case class Null2(n: Null) extends LabelledToStringVal
case class MixedFieldDeclarations1(var i: Int, val j: Int, k: Int)
case class MixedFieldDeclarations2(var i: Int, val j: Int, k: Int)
  extends LabelledToStringDef
case class MixedArgTypes1(b: Byte, o: AnyRef, a: Array[Int])
case class MixedArgTypes2(b: Byte, o: AnyRef, a: Array[Int])
  extends LabelledToStringVal
