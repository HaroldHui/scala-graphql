package com.shu
import cats.effect.{ContextShift, IO, Timer}
import com.shu.config.Config
import fs2.Stream
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.Logger

import scala.concurrent.ExecutionContext.global

class AppServer(port: Int, config: Config) {

  def stream()(implicit contextShift: ContextShift[IO],
               timer: Timer[IO]): Stream[IO, Nothing] = {
    for {
      client <- BlazeClientBuilder[IO](global).stream
      httpApp = new AppRuntime(config, client, contextShift, timer).routes
      finalHttpApp = Logger.httpApp(logHeaders = true, logBody = true)(httpApp)
      exitCode <- BlazeServerBuilder[IO]
        .bindHttp(port, "0.0.0.0")
        .withHttpApp(finalHttpApp)
        .serve
    } yield exitCode
  }.drain
}
