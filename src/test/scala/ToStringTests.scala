package com.yuvimasory.tostring

import org.scalatest.FunSuite

import ToStringBuilder._

class ToStringTests extends FunSuite {

  test("no labels") {

    println(Person())
  }
}
case class Person() {override val toString = generateString(this)}
