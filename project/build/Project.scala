import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) {
  
  //managed dependencies from built-in repositories
  val scalatest = "org.scalatest" % "scalatest" % "1.3"
  
  //files to go in packaged jars
  val extraResources = "README.md" +++ "LICENSE"
  override val mainResources = super.mainResources +++ extraResources

  //turn down logging a bit
  log.setLevel(Level.Warn)
  log.setTrace(2)

  //compiler options
  override def compileOptions = super.compileOptions ++
    compileOptions("-deprecation", "-unchecked")
  override def javaCompileOptions =
    JavaCompileOption("-Xlint:unchecked") :: super.javaCompileOptions.toList

  //scaladoc options
  override def documentOptions =
    LinkSource ::
    documentTitle(name + " " + version + " API") ::
    windowTitle(name + " " + version + " API") ::
    Nil
}
