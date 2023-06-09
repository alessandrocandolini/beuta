import sbt._
import Keys._

object Settings {

  val commonSettings: Seq[Def.Setting[_]] = Seq(
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-language:strictEquality",
      "-language:postfixOps",
      "-Yexplicit-nulls",
      "-source:future"
    )
  )

}

object Dependencies {

  val circe = Seq(
    "io.circe" %% "circe-core"
  ).map(_ % "0.15.0-M1") ++
    Seq("io.circe" %% "circe-fs2" % "0.14.0")

  val fs2 = Seq(
    "fs2-core",
    "fs2-io"
  ).map("co.fs2" %% _ % "3.3.0")

  val cats = Seq(
    "org.typelevel" %% "cats-core" % "2.8.0",
    "org.typelevel" %% "cats-effect" % "3.3.4"
  )

  val postgres = Seq(
    "org.postgresql" % "postgresql" % "42.5.0",
    "org.tpolecat" %% "skunk-core" % "0.3.2"
  )

  val tapirVersion = "1.1.1"
  val tapirNamespace = "com.softwaremill.sttp.tapir"

  val tapir = Seq(
    "tapir-core",
    "tapir-sttp-client",
    "tapir-http4s-server",
    "tapir-json-circe"
  ).map(tapirNamespace%% _ % tapirVersion) ++ Seq("com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % "3.3.18")

  val http4s = Seq(
    "org.http4s" %% "http4s-core" % "0.23.16",
    "org.http4s" %% "http4s-blaze-server" % "0.23.12"
  )


  val tapirTest = Seq(
     "tapir-server-tests"
    ).map(tapirNamespace %% _ % tapirVersion)

  val decline = Seq(
    "decline-effect",
    "decline"
  ).map("com.monovore" %% _ % "2.3.1")

  val config = Seq(
    "pureconfig-core"
  ).map("com.github.pureconfig" %%  _ % "0.17.1" )

  val scalacheckEffect = Seq(
    "scalacheck-effect",
    "scalacheck-effect-munit"
  ).map(
    "org.typelevel" %% _ % "1.0.3"
  )

  val dependencies = circe ++ fs2 ++ cats ++ postgres ++ tapir ++ decline ++ config ++ http4s

  val testDependencies = (Seq(
    "org.scalacheck" %% "scalacheck" % "1.15.4",
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.3"
  ) ++ scalacheckEffect ++ tapirTest).map(_ % "it,test")


}
