import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) {

  //deployment
  override def managedStyle = ManagedStyle.Maven
  val publishTo = (
    "Scala Tools Nexus" at
    "http://nexus.scala-tools.org/content/repositories/releases")
  Credentials(Path.userHome / ".ivy2"/ ".credentials", log)
  override def packageSrcJar= defaultJarPath("-sources.jar")
  val sourceArtifact = Artifact.sources(artifactID)
  override def packageToPublishActions = super.packageToPublishActions ++
    Seq(packageSrc)
  
  //managed dependencies from built-in repositories
  val SonatypeSnapshotRepo =
    MavenRepository("Sonatype OSS Repo",
                    "http://oss.sonatype.org/content/repositories/snapshots")
  val scalaTest = "org.scalatest" % "scalatest" % "1.3"
  val commonsLang = "commons-lang" % "commons-lang" % "2.3"
  val instrumenter =
    "com.google.code.java-allocation-instrumenter" %
    "java-allocation-instrumenter" % "2.0"
  val caliper = "com.google.code.caliper" % "caliper" % "1.0-SNAPSHOT"
  
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
