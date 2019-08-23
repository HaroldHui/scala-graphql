package com.shu.controllers

import cats.effect.IO
import org.http4s.Response

class GraphQLController {
  def schema: IO[Response[IO]] = ???
  def searchFromParams: IO[Response[IO]] = ???
  def searchFromBody: IO[Response[IO]] = ???
}
