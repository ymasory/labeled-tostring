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

  test("in a package") {
    val expected =
      "Person2(" + nameField + "=" + name + "," + ageField + "=" + age + ")"
    println(Person2(name, age))
    println(expected)
    // expect(expected){Person2(name, age).toString}
  }
}

case class Person1(name: String, age: Int)
case class Person2(name: String, age: Int) extends LabelledToString
