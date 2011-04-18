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
    setNullText("null")

    def toStringAppend(buf: StringBuffer, any: Any) = {
      println("APPENDING AN ARRAY")
      buf append any.toString
    }

    override def append(buf: StringBuffer, fieldName: String,
                        arr: Array[Char], fullDetail: java.lang.Boolean) =
      toStringAppend(buf, arr)

    override def append(buf: StringBuffer, fieldName: String,
                        arr: Array[Byte], fullDetail: java.lang.Boolean) =
      toStringAppend(buf, arr)

    override def append(buf: StringBuffer, fieldName: String,
                        arr: Array[Short], fullDetail: java.lang.Boolean) =
      toStringAppend(buf, arr)

    override def append(buf: StringBuffer, fieldName: String,
                        arr: Array[Int], fullDetail: java.lang.Boolean) =
      toStringAppend(buf, arr)

    override def append(buf: StringBuffer, fieldName: String,
                        arr: Array[Long], fullDetail: java.lang.Boolean) =
      toStringAppend(buf, arr)

    override def append(buf: StringBuffer, fieldName: String,
                        arr: Array[Float], fullDetail: java.lang.Boolean) =
      toStringAppend(buf, arr)

    override def append(buf: StringBuffer, fieldName: String,
                        arr: Array[Double], fullDetail: java.lang.Boolean) =
      toStringAppend(buf, arr)

    override def append(buf: StringBuffer, fieldName: String,
                        arr: Array[AnyRef], fullDetail: java.lang.Boolean) =
      toStringAppend(buf, arr)
  }
}

trait LabelledToString {
  override val toString = ToString.generateString(this)
}
trait MutableLabelledToString {
  override def toString = ToString.generateString(this)
}
