package com.yuvimasory.tostring

import java.lang.reflect.Field

import org.apache.commons.lang.builder._
import org.apache.commons.lang.ClassUtils

object ToString {

  def generateString(obj: AnyRef): String = {
    val builder = new ReflectionToStringBuilder(obj, Style) {
      override def accept(field: Field) =
        field.getName != "toString"
    }
    builder.toString
  }

  object Style extends StandardToStringStyle {
    setContentStart("(")
    setContentEnd(")")
    setUseShortClassName(true)
    setUseIdentityHashCode(false)

    // override def append(buf: StringBuffer, fieldName: String, ar
  }
}

trait LabelledToString {
  override val toString = ToString.generateString(this)
}
trait MutableLabelledToString {
  override def toString = ToString.generateString(this)
}
