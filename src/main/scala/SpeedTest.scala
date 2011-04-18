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
    createObjects({new Person(name, age)},
                  "class objects")
    createObjects({new CasePerson(name, age)},
                  "case class objects")
    createObjects({CasePerson(name, age)},
                  "case class objects w/o new")
    createObjects({new ValPerson(name, age)},
                  "case class objects with LabeledToStringVal")
    createObjects({new ValPerson(name, age)},
                  "case class objects with LabeledToStringLazyVal")
    createObjects({new DefPerson(name, age)},
                  "case class objects with LabeledToStringDef")
    println()
  }


  def time(block: => Unit): Long = {
    val before = System.currentTimeMillis()
    block
    val after = System.currentTimeMillis()
    after - before
  }

  case class ValPerson(name: String, age: Int) extends LabeledToStringVal
  case class LazyValPerson(name: String, age: Int) extends LabeledToStringLazyVal
  case class DefPerson(name: String, age: Int) extends LabeledToStringDef
  case class CasePerson(name: String, age: Int)
  class Person(name: String, age: Int)
}
