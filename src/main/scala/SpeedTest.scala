package com.yuvimasory.tostring

object SpeedTest {
  
  val numObjects = 100000000
  val numTrials = 5

  def main(args: Array[String]) = {
    for (i <- 1 to numTrials) {
      println("trial " + i + " of " + numTrials)
      runTrial()
    }
  }

  def runTrial() {

    def createObjects(objectCreate: => Unit, desc: String) {
      print("making " + numObjects + " " + desc + " ... ")
      println(time {
        for (i <- 0 until numObjects) {
          () => objectCreate.toString
        }
      } + "ms")
    }
    val name = "John Doe"
    val age = 30
    createObjects({new ClassPerson(name, age)},
                  "class objects")
    createObjects({new CaseClassPerson(name, age)},
                  "case class objects")
    createObjects({CaseClassPerson(name, age)},
                  "case class objects w/o new")
    createObjects({new LabelledCaseClassPerson(name, age)},
                  "case class objects with LabelledToString")
    createObjects({new MutableLabelledCaseClassPerson(name, age)},
                  "case class objects with MutableLabelledToString")
    println()
  }


  def time(block: => Unit): Long = {
    val before = System.currentTimeMillis()
    block
    val after = System.currentTimeMillis()
    after - before
  }

  case class LabelledCaseClassPerson(name: String, age: Int)
    extends LabelledToStringVal
  case class MutableLabelledCaseClassPerson(name: String, age: Int)
    extends LabelledToStringDef
  case class CaseClassPerson(name: String, age: Int)
  class ClassPerson(name: String, age: Int)
}
