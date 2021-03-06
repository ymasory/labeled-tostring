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
        case arr: Array[_] => {
          super.appendFieldStart(buf, fieldName)
          buf append arr.toString
          super.appendFieldEnd(buf, fieldName)
        }        
        case _             => super.append(buf, fieldName, obj, fullDetail)
      }
    }
  }
}

trait LabeledToStringVal {
  override val toString = ToString.generateString(this)
}
trait LabeledToStringLazyVal {
  override lazy val toString = ToString.generateString(this)
}
trait LabeledToStringDef {
  override def toString = ToString.generateString(this)
}
