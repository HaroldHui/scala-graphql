package com.shu

import cats.effect.{ExitCode, IO, IOApp}
import com.shu.config.Config
import cats.implicits._

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    val server = startServer()
    println("Starting server")
    server
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
