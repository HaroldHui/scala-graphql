package com.shu.config

import cats.data.ValidatedNel
import cats.implicits._

final case class Environment(env: Map[String, String]) {
  def optional(key: String,
               defaultValue: => String): ValidatedNel[ConfigError, String] = {
    env.getOrElse(key, defaultValue).validNel
  }

  def required(key: String): ValidatedNel[ConfigError, String] = {
    env.get(key) match {
      case Some(value) => value.validNel
      case None        => MissingEnvironmentVariable(key).invalidNel
    }
  }
}
