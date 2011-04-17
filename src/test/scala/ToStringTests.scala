package com.yuvimasory.tostring

import org.scalatest.FunSuite

import ToString._

class ToStringTests extends FunSuite {

  val name = "John Doe"
  val age = 30

  test("plane old case class") {
    expect("Person1(" + name + "," + age + ")") {
      Person1(name, age).toString
    }
  }
}

case class Person1(name: String, age: Int)
case class Person2(name: String, age: Int) {
  override val toString = generateString(this)
}
