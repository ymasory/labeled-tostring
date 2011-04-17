package com.yuvimasory.tostring

import org.scalatest.FunSuite

class ToStringTests extends FunSuite {

  test("dummy test works") {
    assert(1 === 1)

    expect(1) {
      2 - 1
    }

    intercept[IllegalArgumentException] {
      throw new IllegalArgumentException()
    }
  }
}
