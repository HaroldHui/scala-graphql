package com.shu

import cats.effect.{ExitCode, IO, IOApp}
import com.shu.config.Config
import io.circe.Json

import scala.concurrent.Future
import cats.implicits._
import com.shu.repositories.PropertyRepo
import com.shu.schema.PropertySchema
import sangria.macros._
import sangria.execution._
import sangria.marshalling.circe._

object Main extends IOApp {

  val query =
    graphql"""
    query MyProperty {
      property(id: "2") {
        id
        slug
        postcode
      }

      properties {
        slug
      }
    }
  """

  def run(args: List[String]): IO[ExitCode] = {
    val result: Future[Json] = Executor.execute(PropertySchema.schema, query, new PropertyRepo)
//    val server = startServer()
//    println("Starting server")
//    server
  }

  private def runServerWith(config: Config): IO[ExitCode] = {
    new AppServer(9200, config).stream().compile.drain.as(ExitCode.Success)
  }

  def startServer(): IO[ExitCode] = {
    for {
      config <- Config.fromEnvironment()
      exitCode <- runServerWith(config)
    } yield exitCode
  }
}
