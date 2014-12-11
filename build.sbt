name := """scala_kb_dbalibrary"""

version := "1.0"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  // H2 Database
  "com.h2database" % "h2" % "1.4.182",
  // Slick
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  // ScalikeJDBC
  "org.scalikejdbc" %% "scalikejdbc" % "2.2.0",
  //"org.slf4j" % "slf4j-nop" % "1.6.4",
  // Skinny ORM
  "org.skinny-framework" %% "skinny-orm"      % "1.3.6",
  //"org.slf4j" % "slf4j-nop" % "1.6.4",
  // Test
  "org.scalatest" %% "scalatest" % "2.1.6" % "test"
)

parallelExecution in Test := false
