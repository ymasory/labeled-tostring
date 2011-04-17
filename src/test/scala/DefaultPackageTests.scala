import org.scalatest.FunSuite

import com.yuvimasory.tostring._
import com.yuvimasory.tostring.ToString._


class DefaultPackageTests extends FunSuite {

  val nameField = "name"
  val name = "John Doe"
  val ageField = "age"
  val age = 30

  test("default package") {
    val expected =
      "Person1(" + nameField + "=" + name + "," + ageField + "=" + age + ")"
    expect(expected){Person1(name, age).toString}
  }
}

case class Person1(name: String, age: Int) extends LabelledToString
