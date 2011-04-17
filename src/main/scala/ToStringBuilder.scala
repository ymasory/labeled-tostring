package com.yuvimasory.tostring

import java.lang.reflect.Field

import org.apache.commons.lang.builder.ReflectionToStringBuilder

object ToString {

  def generateString(obj: AnyRef): String = {
    val builder = new ReflectionToStringBuilder(obj) {
      override def accept(field: Field) =
        field.getName != "toString"
    }
    builder.toString
  }
}
