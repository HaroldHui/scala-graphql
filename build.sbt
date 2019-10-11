name := "scala-playground"

version := "0.1"

scalaVersion := "2.12.10"

val circeVersion = "0.12.2"
val sangriaVersion = "1.4.2"
val http4sVersion = "0.20.11"
val LogbackVersion = "1.2.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.0.0",
  "org.typelevel" %% "cats-effect" % "2.0.0",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "io.circe" %% "circe-literal" % circeVersion,
  "org.sangria-graphql" %% "sangria" % sangriaVersion,
  "org.sangria-graphql" %% "sangria-circe" % "1.2.1",
  "org.sangria-graphql" %% "sangria-relay" % sangriaVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "ch.qos.logback" % "logback-classic" % LogbackVersion
)


mainClass in (Compile, run) := Some("com.shu.Main")

scalacOptions ++= Seq("-Ypartial-unification")
