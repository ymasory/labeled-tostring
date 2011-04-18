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

    override def append(buf: StringBuffer, fieldName: String,
                        obj: AnyRef, fullDetail: java.lang.Boolean) = {
      obj match {
        case arr: Array[_] => buf append arr.toString
        case _             => super.append(buf, fieldName, obj, fullDetail)
      }
    }
  }
}

trait LabelledToStringVal {
  override val toString = ToString.generateString(this)
}
trait LabelledToStringLazyVal {
  override lazy val toString = ToString.generateString(this)
}
trait LabelledToStringDef {
  override def toString = ToString.generateString(this)
}
