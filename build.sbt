// Application infos
name := "scala_kb_dbalibrary"
version := "1.0"

// Scala infos
scalaVersion := "2.11.4"
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

// JDBC
libraryDependencies += "com.h2database" % "h2" % "1.4.182"

// Slick
libraryDependencies += "com.typesafe.slick" %% "slick" % "2.1.0"

// ScalikeJDBC
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc" % "2.2.0"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % "2.2.0"

// Skinny ORM
libraryDependencies += "org.skinny-framework" %% "skinny-orm" % "1.3.6"

// ScalaActiveRecord
libraryDependencies += "com.github.aselab" %% "scala-activerecord" % "0.3.0"

// Others
libraryDependencies += "ch.qos.logback" %  "logback-classic" % "1.1.2" % "test"
libraryDependencies += "org.scalatest"  %% "scalatest" % "2.2.2" % "test"

parallelExecution in Test := false

