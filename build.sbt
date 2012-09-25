name := "labeled-tostring"

organization := "com.yuvimasory"

version := "0.5.0"

description := "Scala traits that override toString to display parameter labels"

scalaVersion := "2.9.2"

scalacOptions ++= Seq("-deprecation", "-unchecked")

crossScalaVersions := Seq(
  "2.9.1-1", "2.9.1", "2.9.0-1", "2.9.0",
  "2.8.1", "2.8.0"
)

libraryDependencies += "commons-lang" % "commons-lang" % "2.3"

libraryDependencies <+= (scalaVersion) { v =>
  val scalaTestVersion = if (v startsWith "2.8") "1.3" else "1.4.RC2"
  "org.scalatest" % "scalatest" % scalaTestVersion % "test"
} 

logLevel := Level.Warn

traceLevel := 5

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some(
    "snapshots" at nexus + "content/repositories/snapshots"
  )
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/ymasory/labeled-tostring</url>
  <licenses>
    <license>
      <name>GPLv3</name>
      <url>http://www.gnu.org/licenses/gpl-3.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/ymasory/labeled-tostring.git</url>
    <connection>scm:git:https://github.com/ymasory/labeled-tostring.git</connection>
  </scm>
  <developers>
    <developer>
      <id>ymasory</id>
      <name>Yuvi Masory</name>
      <email>ymasory@gmail.com</email>
      <url>http://yuvimasory.com</url>
    </developer>
  </developers>
)

