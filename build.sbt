import com.trueaccord.scalapb.{ScalaPbPlugin => PB}

PB.protobufSettings

PB.runProtoc in PB.protobufConfig := (args => com.github.os72.protocjar.Protoc.runProtoc("-v261" +: args.toArray))

name := "venatus"

version := "0.0.1"

scalaVersion := "2.11.7"

organization := "com.vinctus"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.2",
  "com.typesafe.akka" %% "akka-remote" % "2.4.2",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.postgresql" % "postgresql" % "9.4.1208.jre7",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))

homepage := Some(url("https://github.com/emaxedon/vinctus-venatus"))