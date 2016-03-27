name := "venatus"

version := "0.1"

scalaVersion := "2.11.7"

organization := "com.vinctus"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.2",
  "com.google.protobuf" % "protobuf-java" % "2.6.1",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "org.postgresql" % "postgresql" % "9.4.1208.jre7",
  "de.svenkubiak" % "jBCrypt" % "0.4"
)

licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/emaxedon/vinctus-venatus"))