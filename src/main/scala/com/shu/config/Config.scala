package com.shu.config

import cats.data.Validated.{Invalid, Valid}
import cats.data.ValidatedNel
import cats.effect.IO
import cats.implicits._

case class Config(version: String, databaseConfig: DatabaseConfig)

object Config {
  def apply(env: Environment): ValidatedNel[ConfigError, Config] = {
    val version = env.optional("VERSION", "(unknown)")
    val databaseConfig = DatabaseConfig(env)
    (version, databaseConfig).mapN(Config.apply)
  }

  def fromEnvironment(): IO[Config] = {
    val env = Environment(sys.env)
    Config(env) match {
      case Invalid(errors) =>
        IO.raiseError(
          new IllegalArgumentException(ConfigError.show(errors.toList))
        )
      case Valid(c) => IO.pure(c)
    }
  }
}
