package com.shu

import org.http4s._
import cats.effect._
import org.http4s.dsl.Http4sDsl

class AppRoutes extends Http4sDsl[IO]{

  def openRoutes: HttpRoutes[IO] = {
    HttpRoutes.of {
      case GET -> Root / "schema" => Ok("world")
    }
  }
}
