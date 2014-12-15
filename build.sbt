name := "scala_kb_dbalibrary"
version := "1.0"
scalaVersion := "2.11.4"
libraryDependencies ++= Seq(
  // H2 Database
  "com.h2database" % "h2" % "1.4.182",
  // Slick
  "com.typesafe.slick" %% "slick" % "2.1.0",
  // ScalikeJDBC
  "org.scalikejdbc" %% "scalikejdbc" % "2.2.0",
  "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "2.2.0",
  // Skinny ORM
  "org.skinny-framework" %% "skinny-orm"      % "1.3.6",
  // ScalaActiveRecord
  "com.github.aselab" %% "scala-activerecord" % "0.3.0",
  // Test
  "ch.qos.logback" %  "logback-classic" % "1.1.2" % "test",
  "org.scalatest"  %% "scalatest"       % "2.2.2" % "test"
)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
parallelExecution in Test := false

