package com.shu

import cats.effect._
import com.shu.config.Config
import org.http4s._
import org.http4s.client.Client

class AppRuntime(config: Config,
                 httpClient: Client[IO],
                 contextShift: ContextShift[IO],
                 timer: Timer[IO]) {
  private val appRoutes = new AppRoutes
  private val allRoutes: HttpRoutes[IO] = appRoutes.openRoutes
  def routes: HttpApp[IO] =
    HttpApp(
      (req: Request[IO]) =>
        allRoutes(req).getOrElse(Response[IO](status = Status.NotFound))
    )
}
