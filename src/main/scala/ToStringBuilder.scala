package com.yuvimasory.tostring

import java.lang.reflect.Field

import org.apache.commons.lang.builder._

object ToString {

  def generateString(obj: AnyRef): String = {
    val builder = new ReflectionToStringBuilder(obj, Style) {
      override def accept(field: Field) =
        field.getName != "toString"
    }
    builder.toString
  }

  object Style extends StandardToStringStyle
}

trait LabelledToString {
  override val toString = ToString.generateString(this)
}
