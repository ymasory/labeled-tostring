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

    def doTrial(objectCreate: => Unit, desc: String) {
      print("making " + numObjects + " " + desc + " ... ")
      println(time {
        for (i <- 0 until numObjects) {
          () => objectCreate.toString
        }
      } + "ms")
    }
    val name = "John Doe"
    val age = 30
    doTrial({new ClassPerson(name, age)},
            "class objects")
    doTrial({new CaseClassPerson(name, age)},
            "case class objects")
    doTrial({CaseClassPerson(name, age)},
            "case class objects w/o new")
    doTrial({new FancyCaseClassPerson(name, age)},
            "case class objects with overridden toString")
    println()
  }


  def time(block: => Unit): Long = {
    val before = System.currentTimeMillis()
    block
    val after = System.currentTimeMillis()
    after - before
  }

  case class FancyCaseClassPerson(name: String, age: Int) {
    override val toString = ToStringBuilder.generateString(this)
  }
  case class CaseClassPerson(name: String, age: Int)
  class ClassPerson(name: String, age: Int)
}
