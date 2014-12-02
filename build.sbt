name := """scala_kb_dbalibrary"""

version := "1.0"

scalaVersion := "2.11.1"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6" % "test"

// H2 Database
libraryDependencies += "com.h2database" % "h2" % "1.4.182"

// Slick
libraryDependencies += "com.typesafe.slick" %% "slick" % "2.1.0"

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.6.4"
