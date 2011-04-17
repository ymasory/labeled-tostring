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
  test("boolean args") {pending}
  test("char args") {pending}
  test("byte args") {pending}
  test("short args") {pending}
  test("int args") {pending}
  test("long args") {pending}
  test("float args") {pending}
  test("double args") {pending}
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

case class Person1(name: String, age: Int)
case class Person2(name: String, age: Int) extends LabelledToString
case class Person3() extends LabelledToString
case class ArrayClass1(array: Array[Int])
case class ArrayClass2(array: Array[Int]) extends LabelledToString
